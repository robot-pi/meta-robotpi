# Copyright (c) 2024 Robot Pi Inc.

require ${COREBASE}/../meta-robotpi/meta-robotpi-common/recipes-core/images/robotpi-image-rb5-release.bb

SUMMARY = "Robot Pi image For RB5 (Debug)"
DESCRIPTION = "${SUMMARY}"

inherit ros_distro_${ROS_DISTRO}
inherit ${ROS_DISTRO_TYPE}_image

IMAGE_INSTALL:append = " \
    packagegroup-robotpi-dev-tools \
"
