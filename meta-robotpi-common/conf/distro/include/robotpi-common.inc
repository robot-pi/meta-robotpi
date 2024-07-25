# Robot-Pi Distro Common Include File
# Reference: https://github.com/96boards/meta-rpb/blob/kirkstone/conf/distro/include/rpb.inc


# Robotpi configs
DISTRO_NAME = "Robot-Pi OS Reference Distribution"
DISTRO_CODENAME = "robotpi"
DISTRO_VERSION = "robotpi-1.0+${ROS_DISTRO_METADATA_VERSION_MAJOR}.${ROS_DISTRO_METADATA_VERSION_MINOR}"

SDK_NAME_PREFIX = "${DISTRO}"
SDK_VENDOR = "-robotpisdk"
SDK_VERSION = "${@d.getVar('DISTRO_VERSION').replace('snapshot-${METADATA_REVISION}', 'snapshot')}"
SDK_VERSION[vardepvalue] = "${SDK_VERSION}"

TARGET_VENDOR = "-robotpi"

MAINTAINER = "Weiheng Ni <weihengni@robotpi.net>"


# Yocto uninative
require conf/distro/include/yocto-uninative.inc
INHERIT += "uninative"

# ROS common configs (overwritten by Robotpi configs)
require conf/distro/include/ros-useful-buildcfg-vars.inc

# meta-rpb references
require conf/distro/include/arm-defaults.inc
require conf/distro/include/egl.inc

# defaultsetup.inc gets includes after ${DISTRO}.conf, so we need to set it here
# to make the python below work. Local, site and auto.conf will override it.
TCMODE ?= "default"

# Enable multilib conditionally, only for aarch64 with default toolchain combination
# Other combinations aren't supported
def get_multilib_handler(d):
    features = d.getVar('TUNE_FEATURES', True).split()
    tcmode = d.getVar('TCMODE', True)
    if ('aarch64' in features) and (tcmode == "default"):
        distro_multilib = "conf/distro/include/distro-multilib.inc"
    else:
        distro_multilib = "conf/distro/include/file-cannot-be-found.inc"
    return distro_multilib

# Use a weak include to avoid to produce an error when the file cannot be found.
# It is the case when we don't want multilib enabled (e.g. on 32bit machines).
#include ${@get_multilib_handler(d)}


GCCVERSION ?= "arm-11.2"

ROBOTPI_DEFAULT_DISTRO_FEATURES = " \
    glfw \
    largefile \
    multiarch \
    opengl \
    pam \
    polkit \
    ptest \
    systemd \
    usrmerge \
    vulkan \
"
ROBOTPI_DEFAULT_EXTRA_RDEPENDS = "packagegroup-core-boot"

DISTRO_FEATURES:remove = "3g sysvinit"
DISTRO_FEATURES:append = " ${ROBOTPI_DEFAULT_DISTRO_FEATURES}"
DISTRO_EXTRA_RDEPENDS:append = " ${ROBOTPI_DEFAULT_EXTRA_RDEPENDS}"

VIRTUAL-RUNTIME_init_manager = "systemd"
PACKAGECONFIG:append:pn-systemd = " resolved networkd"
PACKAGECONFIG:append:pn-qtbase = " gles2 fontconfig examples"
PACKAGECONFIG:remove:pn-gpsd = "qt"
PACKAGECONFIG:append:pn-gstreamer1.0-plugins-bad = " kms"
PACKAGECONFIG:append:pn-ffmpeg = " sdl2"
PACKAGECONFIG:append:pn-igt-gpu-tools = " chamelium"

PREFERRED_PROVIDER_iasl-native = "acpica-native"
PREFERRED_PROVIDER_iasl = "acpica"


LICENSE_FLAGS_ACCEPTED += " \
    commercial \
    commercial_gstreamer1.0-libav \
    commercial_ffmpeg \
    commercial_x264 \
    non-commercial \
    synaptics-killswitch \
"

# Avoid to duplicate the rootfs tarball by generating both tar.gz/tar.xz
IMAGE_FSTYPES:remove = "tar.gz"

# By default, meta-ti and TI SDK ship tar.xz rootfs tarball for SD card deployment
# fastboot is preferred for deployment in automation
IMAGE_FSTYPES:append:omap-a15 = " ext4.gz"
IMAGE_FSTYPES:append:beaglebone = " ext4.gz"

INHERIT += "buildhistory"
INHERIT += "image-buildinfo"
BUILDHISTORY_COMMIT = "1"

# Enable support for automatically running a local hash equivalence server and
# instruct bitbake to use a hash equivalence aware signature generator. Hash
# equivalence improves reuse of sstate by detecting when a given sstate
# artifact can be reused as equivalent, even if the current task hash doesn't
# match the one that generated the artifact.
#
# A shared hash equivalent server can be set with "<HOSTNAME>:<PORT>" format
#
BB_SIGNATURE_HANDLER ?= "OEEquivHash"
BB_HASHSERVE ??= "auto"