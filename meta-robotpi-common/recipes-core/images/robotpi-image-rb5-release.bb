# Copyright (c) 2024 Robot Pi Inc.

require ${COREBASE}/meta/recipes-sato/images/core-image-sato.bb

SUMMARY = "Robot Pi image For RB5 (Release)"
DESCRIPTION = "${SUMMARY}"

inherit ros_distro_${ROS_DISTRO}
inherit ${ROS_DISTRO_TYPE}_image

IMAGE_INSTALL:append = " \
    packagegroup-robotpi-ros-core \
    packagegroup-robotpi-system-setup \
    packagegroup-robotpi-rb5-core \
"
