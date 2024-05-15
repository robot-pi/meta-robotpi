# Copyright (c) 2024 Robot Pi Inc.

SUMMARY = "Core packages of Robot Pi native build tools"

# Need this because the recipe uses MACHINE overrides.
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup
inherit ros_distro_${ROS_DISTRO}

RDEPENDS:${PN} = " \
    ament-cmake \
    cmake \
    clang \
    packagegroup-core-buildessential \
    python-rosdep-data \
    python3-rosdep \
    python3-rosdistro \
    python3-rospkg \
    python3-argcomplete \
    python3-colcon-common-extensions \
    python3-colcon-notification \
    python3-colcon-python-setup-py \
    systemd-analyze \
"