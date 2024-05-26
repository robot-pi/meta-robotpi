SUMMARY = "Default Connman Configuration on Robot Pi"
DESCRIPTION = "Configure Wi-Fi & BT state, and install default Wi-Fi config"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = " \
    file://default_wifi_connection.config \
    file://settings \
"

S = "${WORKDIR}"

DEPENDS += "connman"
RDEPENDS:${PN} += "connman"

# Default connman configuration
CONNMAN_WIFI_ENABLE ??= "true"
CONNMAN_BT_ENABLE ??= "false"
CONNMAN_WIFI_NAME ??= "WIFI_NAME_DEFAULT"
CONNMAN_WIFI_PASSPHRASE ??= "WIFI_PASSPHRASE_DEFAULT"

do_install () {
    # install connman default config/settings
    connman_setting_dir=/var/lib/connman
    install -d ${D}${connman_setting_dir}
    install -m 0644 ${WORKDIR}/default_wifi_connection.config ${D}${connman_setting_dir}
    install -m 0644 ${WORKDIR}/settings ${D}${connman_setting_dir}

    # enable/disable wifi, bt in connman settings
    [ "${CONNMAN_WIFI_ENABLE}" != "true" ] && CONNMAN_WIFI_ENABLE="false"
    [ "${CONNMAN_BT_ENABLE}" != "true" ] && CONNMAN_BT_ENABLE="false"
    sed -i "s/WIFI_ENABLE_DEFAULT/${CONNMAN_WIFI_ENABLE}/g" ${D}${connman_setting_dir}/settings
    sed -i "s/BT_ENABLE_DEFAULT/${CONNMAN_BT_ENABLE}/g" ${D}${connman_setting_dir}/settings

    # replace wifi ssid/password with CONNMAN_WIFI_NAME/CONNMAN_WIFI_PASSPHRASE env vars
    if [ -n "${CONNMAN_WIFI_NAME}" ]; then
        sed -i "s/WIFI_NAME/${CONNMAN_WIFI_NAME}/g" ${D}${connman_setting_dir}/default_wifi_connection.config
    fi
    if [ -n "${CONNMAN_WIFI_PASSPHRASE}" ]; then
        sed -i "s/WIFI_PASSPHRASE/${CONNMAN_WIFI_PASSPHRASE}/g" ${D}${connman_setting_dir}/default_wifi_connection.config
    fi
}


do_install:append:qemuall () {
    # do not cutomize config for qemu
    rm ${D}${connman_setting_dir}/*
}
