/*----------------------------------------------------------------
Atividade: TP01Q06 - Is em Java
------------------------------------------------------------------
Aluno: Caio Eduardo Ramos Araes
------------------------------------------------------------------
Matricula: 738811
----------------------------------------------------------------*/

class TP01Q06IsEmJava {
   public static boolean isFim(String s){
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }

   public static boolean isVogal (char c){
      return (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' ||
              c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
   }

   public static boolean isVogalOnly (String s){
      boolean resp = true;
      //Procura por tudo aquilo que nao for vogal na string recebida
      for(int i = 0; i < s.length(); i++){
         //Caractere qualquer que nao seja vogal
         if( ! isVogal(s.charAt(i)) ){
            resp = false; 
         }
      }
      return resp;
   }

   public static boolean isConsoanteOnly (String s){
      boolean resp = true;
      //Procura por tudo aquilo que nao for consoante na string recebida
      for(int i = 0; i < s.length(); i++){
         //Caractere qualquer que nao seja consoante
         if( ! ( !isVogal(s.charAt(i) ) && ( ( s.charAt(i) >= 'A' && s.charAt(i) <= 'Z' ) || ( s.charAt(i) >= 'a' && s.charAt(i) <= 'z' ) ) ) ){
            resp = false; 
         }
      }
      return resp;
   }

   public static boolean isInteiro (String s){
      boolean resp = true;
      //Procura por tudo aquilo que nao for um digito na string recebida
      for(int i = 0; i < s.length(); i++){
         if( ! ( s.charAt(i) >= '0' && s.charAt(i) <= '9' ) ){
            resp = false; 
         }
      }
      return resp;
   }

   public static boolean isReal (String s){
      boolean resp = true;
      int pontoDecimal = 0;
      //Procura por tudo aquilo que nao for um digito, ponto decimal ou virgula na string recebida
      for(int i = 0; i < s.length(); i++){
         if( s.charAt(i) == '.' || s.charAt(i) == ',' ){
            pontoDecimal++;
         }
         if( ! ( s.charAt(i) >= '0' && s.charAt(i) <= '9' || 
                 s.charAt(i) == '.' || s.charAt(i) == ',' ) ){
            resp = false; 
         }
      }
      //Nao ha' ou ha' mais de um ponto decimal ou virgula na string
      if(pontoDecimal != 1){
         resp = false;
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

      //Para cada linha de entrada, gerando uma saida contendo "SIM/NAO" como resposta aos testes
      for(int i = 0; i < numEntrada; i++){
         //Teste de vogal
         if(isVogalOnly(entrada[i])){
            MyIO.print("SIM "); 
         }
         else{
            MyIO.print("NAO ");
         }
         //Teste de consoante
         if(isConsoanteOnly(entrada[i])){
            MyIO.print("SIM "); 
         }
         else{
            MyIO.print("NAO ");
         }
         //Teste de inteiro
         if(isInteiro(entrada[i])){
            MyIO.print("SIM "); 
         }
         else{
            MyIO.print("NAO ");
         }
         //Teste de real
         if(isReal(entrada[i])){
            MyIO.print("SIM\n"); 
         }
         else{
            MyIO.print("NAO\n");

         }
      }
   }
}


