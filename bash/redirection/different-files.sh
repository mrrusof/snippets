#!/bin/bash

# Redirect stdout to stdout.log and stderr to stderr.log
(echo 'this is ok'; echo 'this is an error' 1>&2) 2> >(tee stderr.log) > >(tee stdout.log)
