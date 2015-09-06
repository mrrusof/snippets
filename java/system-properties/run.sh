#!/bin/bash

gradle assemble
echo The following is a run of example w/o debug flag.
java -cp build/classes/main com.example.SystemProperties
echo The following is a run of example with debug flag.
java -cp build/classes/main -Ddebug.greeting com.example.SystemProperties
