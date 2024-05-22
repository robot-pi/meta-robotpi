FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

COMPATIBLE_MACHINE = "raspberrypi4-64"

SRC_URI += " \
    file://usbc-driver-config.cfg \
    "