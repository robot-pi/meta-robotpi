FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "https://github.com/ocornut/imgui/archive/v1.79.tar.gz;protocol=https;name=imgui"
SRC_URI += "file://0001-Components-Overlay-search-OE-downloaded-imgui-direct.patch"

SRC_URI[imgui.sha256sum] = "f1908501f6dc6db8a4d572c29259847f6f882684b10488d3a8d2da31744cd0a4"