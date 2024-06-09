SUMMARY = "First boot runner"
DESCRIPTION = "A mechanism to run scripts only on first boot"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = " \
    file://firstboot-runner.service \
    file://runner_starter.sh \
    file://config_ros_env.sh \
    file://demo_runner.sh \
"

RUNNER_SCRIPT_LIST = " \
    config_ros_env.sh \
    demo_runner.sh \
"

S = "${WORKDIR}"

inherit systemd

RUNNER_REBOOT_TIMEOUT ??= "5"
RUNNER_REBOOT_TIMEOUT:qemuall ??= ""

do_install() {
    # Install all runner scripts to /etc/profile.d/system_setup/firstboot_runner
    SYSTEM_SETUP_DIR=/etc/profile.d/system_setup
    FIRSTBOOT_SCRIPT_DIR=${SYSTEM_SETUP_DIR}/firstboot_runner

    install -d ${D}${FIRSTBOOT_SCRIPT_DIR}
    for file_uri in ${SRC_URI}; do
        if echo ${file_uri} | grep -q '^file://'; then
            file_name="${file_uri#file://}"
            if echo ${file_name} | grep -q "\.sh$"; then
                install -m 0755 ${WORKDIR}/${file_name} ${D}${FIRSTBOOT_SCRIPT_DIR}
            fi 
        fi
    done
    # set reboot timeout for runner starter script
    if [ -n "${RUNNER_REBOOT_TIMEOUT}" ]; then
        sed -i "s/REBOOT_TIMEOUT_PLACEHOLDER/${RUNNER_REBOOT_TIMEOUT}/g" ${D}${FIRSTBOOT_SCRIPT_DIR}/runner_starter.sh
    fi

    # Add RUNNER_SCRIPT_LIST to runner.list
    FIRSTBOOT_RUNNER_LIST=${D}${FIRSTBOOT_SCRIPT_DIR}/runner.list
    echo "${@'\n'.join(d.getVar('RUNNER_SCRIPT_LIST').split())}" > "${FIRSTBOOT_RUNNER_LIST}"

    # Install the systemd service file
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/firstboot-runner.service ${D}${systemd_system_unitdir}/
}

# Add to systemd service
SYSTEMD_SERVICE:${PN} = "firstboot-runner.service"
SYSTEMD_AUTO_ENABLE = "enable"
