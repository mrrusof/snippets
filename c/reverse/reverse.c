#include <stdio.h>

void reverse(char *s) {
  int l = 0;
  char swap;
  for (; *(s+l) != 0; l++);
  for(int i = 0; l > i; i++) {
    swap = *(s+(--l));
    *(s+l) = *(s+i);
    *(s+i) = swap;
  }
}

int main() {
  char mystring[] = "hola";
  printf("Original string: \"%s\"\n", mystring);
  reverse(mystring);
  printf("Reversed string: \"%s\"\n", mystring);
  return 0;
}
