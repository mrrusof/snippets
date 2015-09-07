#!/bin/bash

gradle assemble
jdb -sourcepath src/main/java/ -classpath build/classes/main com.example.Buggy
