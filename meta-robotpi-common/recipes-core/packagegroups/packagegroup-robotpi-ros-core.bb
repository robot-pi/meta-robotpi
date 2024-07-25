# Copyright (c) 2024 Robot Pi Inc.

SUMMARY = "Robot Pi packages for ROS functionalities"

# Need this because the recipe uses MACHINE overrides.
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup
inherit ros_distro_${ROS_DISTRO}


TURTLEBOT3_PACKAGES_LIDAR = " \
    rplidar-ros \
    ld08-driver \
"

TURTLEBOT3_PACKAGES_CAMERA = " \
    camera-info-manager \
    compressed-image-transport \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'v4l2-camera', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'camera-ros', '', d)} \
"

TURTLEBOT3_PACKAGES_REQUIRED = " \
    turtlebot3 \
    turtlebot3-msgs \
    ${TURTLEBOT3_PACKAGES_CAMERA} \
    ${TURTLEBOT3_PACKAGES_LIDAR} \
"
 
RDEPENDS:${PN} = " \
    ${TURTLEBOT3_PACKAGES_REQUIRED} \
    robot-setup-scripts \
    ros-base \
    ros-core \
    ros2-control \
    demo-nodes-cpp \
    demo-nodes-py \
    moveit \
    xacro \
"