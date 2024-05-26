SUMMARY = "Configure USB over Ethernet interface upon boot"
DESCRIPTION = "Assign IP and bring up interface if USB over Ethernet is available"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "file://usb_ether_config.sh \
           file://usb-ether-config.service"


S = "${WORKDIR}"

inherit systemd

do_install() {
    # Install the script to /etc/profile.d/system_setup/
    install -d ${D}/etc/profile.d/system_setup
    install -m 0755 ${WORKDIR}/usb_ether_config.sh ${D}/etc/profile.d/system_setup/

    # Install the systemd service file
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/usb-ether-config.service ${D}${systemd_system_unitdir}/
}

# Add to systemd service
SYSTEMD_SERVICE:${PN} = "usb-ether-config.service"
# Manage service per override (disabled by default)
USB_ETHER_ENABLE_SERVICE ??= "mask"
USB_ETHER_ENABLE_SERVICE:qemuall ??= "mask"
USB_ETHER_ENABLE_SERVICE:rpi ??= "enable"
SYSTEMD_AUTO_ENABLE = "${USB_ETHER_ENABLE_SERVICE}"
