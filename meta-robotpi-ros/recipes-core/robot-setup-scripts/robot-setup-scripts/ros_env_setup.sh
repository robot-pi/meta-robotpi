# -*-Shell-script-*-
#
# functions     This file sets env variables required by ROS 2
#

# ROS global env
export ROS_PROFILE_DIR=/etc/profile.d/ros
export ROS_DOMAIN_ID=30
source "$ROS_PROFILE_DIR/setup.bash"

# Colcon event handler blocklist
export COLCON_EXTENSION_BLOCKLIST=""
COLCON_EXTENSION_BLOCKLIST+="colcon_core.event_handler.desktop_notification:"
COLCON_EXTENSION_BLOCKLIST+="colcon_core.event_handler.terminal_title:"
COLCON_EXTENSION_BLOCKLIST+="colcon_core.event_handler.status"

# TURTLEBOT env
export LDS_MODEL=LDS-02
export TURTLEBOT3_MODEL=waffle_pi