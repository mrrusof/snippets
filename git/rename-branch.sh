#!/bin/bash

$oldbranch="$1"
$newbranch="$2"

if [ -z "$oldbranch" ]; then
    echo 'ERROR: old branch was not given'
    exit 1
fi
if [ -z "$newbranch" ]; then
    echo 'ERROR: target branch was not given'
    exit 1
fi

echo '**** Renaming current newbranch locally'
git newbranch -m $oldbranch $newbranch

echo '**** Deleting old branch in origin'
git push origin :$oldbranch

echo '*** Pushing new branch into origin'
git push -u origin $newbranch 

