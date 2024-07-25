# Copyright (c) 2024 Robot Pi Inc.

SUMMARY = "Robot Pi packages for debugging purpose"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

# Python support for debugging
ROBOTPI_PACKAGES_DEBUG_PYTHON = " \
    python3 \
    python3-misc \
    python3-modules \
    python3-pexpect \
    python3-pip \
    python3-pyyaml \
    "

# Debug apps in console (no graphics)
ROBOTPI_PACKAGES_DEBUG_CONSOLE = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd-analyze', '', d)} \
    ${@oe.utils.conditional("PREFERRED_PROVIDER_virtual/kernel", "linux-dummy", "", "cpupower", d)} \
    alsa-utils-alsaucm \
    alsa-utils-speakertest \
    crash \
    cryptsetup \
    curl \
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
    pciutils \
    perf \
    pm-qa \
    powertop \
    s-suite \
    stress-ng \
    sysbench \
    systemtap \
    tinymembench \
    tiobench \
    usbutils \
    valgrind \
    vim \
    wget \
    whetstone \
"

RDEPENDS:${PN} = " \
    ${ROBOTPI_PACKAGES_DEBUG_PYTHON} \
    ${ROBOTPI_PACKAGES_DEBUG_CONSOLE} \
"

RDEPENDS:${PN}:remove:libc-musl = "igt-gpu-tools-tests"
