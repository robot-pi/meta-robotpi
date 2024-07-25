require robotpi-minimal-image.bb

SUMMARY = "Robot Pi release image with X11 graphics"

IMAGE_FEATURES += "package-management ssh-server-openssh hwcodecs x11"

# let's make sure we have a good image..
REQUIRED_DISTRO_FEATURES += "x11"

# make sure we boot to desktop
# by default and without x11-base in IMAGE_FEATURES we default to multi-user.target
SYSTEMD_DEFAULT_TARGET = "graphical.target"

CORE_IMAGE_BASE_INSTALL += " \
    packagegroup-robotpi-base \
    packagegroup-robotpi-ros-core \
    packagegroup-robotpi-x11 \
"

EXTRA_USERS_PARAMS += "\
usermod -a -G adm robotpi; \
usermod -a -G cdrom robotpi; \
usermod -a -G plugdev robotpi; \
usermod -a -G video robotpi; \
usermod -a -G voice robotpi; \
"

# docker pulls runc/containerd, which in turn recommend lxc unecessarily
BAD_RECOMMENDATIONS:append = " lxc"