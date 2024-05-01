# Copyright (c) 2024 Robot Pi Inc.

SUMMARY = "TurtleBot 3 packages that must be run on the device"

# Need this because the recipe uses MACHINE overrides.
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup
inherit ros_distro_${ROS_DISTRO}

# Include ROS turtlebot3 related packages
# Refer to https://emanual.robotis.com/docs/en/platform/turtlebot3/sbc_setup/

TURTLEBOT3_PACKAGES_LIDAR ??= " \
    hls-lfcd-lds-driver \
"
TURTLEBOT3_PACKAGES_LIDAR:qemux86 = ""

TURTLEBOT3_PACKAGES_CAMERA ??= " \
    camera-info-manager \
    compressed-image-transport \
"
TURTLEBOT3_PACKAGES_CAMERA:append:rpi = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'camera-ros', '', d)} \
"

TURTLEBOT3_PACKAGES_COLCON ??= " \
    python3-argcomplete \
    python3-colcon-common-extensions \
    python3-colcon-notification \
    python3-colcon-python-setup-py \
"

TURTLEBOT3_PACKAGES_ROSDEP ??= " \
    python-rosdep-data \
    python3-rosdep \
    python3-rosdistro \
    python3-rospkg \
"

RDEPENDS:${PN} = " \
    ros-base \
    tf2 \
    turtlebot3 \
    turtlebot3-msgs \
    ${TURTLEBOT3_PACKAGES_CAMERA} \
    ${TURTLEBOT3_PACKAGES_LIDAR} \
    ${TURTLEBOT3_PACKAGES_COLCON} \
    ${TURTLEBOT3_PACKAGES_ROSDEP} \
"

# TODO: add replacement for rosserial-python in ros2
RDEPENDS:${PN}:append:ros1-distro = " \
    rosserial-python \
"
