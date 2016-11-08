#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>

int main(void)
{
  int sock;

  if ( (sock = socket( PF_INET, SOCK_STREAM, 0)) == -1)
       exit(-1);

  printf("Valeur : %d\n", sock);

  int test = 1;
  if ( setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &test, sizeof(int)) < 0)
    exit(-1);

  printf("Valeur : %d\n", sock);

  return 0;
}

    
