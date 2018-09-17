#!/bin/bash

echo "1 : Building the project with maven"

mvn clean package

echo "2 : Building has been finished successfully"

cd target/

echo "3 : Executing jar"

java -jar logging-event-service-1.0.0.jar &

echo "4 : Logging Event Procedure has been stated"
