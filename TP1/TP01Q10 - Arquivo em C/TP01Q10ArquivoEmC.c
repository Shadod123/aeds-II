/*----------------------------------------------------------------
Atividade: TP01Q10 - Arquivo em C
------------------------------------------------------------------
Aluno: Caio Eduardo Ramos Araes
------------------------------------------------------------------
Matricula: 738811
----------------------------------------------------------------*/
#include <stdio.h>
#include <stdlib.h>

    void readFileBackwards (char* fileName, FILE* fp, int n){
     double real = 0.0;   

     if(n > 0){
         fscanf(fp, "%lf", &real);
         readFileBackwards(fileName,fp,n-1);
         //Se o numero lido como real for na verdade inteiro, imprimir com %d e type casting
         if(real == (int) real){
            printf("%d\n", (int) real);
         }
         else{
            printf("%g\n", real);
         }    
     }
   }

    int main ( ){
      int qtd = 0;
      double entrada = 0.0;
      char* fileName = "arquivo.txt";
    
      //Abrir arquivo para gravacao 
      FILE* fp = fopen (fileName, "w");
      //Leitura de entrada padrao
      scanf("%d", &qtd);
      for(int i = 0; i < qtd; i++){
         scanf("%lf", &entrada);
         fprintf(fp, "%f\n", entrada);
      }
      fclose(fp);

      //Leitura dos numeros reais arquivo
      fp = fopen (fileName, "r");
      readFileBackwards(fileName,fp,qtd);
      fclose(fp);
   }