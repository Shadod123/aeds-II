/*----------------------------------------------------------------
Atividade: TP01Q02 - Palindromo em C
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

   bool identificaPalindromo (char s[]){
    bool resp = true;
	  int half = (strlen(s)-1) / 2;
	  
	  //Percorre a string recebida ate sua metade, comparando a primeira posicao com a ultima, a segunda com a penultima, etc
	  for(int i = 0; i <= half; i++){
		if(s[i] != s[(strlen(s)-1)-i]){
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
            if (identificaPalindromo(entrada)){
                printf("SIM\n");
            } 
            else{
                printf("NAO\n");
            } 
        }              
      } while (!isFim(entrada));
   }