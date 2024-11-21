#!/bin/bash

echo "Building the project..."
mvn clean package

echo "Choose mode:"
echo "1. Single Process (Single PID)"
echo "2. Multi Process (Multiple PIDs)"
read -p "Enter your choice (1/2): " mode

if [ "$mode" == "1" ]; then
    echo "Running in single process mode..."
    java -jar target/player-communication-1.0-SNAPSHOT.jar single
elif [ "$mode" == "2" ]; then
    echo "Running in multi process mode..."
    java -jar target/player-communication-1.0-SNAPSHOT.jar multi
else
    echo "Invalid choice. Exiting."
    exit 1
fi