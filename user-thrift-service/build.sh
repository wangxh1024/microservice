#!/usr/bin/env bash

mvn clean package

docker build -t user-thrift-service:latest .