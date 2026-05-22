#!/bin/sh
set -e
FILE="word.tar.gz"
trap 'rm -f "$FILE"' EXIT
curl -L https://github.com/8ta4/word/releases/download/v0.1.6/word.tar.gz -o $FILE
echo "baa8419c148e452fee042fdf38d7da24779c8620dd2d3b14946a98b52abefac0  $FILE" | shasum -a 256 -c
tar -xzf $FILE