#!/bin/bash
# Script to download AAR files for offline building
# Run this if you don't have internet access during build

set -e

AAR_DIR="app/libs"
mkdir -p "$AAR_DIR"

echo "Downloading AAR dependencies..."

# Note: In production, download these from Maven Central or Google Maven
# URLs are provided for reference

# AndroidX Core
# https://maven.google.com/androidx/core/core/1.15.0/core-1.15.0.aar

# AppCompat
# https://maven.google.com/androidx/appcompat/appcompat/1.7.0/appcompat-1.7.0.aar

# ConstraintLayout
# https://maven.google.com/androidx/constraintlayout/constraintlayout/2.2.0/constraintlayout-2.2.0.aar

# Material Design
# https://maven.google.com/com/google/android/material/material/1.12.0/material-1.12.0.aar

# RecyclerView
# https://maven.google.com/androidx/recyclerview/recyclerview/1.4.0/recyclerview-1.4.0.aar

# CardView
# https://maven.google.com/androidx/cardview/cardview/1.0.0/cardview-1.0.0.aar

# Lifecycle Service
# https://maven.google.com/androidx/lifecycle/lifecycle-service/2.8.0/lifecycle-service-2.8.0.aar

# Multidex
# https://maven.google.com/androidx/multidex/multidex/2.0.1/multidex-2.0.1.aar

echo "Place downloaded .aar files in $AAR_DIR"
echo "Then run: ./gradlew assembleDebug"
