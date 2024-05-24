#!/bin/sh

# Default interface name and IP address
INTERFACE=${1:-usb0}
IP_ADDRESS=${2:-169.254.1.100/16}
MAX_ATTEMPTS=${3:-8}
RETRY_INTERVAL="0.5"

if ip link show "$INTERFACE" > /dev/null 2>&1; then
    echo "$INTERFACE interface is available."
    INTERFACE_TYPE=$(cat /sys/class/net/"$INTERFACE"/type)
    echo "/sys/class/net/"$INTERFACE"/type: $INTERFACE_TYPE"

    if [[ $INTERFACE_TYPE -eq 1 ]]; then
        for ((i=1; i<=$MAX_ATTEMPTS; i++)); do
            if ip link show "$INTERFACE" | grep -q "UP"; then
                echo "$INTERFACE interface is up"
                break
            fi

            echo "$INTERFACE interface is down. Assign IP $IP_ADDRESS and bring it up"
            ip addr add "$IP_ADDRESS" dev "$INTERFACE"
            ip link set "$INTERFACE" up

            sleep $RETRY_INTERVAL
        done
    else
        echo "Not configure non-Ethernet type interface"
        break
    fi
else
    echo "$INTERFACE interface is not available"
fi
