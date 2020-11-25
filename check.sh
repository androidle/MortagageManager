#!/bin/sh
set -e
./gradlew --stop
./gradlew spotlessApply
./gradlew clean jacocoTestReport