#!/bin/sh
set -e
FILE="word.tar.gz"
trap 'rm -f "$FILE"' EXIT
curl -L https://github.com/8ta4/word/releases/download/v0.1.5/word.tar.gz -o $FILE
echo "39dfc00f0c8f548224f82225ecb1743a8ae83f13dee8a0e41f7740f53c0438b2  $FILE" | shasum -a 256 -c
tar -xzf $FILE