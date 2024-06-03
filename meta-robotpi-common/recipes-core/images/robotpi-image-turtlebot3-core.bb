# Copyright (c) 2024 Robot Pi Inc.

require ${COREBASE}/meta/recipes-sato/images/core-image-sato.bb

SUMMARY = "Robot Pi image with core TurtleBot3 and ROS2 packages"
DESCRIPTION = "${SUMMARY}"

inherit ros_distro_${ROS_DISTRO}
inherit ${ROS_DISTRO_TYPE}_image

IMAGE_INSTALL:append = " \
    packagegroup-robotpi-dev-tools \
    packagegroup-robotpi-ros-core \
    packagegroup-robotpi-system-setup \
    packagegroup-robotpi-turtlebot3-core \
    packagegroup-robotpi-ui-core \
"
