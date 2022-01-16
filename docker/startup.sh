#!/bin/bash

docker stop axon-server
docker rm -f jaeger booking auditor
docker-compose up -d --build
