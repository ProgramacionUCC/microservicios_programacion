#!/bin/sh
exec "$(dirname "$0")/.tools/apache-maven-3.9.10/bin/mvn" "$@"
