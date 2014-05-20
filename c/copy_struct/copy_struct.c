#include <stdio.h>
#include <stdlib.h>

typedef struct {
  int a;
  short s[2];
} MSG;

int main() {
  // ... m = {4, 3, 0} <-- C89 initialization style
  MSG *mp, m = {4, 3, 2};
  printf("m.a      = %d\nm.s[0]   = %d\nm.s[1]   = %d\n\n", m.a, m.s[0], m.s[1]);
  mp = (MSG *) malloc(sizeof(MSG));
  printf("mp->a    = %d\nmp->s[0] = %d\nmp->s[1] = %d\n\n", mp->a, mp->s[0], mp->s[1]);
  mp->a = m.a;
  char *fp, *tp;
  for(fp = (char *)m.s, tp = (char *)mp->s; tp < (char *)(mp+1);)
        *tp++ = *fp++;
  printf("m.a      = %d\nm.s[0]   = %d\nm.s[1]   = %d\n\n", m.a, m.s[0], m.s[1]);
  printf("mp->a    = %d\nmp->s[0] = %d\nmp->s[1] = %d\n\n", mp->a, mp->s[0], mp->s[1]);
  free(mp);
  return 0;
}
