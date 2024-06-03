# Copyright (c) 2024 Robot Pi Inc.

SUMMARY = "UI core packages for Robot Pi OS"

# Need this because the recipe uses MACHINE overrides.
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup
inherit ros_distro_${ROS_DISTRO}

WEB_FRAMEWORK_PKGS ??= " \
    chromium-x11 \
"

RDEPENDS:${PN} = " \
    ${WEB_FRAMEWORK_PKGS} \
"