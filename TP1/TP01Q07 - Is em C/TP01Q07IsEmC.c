/*----------------------------------------------------------------
Atividade: TP01Q07 - Is em C
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

   bool isVogal (char c){
      return (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' ||
              c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
   }

   bool isVogalOnly (char s[]){
      bool resp = true;
      //Procura por tudo aquilo que nao for vogal na string recebida
      for(int i = 0; i < strlen(s); i++){
         //Caractere qualquer que nao seja vogal
         if( ! isVogal(s[i]) ){
            resp = false; 
         }
      }
      return resp;
   }

   bool isConsoanteOnly (char s[]){
      bool resp = true;
      //Procura por tudo aquilo que nao for consoante na string recebida
      for(int i = 0; i < strlen(s); i++){
         //Caractere qualquer que nao seja consoante
         if( ! ( !isVogal(s[i] ) && ( ( s[i] >= 'A' && s[i] <= 'Z' ) || ( s[i] >= 'a' && s[i] <= 'z' ) ) ) ){
            resp = false; 
         }
      }
      return resp;
   }

   bool isInteiro (char s[]){
      bool resp = true;
      //Procura por tudo aquilo que nao for um digito na string recebida
      for(int i = 0; i < strlen(s); i++){
         if( ! ( s[i] >= '0' && s[i] <= '9' ) ){
            resp = false; 
         }
      }
      return resp;
   }

   bool isReal (char s[]){
      bool resp = true;
      int pontoDecimal = 0;
      //Procura por tudo aquilo que nao for um digito, ponto decimal ou virgula na string recebida
      for(int i = 0; i < strlen(s); i++){
         if( s[i] == '.' || s[i] == ',' ){
            pontoDecimal++;
         }
         if( ! ( s[i] >= '0' && s[i] <= '9' || 
                 s[i] == '.' || s[i] == ',' ) ){
            resp = false; 
         }
      }
      //Nao ha' ou ha' mais de um ponto decimal ou virgula na string
      if(pontoDecimal != 1){
         resp = false;
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
        //Para cada linha de entrada, gerando uma saida contendo "SIM/NAO" como resposta aos testes
        if (!isFim(entrada)){
            //Teste de vogal
            if(isVogalOnly(entrada)){
               printf("SIM "); 
            }
            else{
               printf("NAO ");
            }
            //Teste de consoante
            if(isConsoanteOnly(entrada)){
               printf("SIM "); 
            }
            else{
               printf("NAO ");
            }
            //Teste de inteiro
            if(isInteiro(entrada)){
               printf("SIM "); 
            }
            else{
               printf("NAO ");
            }
            //Teste de real
            if(isReal(entrada)){
               printf("SIM\n"); 
            }
            else{
               printf("NAO\n");
    
            }
        }              
      } while (!isFim(entrada));
   }