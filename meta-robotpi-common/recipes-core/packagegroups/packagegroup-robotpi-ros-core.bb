# Copyright (c) 2024 Robot Pi Inc.

SUMMARY = "ROS core packages on Robot Pi OS"

# Need this because the recipe uses MACHINE overrides.
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup
inherit ros_distro_${ROS_DISTRO}

RDEPENDS:${PN} = " \
    ros-base \
    ros-core \
    demo-nodes-cpp \
    demo-nodes-py \
"