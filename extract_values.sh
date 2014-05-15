#!/bin/sh

URL="https://fortawesome.github.io/Font-Awesome/cheatsheet/"
PATTERN_VALUE="&#x([0-9a-zA-Z]+)"
PATTERN_KEY="(fa-[a-zA-Z0-9_-]+)"

curl -silent "$URL" | sed -En "
	/$PATTERN_VALUE/ {
		N
		/$PATTERN_KEY/ {
			s/.*$PATTERN_VALUE.*\n.*$PATTERN_KEY.*/\2('\\\u\1'),/
			s/-/_/g
			p
		}
	}" | sort
