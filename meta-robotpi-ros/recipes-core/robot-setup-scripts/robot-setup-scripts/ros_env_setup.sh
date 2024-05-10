# -*-Shell-script-*-
#
# functions     This file sets env variables required by ROS 2
#

# ROS global env
export ROS_PROFILE_DIR=/etc/profile.d/ros
export ROS_DOMAIN_ID=30
source "$ROS_PROFILE_DIR/setup.bash"

# TURTLEBOT env
export LDS_MODEL=LDS-02
export TURTLEBOT3_MODEL=waffle_pi