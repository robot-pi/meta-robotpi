# Copyright (c) 2024 Robot Pi Inc.

require ${COREBASE}/meta/recipes-core/images/core-image-minimal.bb

SUMMARY = "Robot Pi image with core TurtleBot3 and ROS2 packages"
DESCRIPTION = "${SUMMARY}"

inherit ros_distro_${ROS_DISTRO}
inherit ${ROS_DISTRO_TYPE}_image

IMAGE_INSTALL:append = " \
    ros-core \
    packagegroup-robotpi-turtlebot3-core \
"
