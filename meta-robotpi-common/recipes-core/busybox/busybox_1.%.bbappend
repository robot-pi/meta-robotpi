# require busybox_1.%.bbappend from meta-rpb
SRC_URI:append:robotpi = " file://enable-setsid-tty.cfg file://enable-install.cfg"
