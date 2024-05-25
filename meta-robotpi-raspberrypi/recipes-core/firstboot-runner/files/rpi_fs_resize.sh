#!/bin/sh

# Root partition information
# Reference: https://github.com/RPi-Distro/raspi-config/blob/master/usr/lib/raspi-config/init_resize.sh
ROOT_PART_DEV=$(findmnt / -o source -n)
ROOT_PART_NAME=$(echo "$ROOT_PART_DEV" | cut -d "/" -f 3)
ROOT_DEV_NAME=$(echo /sys/block/*/"${ROOT_PART_NAME}" | cut -d "/" -f 4)
ROOT_DEV="/dev/${ROOT_DEV_NAME}"
ROOT_PART_NUM=$(cat "/sys/block/${ROOT_DEV_NAME}/${ROOT_PART_NAME}/partition")

echo "Starting root filesystem resize..."
# Re-partition
parted -s -a opt $ROOT_DEV "resizepart ${ROOT_PART_NUM} 100%"
# Resize root filesystem
resize2fs $ROOT_PART_DEV
echo "Finished root filesystem resize"