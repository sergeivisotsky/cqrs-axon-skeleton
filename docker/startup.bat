@echo off

call docker stop axon-server
call docker rm -f jaeger booking auditor
call docker-compose up -d --build
