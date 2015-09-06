#include <stdio.h>
#include <stdbool.h>

#define btos(b) (b ? "true" : "false")

bool issub(char *sub, char * text) {
  if(sub[0] == 0) return true;
  int i = 0, j;
  while(text[i]) {
    j = 0;
    while(text[i+j] && text[i+j] == sub[j]) j++;
    if(sub[j] == 0) return true;
    i++;
  }
  return false;
}

int main() {
  char *sub[] = { "", " ", "the", "first", "motor", "fast", "tor." };
  char *text[] = { "the first motor", "" };
  for(int i = 0; i<6; i++)
    for(int j = 0; j<2; j++)
      printf("issub(\"%s\", \"%s\") = %s\n", sub[i], text[j], btos(issub(sub[i], text[j])));
  return 0;
}
