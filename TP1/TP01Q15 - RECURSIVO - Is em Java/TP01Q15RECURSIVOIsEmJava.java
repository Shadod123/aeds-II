/*----------------------------------------------------------------
Atividade: TP01Q15 - RECURSIVO - Is em Java
------------------------------------------------------------------
Aluno: Caio Eduardo Ramos Araes
------------------------------------------------------------------
Matricula: 738811
----------------------------------------------------------------*/

class TP01Q15RECURSIVOIsEmJava {
   public static boolean isFim(String s){
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }

   public static boolean isVogal (char c){
      return (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' ||
              c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
   }

   public static boolean isVogalOnly (String s, int n){
      boolean resp = true;
      //Procura por tudo aquilo que nao for vogal na string recebida
      if(n >= 0){
         resp = isVogalOnly(s,n-1);
         //Caractere qualquer que nao seja vogal
         if( ! isVogal(s.charAt(n)) ){
            resp = false; 
         }
      }
      return resp;
   }

   public static boolean isConsoanteOnly (String s, int n){
      boolean resp = true;
      //Procura por tudo aquilo que nao for consoante na string recebida
      if(n >= 0){
         resp = isConsoanteOnly(s,n-1);
         //Caractere qualquer que nao seja consoante
         if( ! ( !isVogal(s.charAt(n)) && ( ( s.charAt(n) >= 'A' && s.charAt(n) <= 'Z' ) || ( s.charAt(n) >= 'a' && s.charAt(n) <= 'z' ) ) ) ){
            resp = false; 
         }
      }
      return resp;
   }

   public static boolean isInteiro (String s, int n){
      boolean resp = true;
      //Procura por tudo aquilo que nao for um digito na string recebida
      if(n >= 0){
         resp = isInteiro(s,n-1);
         if( ! ( s.charAt(n) >= '0' && s.charAt(n) <= '9' ) ){
            resp = false; 
         }
      }
      return resp;
   }

   public static int hasPontoDecimal (String s, int n){
      int pontoDecimal = 0;
      //Procura por ponto decimal ou virgula na string recebida
      if(n >= 0){
         pontoDecimal += hasPontoDecimal(s,n-1);
         if( s.charAt(n) == '.' || s.charAt(n) == ',' ){
            pontoDecimal++;
         }
      }
      return pontoDecimal;
   }

   public static boolean isReal (String s, int n){ //separar essa funcao em duas funcoes recursivas isInteiro isPontoDecimal
      boolean resp = true;
      //Procura por tudo aquilo que nao for um digito, ponto decimal ou virgula na string recebida
      if(n >= 0){
         resp = isReal (s,n-1);
         if( ! ( s.charAt(n) >= '0' && s.charAt(n) <= '9' || 
                 s.charAt(n) == '.' || s.charAt(n) == ',' ) ){
            resp = false; 
         }
      }
      //Confirma se a string tem 1 ponto decimal ou virgula antes de sair de vez da funcao
      if(n == s.length()){
         if(hasPontoDecimal(s,s.length()) != 1){
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

      //Para cada linha de entrada, gerando uma saida contendo "SIM/NAO" como resposta aos testes
      for(int i = 0; i < numEntrada; i++){
         //Teste de vogal
         if(isVogalOnly(entrada[i],entrada[i].length()-1)){
            MyIO.print("SIM "); 
         }
         else{
            MyIO.print("NAO ");
         }
         //Teste de consoante
         if(isConsoanteOnly(entrada[i],entrada[i].length()-1)){
            MyIO.print("SIM "); 
         }
         else{
            MyIO.print("NAO ");
         }
         //Teste de inteiro
         if(isInteiro(entrada[i],entrada[i].length()-1)){
            MyIO.print("SIM "); 
         }
         else{
            MyIO.print("NAO ");
         }
         //Teste de real
         if(isReal(entrada[i],entrada[i].length()-1)){
            MyIO.print("SIM\n"); 
         }
         else{
            MyIO.print("NAO\n");

         }
      }
   }
}

