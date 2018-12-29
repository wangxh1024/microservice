#!/usr/bin/env bash

mvn clean package -Dmaven.test.skip=true

docker build -t course-dubbo-service:latest .