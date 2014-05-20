#include <stdio.h>

void reverse(char *s) {
  int l = 0;
  for(; *(s+l) != 0; l++);
  char swap;
  for(int i = 0; i < l; i++) {
    swap = *(s+(--l));
    *(s+l) = *(s+i);
    *(s+i) = swap;
  }
}

void reverse_wo_swap(char *s) {
  int l = 0;
  for(; *(s+l) != 0; l++);
  for(int i = 0; i < l; i++) {
    *(s+i) = *(s+i) + *(s+(--l));
    *(s+l) = *(s+i) - *(s+l);
    *(s+i) = *(s+i) - *(s+l);
  }
}

int main() {
  char mystring[] = "hola";
  printf("Original string: \"%s\"\n", mystring);
  reverse(mystring);
  printf("Reversed string: \"%s\"\n", mystring);
  reverse_wo_swap(mystring);
  printf("Previous string reversed w/o swap: \"%s\"\n", mystring);
  return 0;
}
