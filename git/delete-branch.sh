#!/bin/bash

$branch="$1"

if [ -z "$branch" ]; then
    echo 'ERROR: branch name was not given'
    exit 1
fi

echo '**** Deleting branch locally'
git branch -d $branch

echo '**** Deleting branch remotely'
git push origin :$branch
