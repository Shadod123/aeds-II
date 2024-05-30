/*----------------------------------------------------------------
Atividade: TP01Q01 - Palindromo em Java
------------------------------------------------------------------
Aluno: Caio Eduardo Ramos Araes
------------------------------------------------------------------
Matricula: 738811
----------------------------------------------------------------*/

class TP01Q01PalindromoEmJava {
   public static boolean isFim(String s){
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }

   public static boolean identificaPalindromo (String s){
     boolean resp = true;
	  int half = (s.length()-1) / 2;
	  
	  //Percorre a string recebida ate sua metade, comparando a primeira posicao com a ultima, a segunda com a penultima, etc
	  for(int i = 0; i <= half; i++){
		   if(s.charAt(i) != s.charAt((s.length()-1)-i)){
			resp = false;
		   }
	  }
   
      return resp;
   }

   public static void main (String[] args){
      String[] entrada = new String[1000];
      int numEntrada = 0;

      //Leitura da entrada padrao
      do {
         entrada[numEntrada] = MyIO.readLine();
      } while (isFim(entrada[numEntrada++]) == false);
      numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

      //Para cada linha de entrada, gerando uma saida contendo "SIM/NAO" dependendo de se a frase for palindromo
      for(int i = 0; i < numEntrada; i++){
         if(identificaPalindromo(entrada[i])){
           MyIO.println("SIM"); 
         }
         else{
           MyIO.println("NAO");
         }
      }
   }
}

