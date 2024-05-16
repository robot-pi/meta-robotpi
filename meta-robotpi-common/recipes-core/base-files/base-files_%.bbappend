do_install:append () {
	echo ${DISTRO} > ${D}${sysconfdir}/hostname
}