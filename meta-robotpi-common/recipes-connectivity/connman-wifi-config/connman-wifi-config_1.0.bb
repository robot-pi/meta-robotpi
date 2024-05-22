SUMMARY = "Default Wi-Fi config for Connman"
DESCRIPTION = "Install default Wi-Fi config to Connman setting"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "file://default_wifi_connection.config \
"

S = "${WORKDIR}"

DEPENDS += "connman"

RDEPENDS:${PN} += "connman"

do_install () {
    connman_setting_dir=/var/lib/connman
    install -d ${D}${connman_setting_dir}
    install -m 0644 ${WORKDIR}/default_wifi_connection.config ${D}${connman_setting_dir}

    if [ -n "${CONNMAN_WIFI_NAME}" ]; then
        sed -i 's/WIFI_NAME/${CONNMAN_WIFI_NAME}/g' ${D}${connman_setting_dir}/default_wifi_connection.config
    fi
    if [ -n "${CONNMAN_WIFI_PASSPHRASE}" ]; then
        sed -i 's/WIFI_PASSPHRASE/${CONNMAN_WIFI_PASSPHRASE}/g' ${D}${connman_setting_dir}/default_wifi_connection.config
    fi
}
