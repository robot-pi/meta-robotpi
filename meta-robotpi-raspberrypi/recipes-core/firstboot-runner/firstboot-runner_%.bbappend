FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:rpi = " \
    file://rpi_fs_resize.sh \
"

RUNNER_SCRIPT_LIST:append:rpi = " \
    rpi_fs_resize.sh \
"
