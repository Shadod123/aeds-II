/*----------------------------------------------------------------
Atividade: TP01Q12 - RECURSIVO - Palindromo em C
------------------------------------------------------------------
Aluno: Caio Eduardo Ramos Araes
------------------------------------------------------------------
Matricula: 738811
----------------------------------------------------------------*/
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

   bool isFim(char s[]){
      return (strlen(s) == 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
   }

   bool identificaPalindromo (char s[], int n){
      bool resp = true;
	  int half = (strlen(s)-1) / 2;
	  
	  //Percorre a string recebida ate sua metade, comparando a primeira posicao com a ultima, a segunda com a penultima, etc
	  if(n >= half){
        resp = identificaPalindromo(s,n-1);   
		if(s[(strlen(s)-1)-n] != s[n]){
			resp = false;
		}
	  }
   
      return resp;
   }

   int main ( ){
      char entrada[100];

      //Leitura da entrada padrao
      do{
        fgets(entrada, 100, stdin);
        setbuf(stdin, NULL);
        sscanf(entrada, "%[^\n]", entrada);
        if (!isFim(entrada)){
            //Para cada linha de entrada, gerando uma saida contendo "SIM/NAO" dependendo de se a frase for palindromo
            if (identificaPalindromo(entrada,strlen(entrada)-1)){
                printf("SIM\n");
            } 
            else{
                printf("NAO\n");
            } 
        }              
      } while (!isFim(entrada));
   }