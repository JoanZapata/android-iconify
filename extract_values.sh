#!/bin/sh

URL="https://fortawesome.github.io/Font-Awesome/cheatsheet/"
PATTERN_VALUE="&#x([0-9a-zA-Z]+)"
PATTERN_KEY="(fa-[a-zA-Z0-9_-]+)"

curl -silent "$URL" | sed -En "
	# Look for the value pattern
	/$PATTERN_VALUE/ {
		# If found, read the next line
		N
		# Look for the key pattern
		/$PATTERN_KEY/ {
			# If found, print the formated key/value pair
			s/.*$PATTERN_VALUE.*\n.*$PATTERN_KEY.*/\2('\\\u\1'),/
			# Replace '-' by '_' in order to have proper enum name
			s/-/_/g
			# Only print these results
			p
		}
	}" | sort
