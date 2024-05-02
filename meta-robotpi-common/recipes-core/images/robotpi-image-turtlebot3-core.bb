# Copyright (c) 2024 Robot Pi Inc.

require ${COREBASE}/meta/recipes-sato/images/core-image-sato.bb

SUMMARY = "Robot Pi image with core TurtleBot3 and ROS2 packages"
DESCRIPTION = "${SUMMARY}"

inherit ros_distro_${ROS_DISTRO}
inherit ${ROS_DISTRO_TYPE}_image

ROBOTPI_EXTRA_PKGS ??= " \
    tzdata \
    systemd-analyze \
"

IMAGE_INSTALL:append = " \
    ${ROBOTPI_EXTRA_PKGS} \
    ros-core \
    packagegroup-robotpi-turtlebot3-core \
"
