@echo off
echo Building the project...
mvn clean package

echo Choose mode:
echo 1. Single Process (Single PID)
echo 2. Multi Process (Multiple PIDs)
set /p mode="Enter your choice (1/2): "

if "%mode%"=="1" (
    echo Running in single process mode...
    java -jar target/player-communication-1.0-SNAPSHOT.jar single
) else if "%mode%"=="2" (
    echo Running in multi process mode...
    java -jar target/player-communication-1.0-SNAPSHOT.jar multi
) else (
    echo Invalid choice. Exiting.
    exit /b 1
)