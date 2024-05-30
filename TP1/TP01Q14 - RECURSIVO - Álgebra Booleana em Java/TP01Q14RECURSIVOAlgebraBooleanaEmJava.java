/*----------------------------------------------------------------
Atividade: TP01Q14 - RECURSIVO - Algebra Booleana em Java
------------------------------------------------------------------
Aluno: Caio Eduardo Ramos Araes
------------------------------------------------------------------
Matricula: 738811
----------------------------------------------------------------*/

class TP01Q14RECURSIVOAlgebraBooleanaEmJava {
   public static boolean isFim(int x){
      return (x == 0);
   }

   public static String stringTrim (String expressao, int n){
      String resp = "";

	   //Reconstroi a string recebida, removendo os espacos em branco
	   if(n >= 0){
         resp = stringTrim(expressao,n-1);
		   if(expressao.charAt(n) != ' '){
            resp = resp + (char) expressao.charAt(n);
		   }
	   }
    
      return resp;
   }

   public static String replaceLetters (String expressao, int[] entradas, int n){
      String resp = "";

	   //Percorre a string recebida, substituindo as letras A, B, C e D pelos valores binarios correspondentes
	   if(n >= 0){
         resp = replaceLetters(expressao,entradas,n-1);
		   if(expressao.charAt(n) == 'A'){
            if(entradas[0] == 0){
               resp = resp + '0';
            }
            else{
               resp = resp + '1';
            }      
		   }
         else if(expressao.charAt(n) == 'B'){
            if(entradas[1] == 0){
               resp = resp + '0';
            }
            else{
               resp = resp + '1';
            }      
		   }
         else if(expressao.charAt(n) == 'C'){
            if(entradas[2] == 0){
               resp = resp + '0';
            }
            else{
               resp = resp + '1';
            }      
		   }
         else{
            resp = resp + (char) expressao.charAt(n);
         }
	   }
    
      return resp;
   }

   public static String replaceLogicalOps (String expressao, int n){
      String resp = "";

	   //Percorre a string recebida, resolvendo as operacoes logicas
	   if(n < expressao.length()){
         //not(0) = 1
         if(expressao.length()-n >= 6 && expressao.charAt(n) == 'n' && expressao.charAt(n+4) == '0' && expressao.charAt(n+5) == ')'){
            resp = replaceLogicalOps(expressao,n+6); //pula para a proxima operacao logica a ser resolvida
            resp = resp + '1';
         }
         //not(1) = 0
         else if(expressao.length()-n >= 6 && expressao.charAt(n) == 'n' && expressao.charAt(n+4) == '1' && (expressao.charAt(n+5) == ')')){
            resp = replaceLogicalOps(expressao,n+6); //pula para a proxima operacao logica a ser resolvida
            resp = resp + '0';
         }
         else{
            //and(0,0) ou and(0,1) ou and(1,0) = 0
		      if(expressao.length()-n >= 8 && expressao.charAt(n) == 'a' && 
              (expressao.charAt(n+4) == '0' || expressao.charAt(n+6) == '0') &&
              (expressao.charAt(n+5) == ',') && (expressao.charAt(n+7) == ')')){ 
               resp = replaceLogicalOps(expressao,n+8); //pula para a proxima operacao logica a ser resolvida   
               resp = resp + '0'; 
            }
            //and(1,1) = 1
            else if(expressao.length()-n >= 8 && expressao.charAt(n) == 'a' && 
                   (expressao.charAt(n+4) == '1' && expressao.charAt(n+6) == '1') &&
                   (expressao.charAt(n+5) == ',') && (expressao.charAt(n+7) == ')')){  
               resp = replaceLogicalOps(expressao,n+8); //pula para a proxima operacao logica a ser resolvida
               resp = resp + '1';
            }
            //or(0,1) ou or(1,0) ou or(1,1) = 1
            else if(expressao.length()-n >= 7 && expressao.charAt(n) == 'o' && 
                   (expressao.charAt(n+3) == '1' || expressao.charAt(n+5) == '1') &&
                   (expressao.charAt(n+4) == ',') && (expressao.charAt(n+6) == ')')){
               resp = replaceLogicalOps(expressao,n+7); //pula para a proxima operacao logica a ser resolvida
               resp = resp + '1';
            }
            //or(0,0) = 0
            else if(expressao.length()-n >= 7 && expressao.charAt(n) == 'o' && 
                   (expressao.charAt(n+3) == '0' && expressao.charAt(n+5) == '0') &&
                   (expressao.charAt(n+4) == ',') && (expressao.charAt(n+6) == ')')){
               resp = replaceLogicalOps(expressao,n+7); //pula para a proxima operacao logica a ser resolvida
               resp = resp + '0';
            }

            //and(0,0,0) ate' and(1,1,0) = 0
		      else if(expressao.length()-n >= 10 && expressao.charAt(n) == 'a' && 
                   (expressao.charAt(n+4) == '0' || expressao.charAt(n+6) == '0' || expressao.charAt(n+8) == '0') &&
                   (expressao.charAt(n+5) == ',' && expressao.charAt(n+7) == ',') && (expressao.charAt(n+9) == ')')){
               resp = replaceLogicalOps(expressao,n+10); //pula para a proxima operacao logica a ser resolvida
               resp = resp + '0';
            }
            //and(1,1,1) = 1
            else if(expressao.length()-n >= 10 && expressao.charAt(n) == 'a' && 
                   (expressao.charAt(n+4) == '1' && expressao.charAt(n+6) == '1' && expressao.charAt(n+8) == '1') &&
                   (expressao.charAt(n+5) == ',' && expressao.charAt(n+7) == ',') && (expressao.charAt(n+9) == ')')){  
               resp = replaceLogicalOps(expressao,n+10); //pula para a proxima operacao logica a ser resolvida
               resp = resp + '1';
            }
            //or(0,0,1) ate' or(1,1,1) = 1
            else if(expressao.length()-n >= 9 && expressao.charAt(n) == 'o' && 
                   (expressao.charAt(n+3) == '1' || expressao.charAt(n+5) == '1' || expressao.charAt(n+7) == '1') &&
                   (expressao.charAt(n+4) == ',' && expressao.charAt(n+6) == ',') && (expressao.charAt(n+8) == ')')){
               resp = replaceLogicalOps(expressao,n+9); //pula para a proxima operacao logica a ser resolvida           
               resp = resp + '1';
            }
            //or(0,0,0) = 0
            else if(expressao.length() >= 9 && expressao.charAt(n) == 'o' && 
                   (expressao.charAt(n+3) == '0' && expressao.charAt(n+5) == '0' && expressao.charAt(n+7) == '0') &&
                   (expressao.charAt(n+4) == ',' && expressao.charAt(n+6) == ',') && (expressao.charAt(n+8) == ')')){
               resp = replaceLogicalOps(expressao,n+9); //pula para a proxima operacao logica a ser resolvida
               resp = resp + '0';
            }

            //and(0,0,0,0) ate' and(1,1,1,0) = 0
		      else if(expressao.length()-n >= 12 && expressao.charAt(n) == 'a' && 
                   (expressao.charAt(n+4) == '0' || expressao.charAt(n+6) == '0' || expressao.charAt(n+8) == '0' || expressao.charAt(n+10) == '0') &&
                   (expressao.charAt(n+5) == ',' && expressao.charAt(n+7) == ',' && expressao.charAt(n+9) == ',') && (expressao.charAt(n+11) == ')')){
               resp = replaceLogicalOps(expressao,n+12); //pula para a proxima operacao logica a ser resolvida
               resp = resp + '0';
            }
            //and(1,1,1,1) = 1
            else if(expressao.length()-n >= 12 && expressao.charAt(n) == 'a' && 
                   (expressao.charAt(n+4) == '1' && expressao.charAt(n+6) == '1' && expressao.charAt(n+8) == '1' && expressao.charAt(n+10) == '1') &&
                   (expressao.charAt(n+5) == ',' && expressao.charAt(n+7) == ',' && expressao.charAt(n+9) == ',') && (expressao.charAt(n+11) == ')')){
               resp = replaceLogicalOps(expressao,n+12); //pula para a proxima operacao logica a ser resolvida
               resp = resp + '1';
            }
            //or(0,0,0,1) ate' or(1,1,1,1) = 1
            else if(expressao.length()-n >= 11 && expressao.charAt(n) == 'o' && 
                   (expressao.charAt(n+3) == '1' || expressao.charAt(n+5) == '1' || expressao.charAt(n+7) == '1' || expressao.charAt(n+9) == '1') &&
                   (expressao.charAt(n+4) == ',' && expressao.charAt(n+6) == ',' && expressao.charAt(n+8) == ',') && (expressao.charAt(n+10) == ')')){  
               resp = replaceLogicalOps(expressao,n+11); //pula para a proxima operacao logica a ser resolvida //pula para a proxima operacao logica a ser resolvida       
               resp = resp + '1';
            }
            //or(0,0,0,0) = 0
            else if(expressao.length()-n >= 11 && expressao.charAt(n) == 'o' && 
                   (expressao.charAt(n+3) == '0' && expressao.charAt(n+5) == '0' && expressao.charAt(n+7) == '0' && expressao.charAt(n+9) == '0') &&
                   (expressao.charAt(n+4) == ',' && expressao.charAt(n+6) == ',' && expressao.charAt(n+8) == ',') && (expressao.charAt(n+10) == ')')){
               resp = replaceLogicalOps(expressao,n+11); //pula para a proxima operacao logica a ser resolvida
               resp = resp + '0';
            }
            else{   
               resp = replaceLogicalOps(expressao,n+1);
               resp = resp + (char) expressao.charAt(n);   
            }
         }       
	   }
    
      return resp;
   }

   public static String reverseString (String expressao, int n){
      String resp = "";

	   //Reconstroi a string recebida, invertendo a ordem de seus caracteres
	   if(n >= 0){
         resp = reverseString(expressao,n-1);
         resp = resp + (char) expressao.charAt((expressao.length()-1)-n);
	   }
    
      return resp;
   }

   public static boolean expressaoBooleana (int[] entradas, String expressao, int n, int init_n){
      boolean resp = false;

      if(n == init_n){
         expressao = stringTrim(expressao,expressao.length()-1);
         expressao = replaceLetters(expressao,entradas,expressao.length()-1);
      }
      
      if(n > 1){
         expressao = replaceLogicalOps(expressao,0);
         //Inverte a string com as expressoes booleanos a cada simplificacao, ja que a logica de recursao constroi a resposta ao inverso
         expressao = reverseString(expressao,expressao.length()-1); 
         resp = expressaoBooleana(entradas,expressao,expressao.length(),init_n);
      }
         
      if(n == 1){
         if(expressao.charAt(0) == '1'){
            resp = true;
         }
      }
      
      return resp;
   }


   public static void main (String[] args){
      String expressao = "";
      int[] entradas = new int[100];
      int qtd = 0;

      do {
         //Nao fazer na primeira vez
         if(qtd > 0){
            //Leitura da entrada padrao
            for(int i = 0; i < qtd; i++){
               entradas[i] = MyIO.readInt();
            }
            expressao = MyIO.readLine();

            //Para cada linha de entrada, gerando uma saida contendo "1/0" dependendo do resultado da expressao booleana
            if(expressaoBooleana(entradas,expressao,expressao.length(),expressao.length())){
               MyIO.println("1"); 
            }
            else{
               MyIO.println("0");
            }
         }  
         qtd = MyIO.readInt();        
      } while (isFim(qtd) == false);  
   }
}