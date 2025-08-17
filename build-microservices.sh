#!/bin/bash

MICROSERVICES_DIR="/home/kamhassan/Desktop/Projects/SmartPOS/micro-services"
SERVICES=(
    "config-server"
    "eureka-server"
    "api-gateway"
    "inventory-service"
    "product-service"
    "payment-service"
    # Add other services here if needed
    "order-service"
)

echo "Choose an option:"
echo "1) Start all micro-services"
echo "2) Stop all micro-services"
read -rp "Enter 1 or 2: " option

cd "$MICROSERVICES_DIR" || { echo "Directory not found: $MICROSERVICES_DIR"; exit 1; }

if [[ "$option" == "1" ]]; then
    for service in "${SERVICES[@]}"; do
        echo "Building $service (skipping tests)..."
        cd "$service" || { echo "Service directory not found: $service"; exit 1; }
        mvn clean install -DskipTests
        echo "Starting $service..."
        nohup mvn spring-boot:run > "../${service}_run.log" 2>&1 &
        cd ..
    done
    echo "All micro-services started."
elif [[ "$option" == "2" ]]; then
    for service in "${SERVICES[@]}"; do
        # Find and kill the spring-boot:run process for each service
        pid=$(pgrep -f "spring-boot:run.*$service")
        if [[ -n "$pid" ]]; then
            echo "Stopping $service (PID: $pid)..."
            kill "$pid"
        else
            echo "$service is not running."
        fi
    done
    echo "All micro-services stopped."
else
    echo "Invalid option. Aborted."
    exit 1
fi