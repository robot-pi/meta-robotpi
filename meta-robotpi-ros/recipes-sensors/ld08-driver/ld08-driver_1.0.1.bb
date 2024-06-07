# Copyright 2024 Robot Pi Inc. 

inherit ros_distro_humble
inherit ros_superflore_generated

DESCRIPTION = "ROS 2 package for LD08 LiDAR. The Lidar sensor sends data to the Host controller for the Simultaneous Localization And Mapping(SLAM)."
AUTHOR = "Weiheng Ni <weihengni@robotpi.com>"
ROS_AUTHOR = "Will Son <willson@robotis.com>"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d8927f3331d2b3e321b7dd1925166d25"

ROS_CN = "ld08_driver"
ROS_BPN = "ld08_driver"

SRC_URI = "git://github.com/ROBOTIS-GIT/ld08_driver.git;branch=ros2-devel;protocol=https"
SRCREV = "f63c92d178dab1d54cee0f0ed143a2b706c17487"

S = "${WORKDIR}/git"

ROS_BUILD_DEPENDS = " \
    boost \
    rclcpp \
    sensor-msgs \
    udev \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
    ament-lint-auto \
"

ROS_EXPORT_DEPENDS = " \
    boost \
    rclcpp \
    sensor-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    boost \
    rclcpp \
    sensor-msgs \
    udev \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

ROS_BUILD_TYPE = "ament_cmake"

inherit ros_${ROS_BUILD_TYPE}