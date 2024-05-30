/*----------------------------------------------------------------
Atividade: TP01Q13 - RECURSIVO - Ciframento em Java
------------------------------------------------------------------
Aluno: Caio Eduardo Ramos Araes
------------------------------------------------------------------
Matricula: 738811
----------------------------------------------------------------*/

class TP01Q13RECURSIVOCiframentoEmJava {
   public static boolean isFim(String s){
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }

   public static String codificaLinha (String s, int n){
	   String s_cod = "";

      //Monta a versao codificada da string recebeida ao copiar cada caractere somando 3 ao seu codigo
      if(n >= 0){
         s_cod = codificaLinha(s,n-1);  
         s_cod = s_cod + (char) (s.charAt(n)+3);
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
         MyIO.println(codificaLinha (entrada[i],entrada[i].length()-1));
      }
   }
}
