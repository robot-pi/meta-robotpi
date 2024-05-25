#!/bin/sh

SYSTEM_SETUP_DIR="/etc/profile.d/system_setup"
FIRSTBOOT_SCRIPT_DIR="$SYSTEM_SETUP_DIR/firstboot_runner"
FIRSTBOOT_RUNNER_LIST_FILE="$FIRSTBOOT_SCRIPT_DIR/runner.list"
FIRSTBOOT_RUNNER_DONE_FILE="$FIRSTBOOT_SCRIPT_DIR/firstboot_runner_done"
FIRSTBOOT_RUNNER_SERVICE="firstboot-runner.service"
REBOOT_TIMEOUT="REBOOT_TIMEOUT_PLACEHOLDER"

if [ -f "$FIRSTBOOT_RUNNER_DONE_FILE" ]; then
    echo "Firstboot runner setup is completed. Exit"
    systemctl mask $FIRSTBOOT_RUNNER_SERVICE
    exit 0
fi

if [ -f "$FIRSTBOOT_RUNNER_LIST_FILE" ]; then
    while IFS= read -r runner_script_line; do
        echo "Read runner line: \"$runner_script_line\""
        runner_script=$(echo "$runner_script_line" | awk '{print $1}')
        if [ -z "$runner_script" ]; then
            echo "Skipping invalid runner line"
            continue
        fi

        runner_script_path="${FIRSTBOOT_SCRIPT_DIR}/${runner_script}"
        if [ -f "$runner_script_path" ] && [ -x "$runner_script_path" ]; then
            echo "Execute runner script: $runner_script"
            "$runner_script_path"
        else
            echo "Runner script $runner_script not found or not executable"
        fi
    done < "$FIRSTBOOT_RUNNER_LIST_FILE"
else
    echo "No firstboot runner script list is provided"
fi

echo "Mark completion and mask fristboot-runner service"
touch $FIRSTBOOT_RUNNER_DONE_FILE
systemctl mask $FIRSTBOOT_RUNNER_SERVICE

# reboot system automatically if we receive a positive integer timeout (cap by 10)
if [[ "$REBOOT_TIMEOUT" =~ ^[1-9][0-9]*$ ]]; then
    if [ "$REBOOT_TIMEOUT" -gt 10 ]; then
        REBOOT_TIMEOUT=10
    fi
    echo "Finished firstboot runner. Rebooting system in ${REBOOT_TIMEOUT}..."
    sleep $REBOOT_TIMEOUT
    reboot
else
    echo "Finished firstboot runner. Please reboot system to take effect"
fi
