SUMMARY = "Test apps that can be used in X11 Desktop"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS:${PN} = "\
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
