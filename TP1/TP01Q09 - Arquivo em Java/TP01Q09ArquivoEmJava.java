/*----------------------------------------------------------------
Atividade: TP01Q09 - Arquivo em Java
------------------------------------------------------------------
Aluno: Caio Eduardo Ramos Araes
------------------------------------------------------------------
Matricula: 738811
----------------------------------------------------------------*/

class TP01Q09ArquivoEmJava {

   public static void readFileBackwards (String fileName, int n, int init_n){
     double real = 0.0;
     
     //Abre o arquivo somente antes da funcao entrar em recursao
     if(n == init_n){
         Arq.openRead(fileName);
     }     

     if(n > 0){
         real = Arq.readDouble();
         readFileBackwards(fileName,n-1,init_n);
         //Se o numero lido como real for na verdade inteiro, imprimir com type casting para int
         if(real == (int) real){
            MyIO.println((int) real);
         }
         else{
            MyIO.println(real);
         }    
     }

     //Fecha o arquivo somente apos todas as chamadas recursivas
     if(n == init_n){
         Arq.close();
     } 
   }

   public static void main (String[] args){
      int qtd = 0;
      double entrada = 0.0;
      Arq.openWrite("arquivo.txt");

      //Leitura de entrada padrao
      qtd = MyIO.readInt();
      for(int i = 0; i < qtd; i++){
         entrada = MyIO.readDouble();
         Arq.println(entrada);
      }

      Arq.close();

      readFileBackwards("arquivo.txt",qtd,qtd);
   }
}

