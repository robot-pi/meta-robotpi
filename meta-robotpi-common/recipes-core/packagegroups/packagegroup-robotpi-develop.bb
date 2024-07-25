# Copyright (c) 2024 Robot Pi Inc.

SUMMARY = "Robot Pi packages for development purpose"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup
inherit ros_distro_${ROS_DISTRO}

# ROS dev packages
ROBOTPI_PACKAGES_DEV_ROS = " \
    ament-cmake \
    python3-argcomplete \
    python3-colcon-common-extensions \
    python3-colcon-notification \
    python3-colcon-python-setup-py \
    python-rosdep-data \
    python3-rosdep \
    python3-rosdistro \
    python3-rospkg \
"

# Dev apps in console (no graphics)
ROBOTPI_PACKAGES_DEV_CONSOLE = " \
    clang \
    cmake \
    packagegroup-core-buildessential \
    ptest-runner \
"

# Dev apps in x11 graphics
ROBOTPI_PACKAGES_DEV_X11 = " \
    gst-devtools \
    libegl-dev \
    opengl-es-cts \
    parallel-deqp-runner \
    piglit \
    x11perf \
    xdpyinfo \
    xlsatoms \
    xlsclients \
    xserver-xorg-xvfb \
"

RDEPENDS:${PN} = " \
    ${ROBOTPI_PACKAGES_DEV_CONSOLE} \
    ${ROBOTPI_PACKAGES_DEV_ROS} \
    ${ROBOTPI_PACKAGES_DEV_X11} \
"
