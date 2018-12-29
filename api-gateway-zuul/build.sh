#!/usr/bin/env bash

mvn clean package -Dmaven.test.skip=true

docker build -t api-gateway-zuul:latest .