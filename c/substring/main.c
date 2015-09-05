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
  char *sub1 = "first";
  char *sub2 = "";
  char *sub3 = "fast";
  char *sub4 = "motor";
  char *sub5 = "tor.";
  char *sub6 = " ";
  char *text1 = "the first motor";
  char *text2 = "";
  printf("issub(\"%s\", \"%s\") = %s\n", sub1, text1, btos(issub(sub1, text1)));
  printf("issub(\"%s\", \"%s\") = %s\n", sub1, text2, btos(issub(sub1, text2)));
  printf("issub(\"%s\", \"%s\") = %s\n", sub2, text1, btos(issub(sub2, text1)));
  printf("issub(\"%s\", \"%s\") = %s\n", sub2, text2, btos(issub(sub2, text2)));
  printf("issub(\"%s\", \"%s\") = %s\n", sub3, text1, btos(issub(sub3, text1)));
  printf("issub(\"%s\", \"%s\") = %s\n", sub3, text2, btos(issub(sub3, text2)));
  printf("issub(\"%s\", \"%s\") = %s\n", sub4, text1, btos(issub(sub4, text1)));
  printf("issub(\"%s\", \"%s\") = %s\n", sub4, text2, btos(issub(sub4, text2)));
  printf("issub(\"%s\", \"%s\") = %s\n", sub5, text1, btos(issub(sub5, text1)));
  printf("issub(\"%s\", \"%s\") = %s\n", sub5, text2, btos(issub(sub5, text2)));
  printf("issub(\"%s\", \"%s\") = %s\n", sub6, text1, btos(issub(sub6, text1)));
  printf("issub(\"%s\", \"%s\") = %s\n", sub6, text2, btos(issub(sub6, text2)));
  return 0;
}
