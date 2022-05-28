#!/bin/bash

GENERATED_PATH=generated

protoc -I . --java_out=$GENERATED_PATH --plugin=protoc-gen-grpc-java=protoc-gen-grpc-java-1.45.1-linux-x86_64.exe --grpc-java_out=$GENERATED_PATH proto/*.proto