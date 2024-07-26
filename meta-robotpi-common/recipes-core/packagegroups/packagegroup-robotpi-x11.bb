# Copyright (c) 2024 Robot Pi Inc.

SUMMARY = "Robot Pi X11 packages for all images"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup features_check

REQUIRED_DISTRO_FEATURES = "x11"

# Web app in X11 Desktop
ROBOTPI_PACKAGES_X11_WEB = " \
    chromium-x11 \
"

# Apps that can be used in X11 Desktop
ROBOTPI_PACKAGES_X11_APPS = " \
    ${ROBOTPI_PACKAGES_X11_WEB} \
    96boards-artwork \ 
    feh-autostart \
    ffmpeg \
    glmark2 \
    gstreamer1.0-plugins-bad-meta \
    gstreamer1.0-plugins-base-meta \
    gstreamer1.0-plugins-good-meta \
    ${@bb.utils.contains("LICENSE_FLAGS_WHITELIST", "commercial_gstreamer1.0-libav", "gstreamer1.0-libav", "", d)} \
    gtkperf \
    kmscube \
    mesa-demos \
    openbox \
    openbox-theme-clearlooks \
    vulkan-tools \
    xf86-video-modesetting \
    xterm \
"

# Additional utils that can be used in X11 Desktop
ROBOTPI_PACKAGES_X11_UTILS = " \
    iceauth \
    sessreg \
    setxkbmap \
    xauth \
    xclock \
    xgamma \
    xlsfonts \
    xmag \
    xmessage \
    xrandr \
    xrdb \
    xrefresh \
    xsetmode \
    xsetroot \
"

RDEPENDS:${PN} = " \
    ${ROBOTPI_PACKAGES_X11_APPS} \
    ${ROBOTPI_PACKAGES_X11_UTILS} \
"