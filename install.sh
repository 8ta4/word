#!/bin/sh
set -e
FILE="word.tar.gz"
trap 'rm -f "$FILE"' EXIT
curl -L https://github.com/8ta4/word/releases/download/v0.1.2/word.tar.gz -o $FILE
echo "545ef0d9adaccdd5e6932d6b103487db6a7b54f36634d679958a9f5e16081310  $FILE" | shasum -a 256 -c
tar -xzf $FILE