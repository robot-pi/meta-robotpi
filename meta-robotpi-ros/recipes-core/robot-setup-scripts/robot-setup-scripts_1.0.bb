SUMMARY = "Robot Pi setup scripts"
DESCRIPTION = "Install scripts to specific destination for Robot Pi OS setup"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "file://ros_env_setup.sh \
           file://fastrtps_default_profile.xml \
"

S = "${WORKDIR}"

do_install () {
	# ros setup script location
    ros_profile_dir=${sysconfdir}/profile.d/ros
    install -d ${D}${ros_profile_dir}

    install -m 0755 ${WORKDIR}/ros_env_setup.sh ${D}${ros_profile_dir}
    install -m 0644 ${WORKDIR}/fastrtps_default_profile.xml ${D}${ros_profile_dir}

    # other scriot location starts here
}
