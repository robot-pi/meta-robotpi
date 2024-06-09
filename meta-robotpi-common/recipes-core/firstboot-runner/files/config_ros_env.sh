#!/bin/sh

ROOT_PROFILE="/home/root/.profile"

# load ROS env variables
echo 'source /etc/profile.d/ros/ros_env_setup.sh' >> $ROOT_PROFILE

# config udev rules required by turtlebot3 ros components
TURTLEBOT3_UDEV_SCRIPT="/usr/share/turtlebot3_bringup/script/create_udev_rules"
if [ -e "$TURTLEBOT3_UDEV_SCRIPT" ]; then
    sh "$TURTLEBOT3_UDEV_SCRIPT"
fi
