#!/bin/sh

# Specify the folder path to be deleted
folderPath="/storage/emulated/0/UmraniHacksFiles.sh"

# Specify the file path to be created
filePath="/storage/emulated/0/UmraniHacksFiles"

# Delete the folder
rm -rf "$folderPath"

# Create a new file
touch "$filePath"
