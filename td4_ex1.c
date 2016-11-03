#include <stdlib.h>
#include <stdio.h>
#include <stdarg.h>

void CatStrings(char *str1, ...)
{
  char * s;
  va_list l;
  va_start(l,str1); //init
  printf("%s", str1);
  while((s = va_arg(l,char *)) != NULL)
    printf("%s", s);

}
void AfficherEntier(int a)
{
  printf("%d", a);
}
void Printf(char *str, ...)
{
  va_list l;
  va_start(l, str);
  int format = 0;
  for( int i = 0; str[i] != '\0'; i++)
   {
      if(str[i] == '%')
	format = 1;
      else if(format == 1)
      {
	switch(str[i])
	  {
	  case 'd':
	    AfficherEntier((va_arg(l, int *)));
	    break;


	  }
      format = 0;

      }
      else if ((str[i]!= '%'))
      {
	putchar(str[i]);
      }
   }

}
int main()
{
  int test = 5;
  int * a = &test;
  Printf("test %d dgd", 5);
  printf("\%");
  printf("%d", test(5,int));
  return 0;
  
}
type test(void * a, type)
{
  return (int *) *a;
}
template <class T>
T Min(T x, T y)
{
    return x<y ? x : y;
}
