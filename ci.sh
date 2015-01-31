#!/bin/sh

set -ex

#run tests
./gradlew clean localTest --stacktrace
