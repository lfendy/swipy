#!/bin/sh

set -ex

#run tests
./gradlew clean testLocal
