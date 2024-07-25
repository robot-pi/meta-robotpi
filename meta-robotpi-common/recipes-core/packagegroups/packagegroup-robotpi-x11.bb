SUMMARY = "Organize packages to avoid duplication across all images (with X11)"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup features_check

REQUIRED_DISTRO_FEATURES = "x11"

# Apps that can be used in X11 Desktop
ROBOTPI_X11_APPS_PKGS ??= " \
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
ROBOTPI_X11_UTILS_PKGS ??= " \
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
    ${ROBOTPI_X11_APPS_PKGS} \
    ${ROBOTPI_X11_UTILS_PKGS} \
"