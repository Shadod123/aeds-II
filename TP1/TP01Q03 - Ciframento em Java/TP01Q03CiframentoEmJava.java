/*----------------------------------------------------------------
Atividade: TP01Q03 - Ciframento em Java
------------------------------------------------------------------
Aluno: Caio Eduardo Ramos Araes
------------------------------------------------------------------
Matricula: 738811
----------------------------------------------------------------*/

class TP01Q03CiframentoEmJava {
   public static boolean isFim(String s){
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }

   public static String codificaLinha (String s){
      String s_cod = "";
      int c_cod = 0;
	  
	   //Monta a versao codificada da string recebeida ao copiar cada caractere somando 3 ao seu codigo
	   for(int i = 0; i < s.length(); i++){
         c_cod = s.charAt(i) + 3;
         s_cod = s_cod + (char) c_cod;
	   }
   
      return s_cod;
   }

   public static void main (String[] args){
      String[] entrada = new String[1000];
      int numEntrada = 0;

      //Leitura da entrada padrao
      do {
         entrada[numEntrada] = MyIO.readLine();
      } while (isFim(entrada[numEntrada++]) == false);
      numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

      //Para cada linha de entrada, gerando uma saida contendo a linha de entrada codificada
      for(int i = 0; i < numEntrada; i++){
         MyIO.println(codificaLinha (entrada[i]));
      }
   }
}

