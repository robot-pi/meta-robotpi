SUMMARY = "Organize test packages to avoid duplication across all images"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup
inherit ros_distro_${ROS_DISTRO}

# Python support for running tests
ROBOTPI_BASE_DEBUG_PYTHON_PKGS ??= " \
    python3 \
    python3-misc \
    python3-modules \
    python3-pexpect \
    python3-pip \
    python3-pyyaml \
    "

# ROS debug packages
ROBOTPI_BASE_DEBUG_ROS_PKGS ??= " \
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

# Test apps that can be used in console (no graphics)
ROBOTPI_BASE_DEBUG_CONSOLE_PKGS ??= " \
    alsa-utils-alsaucm \
    alsa-utils-speakertest \
    ${@oe.utils.conditional("PREFERRED_PROVIDER_virtual/kernel", "linux-dummy", "", "cpupower", d)} \
    clang \
    cmake \
    crash \
    cryptsetup \
    dhrystone \
    ethtool \
    git \
    gps-utils \
    gpsd \
    i2c-tools \
    igt-gpu-tools-tests \
    iozone3 \
    iperf3 \
    libdrm-tests \
    libgpiod-tools \
    lmbench \
    ltp \
    mbw \
    netperf \
    net-snmp \
    s-suite \
    stress-ng \
    sysbench \
    pciutils \
    pm-qa \
    ptest-runner \
    systemd-analyze \
    tinymembench \
    tiobench \
    usbutils \
    whetstone \
    vim \
    "

# contains basic dependencies, that don't need graphics/display
RDEPENDS:${PN} = " \
    packagegroup-core-buildessential \
    ${ROBOTPI_BASE_DEBUG_CONSOLE_PKGS} \
    ${ROBOTPI_BASE_DEBUG_PYTHON_PKGS} \
    ${ROBOTPI_BASE_DEBUG_ROS_PKGS} \
"

RDEPENDS:${PN}:remove:libc-musl = "igt-gpu-tools-tests"
