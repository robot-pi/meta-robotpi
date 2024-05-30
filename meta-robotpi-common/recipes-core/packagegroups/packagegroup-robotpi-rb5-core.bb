# Copyright (c) 2024 Robot Pi Inc.

SUMMARY = "RB5 packages that must be run on the device"

# Need this because the recipe uses MACHINE overrides.
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup
inherit ros_distro_${ROS_DISTRO}

RB5_PACKAGES_CAMERA ??= " \
    camera-info-manager \
    compressed-image-transport \
    v4l2-camera \
    camera-ros \
"

RB5_PACKAGES_SLAM ??= ""

RB5_PACKAGES_NAVIGATION ??= ""

RB5_PACKAGES_MOTION ??= ""

RDEPENDS:${PN} = " \
    packagegroup-robotpi-ros-core \
    ${RB5_PACKAGES_CAMERA} \
    ${RB5_PACKAGES_SLAM} \
    ${RB5_PACKAGES_NAVIGATION} \
    ${RB5_PACKAGES_MOTION} \
"
