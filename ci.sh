#!/bin/sh

set -ex

#run tests - Roboelectric with no avd
#./gradlew clean localTest --stacktrace --debug


echo no | android create avd --force -n test -t android-21 --abi armeabi-v7a
emulator -avd test -no-skin -no-audio -no-window &
./android-wait-for-emulator # see https://github.com/gildegoma/chef-android-sdk/blob/master/files/default/android-wait-for-emulator
adb shell input keyevent 82 &



#run tests
./gradlew connectedAndroidTest
