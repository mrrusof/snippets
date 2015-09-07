#!/bin/bash

gradle assemble
echo
echo Vanilla run
java -cp build/classes/main com.example.Assertions
echo
echo Run with argument 0 and assertions disabled
java -cp build/classes/main com.example.Assertions 0
echo
echo Run with argument 0 and assertions enabled
java -ea -cp build/classes/main com.example.Assertions 0
