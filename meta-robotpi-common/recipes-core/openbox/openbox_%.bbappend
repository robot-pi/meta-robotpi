# require openbox_%.bbappend from meta-rpb

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://robotpi_menu.xml"

do_install:append:robotpi() {
    install -m 0644 ${WORKDIR}/robotpi_menu.xml ${D}${sysconfdir}/xdg/openbox/menu.xml
}
