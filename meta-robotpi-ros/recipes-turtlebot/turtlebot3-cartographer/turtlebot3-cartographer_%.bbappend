FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://0001-remove-rviz-launch-dependency.patch \
    file://0002-add-rplidar-a1m8-support.patch \
"
