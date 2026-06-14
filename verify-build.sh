#!/bin/bash
# Verify build environment

echo "=== SecureApp Build Verification ==="
echo ""

echo "Checking Java..."
java -version 2>&1 | head -1

echo ""
echo "Checking Android SDK..."
if [ -z "$ANDROID_SDK_ROOT" ] && [ -z "$ANDROID_HOME" ]; then
    echo "WARNING: ANDROID_SDK_ROOT or ANDROID_HOME not set"
else
    echo "SDK found"
fi

echo ""
echo "Checking Gradle wrapper..."
if [ -f "gradlew" ]; then
    echo "gradlew found"
    chmod +x gradlew
else
    echo "WARNING: gradlew not found"
fi

echo ""
echo "Project structure:"
find . -maxdepth 3 -type f | grep -E "\.(java|xml|gradle)$" | head -20

echo ""
echo "Ready to build!"
echo "Run: ./gradlew assembleDebug"
