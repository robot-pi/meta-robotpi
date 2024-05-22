# Copyright (c) 2024 Robot Pi Inc.

SUMMARY = "TurtleBot 3 packages that must be run on the device"

# Need this because the recipe uses MACHINE overrides.
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup
inherit ros_distro_${ROS_DISTRO}

# Include ROS turtlebot3 related packages
# Refer to https://emanual.robotis.com/docs/en/platform/turtlebot3/sbc_setup/

TURTLEBOT3_PACKAGES_LIDAR ??= ""
TURTLEBOT3_PACKAGES_LIDAR:append:rpi = " \
    ld08-driver \
"

TURTLEBOT3_PACKAGES_CAMERA ??= " \
    camera-info-manager \
    compressed-image-transport \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'v4l2-camera', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'camera-ros', '', d)} \
"
TURTLEBOT3_PACKAGES_CAMERA:remove:qemuall = " \
    v4l2-camera \
    camera-ros \
"

RDEPENDS:${PN} = " \
    packagegroup-robotpi-ros-core \
    turtlebot3 \
    turtlebot3-msgs \
    ${TURTLEBOT3_PACKAGES_CAMERA} \
    ${TURTLEBOT3_PACKAGES_LIDAR} \
"

# TODO: add replacement for rosserial-python in ros2
RDEPENDS:${PN}:append:ros1-distro = " \
    rosserial-python \
"
