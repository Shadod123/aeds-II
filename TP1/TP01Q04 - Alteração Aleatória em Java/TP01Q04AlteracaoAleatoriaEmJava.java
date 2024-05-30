/*----------------------------------------------------------------
Atividade: TP01Q04 - Alteracao Aleatoria em Java
------------------------------------------------------------------
Aluno: Caio Eduardo Ramos Araes
------------------------------------------------------------------
Matricula: 738811
----------------------------------------------------------------*/
import java.util.Random;

class TP01Q04AlteracaoAleatoriaEmJava {
   public static boolean isFim(String s){
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }

   public static String alteracaoAleatoriaLinha (String s){
      String s_mod = "";
      int l1, l2;

      //Gera, aleatoriamente, as duas letras a serem trocadas entre si
      Random gerador1 = new Random( );
      gerador1.setSeed(4);
      
      l1 = 'a' + (Math.abs(gerador1.nextInt( )) % 26);
      
	   //Monta a versao modificada da string recebida, trocando as letras geradas
	   for(int i = 0; i < s.length(); i++){
         if(s.charAt(i) == l1){
            l2 = 'a' + (Math.abs(gerador1.nextInt( )) % 26);
            s_mod = s_mod + (char) l2;
         } else{
            s_mod = s_mod + (char) s.charAt(i);
         }
	   }
   
      return s_mod;
   }

   public static void main (String[] args){
      String[] entrada = new String[1000];
      int numEntrada = 0;

      //Leitura da entrada padrao
      do {
         entrada[numEntrada] = MyIO.readLine();
      } while (isFim(entrada[numEntrada++]) == false);
      numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

      //Para cada linha de entrada, gerando uma saida contendo a linha de entrada modificada segundo
      //o processo de alteracao aleatoria
      for(int i = 0; i < numEntrada; i++){
         MyIO.println(alteracaoAleatoriaLinha (entrada[i]));
      }
   }
}

