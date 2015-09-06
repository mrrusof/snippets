#!/bin/bash

gradle assemble
echo
echo The following are the original strings that made it into the class.
strings build/classes/main/com/example/ConditionalCompilation.class | grep -e Hello -e Today
