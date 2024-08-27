# Temporarily disable gpsd
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-disable-USBAUTO-to-prevent-gpsd-from-occupying-dev-t.patch"

