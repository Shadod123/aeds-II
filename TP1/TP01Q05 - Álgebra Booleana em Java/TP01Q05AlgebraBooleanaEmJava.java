/*----------------------------------------------------------------
Atividade: TP01Q05 - Algebra Booleana em Java
------------------------------------------------------------------
Aluno: Caio Eduardo Ramos Araes
------------------------------------------------------------------
Matricula: 738811
----------------------------------------------------------------*/

class TP01Q05AlgebraBooleanaEmJava {
   public static boolean isFim(int x){
      return (x == 0);
   }

   public static String stringTrim (String expressao){
      String resp = "";

	   //Reconstroi a string recebida, removendo os espacos em branco
	   for(int i = 0; i < expressao.length(); i++){
		   if(expressao.charAt(i) != ' '){
            resp = resp + (char) expressao.charAt(i);
		   }
	   }
    
      return resp;
   }

   public static String replaceLetters (String expressao, int[] entradas){
      String resp = "";

	   //Percorre a string recebida, substituindo as letras A, B, C e D pelos valores binarios correspondentes
	   for(int i = 0; i < expressao.length(); i++){
		   if(expressao.charAt(i) == 'A'){
            if(entradas[0] == 0){
               resp = resp + '0';
            }
            else{
               resp = resp + '1';
            }      
		   }
         else if(expressao.charAt(i) == 'B'){
            if(entradas[1] == 0){
               resp = resp + '0';
            }
            else{
               resp = resp + '1';
            }      
		   }
         else if(expressao.charAt(i) == 'C'){
            if(entradas[2] == 0){
               resp = resp + '0';
            }
            else{
               resp = resp + '1';
            }      
		   }
         else{
            resp = resp + (char) expressao.charAt(i);
         }
	   }
    
      return resp;
   }

   public static String replaceLogicalOps (String expressao){
      String resp = "";
      int i = 0;

	   //Percorre a string recebida, resolvendo as operacoes logicas
	   while(i < expressao.length()){
         //not(0) = 1
         if(expressao.charAt(i) == 'n' && expressao.charAt(i+4) == '0' && expressao.charAt(i+5) == ')'){
            resp = resp + '1';
            i += 5; //pula para a proxima operacao logica a ser resolvida
         }
         //not(1) = 0
         else if(expressao.charAt(i) == 'n' && expressao.charAt(i+4) == '1' && (expressao.charAt(i+5) == ')')){
            resp = resp + '0';
            i += 5; //pula para a proxima operacao logica a ser resolvida
         }
         else{
            //and(0,0) ou and(0,1) ou and(1,0) = 0
		      if(expressao.charAt(i) == 'a' && 
              (expressao.charAt(i+4) == '0' || expressao.charAt(i+6) == '0') &&
              (expressao.charAt(i+5) == ',') && (expressao.charAt(i+7) == ')')){
               resp = resp + '0';
               i += 7; //pula para a proxima operacao logica a ser resolvida      
		      }
            //and(1,1) = 1
            else if(expressao.charAt(i) == 'a' && 
                   (expressao.charAt(i+4) == '1' && expressao.charAt(i+6) == '1') &&
                   (expressao.charAt(i+5) == ',') && (expressao.charAt(i+7) == ')')){
               resp = resp + '1';
               i += 7; //pula para a proxima operacao logica a ser resolvida
		      }
            //or(0,1) ou or(1,0) ou or(1,1) = 1
            else if(expressao.charAt(i) == 'o' && 
                   (expressao.charAt(i+3) == '1' || expressao.charAt(i+5) == '1') &&
                   (expressao.charAt(i+4) == ',') && (expressao.charAt(i+6) == ')')){
               resp = resp + '1';
               i += 6; //pula para a proxima operacao logica a ser resolvida
		      }
            //or(0,0) = 0
            else if(expressao.charAt(i) == 'o' && 
                   (expressao.charAt(i+3) == '0' && expressao.charAt(i+5) == '0') &&
                   (expressao.charAt(i+4) == ',') && (expressao.charAt(i+6) == ')')){
               resp = resp + '0';
               i += 6; //pula para a proxima operacao logica a ser resolvida
		      }

            //and(0,0,0) ate' and(1,1,0) = 0
		      else if(expressao.charAt(i) == 'a' && 
                   (expressao.charAt(i+4) == '0' || expressao.charAt(i+6) == '0' || expressao.charAt(i+8) == '0') &&
                   (expressao.charAt(i+5) == ',' && expressao.charAt(i+7) == ',') && (expressao.charAt(i+9) == ')')){
               resp = resp + '0';
               i += 9; //pula para a proxima operacao logica a ser resolvida    
		      }
            //and(1,1,1) = 1
            else if(expressao.charAt(i) == 'a' && 
                   (expressao.charAt(i+4) == '1' && expressao.charAt(i+6) == '1' && expressao.charAt(i+8) == '1') &&
                   (expressao.charAt(i+5) == ',' && expressao.charAt(i+7) == ',') && (expressao.charAt(i+9) == ')')){
               resp = resp + '1';
               i += 9; //pula para a proxima operacao logica a ser resolvida
		      }
            //or(0,0,1) ate' or(1,1,1) = 1
            else if(expressao.charAt(i) == 'o' && 
                   (expressao.charAt(i+3) == '1' || expressao.charAt(i+5) == '1' || expressao.charAt(i+7) == '1') &&
                   (expressao.charAt(i+4) == ',' && expressao.charAt(i+6) == ',') && (expressao.charAt(i+8) == ')')){
               resp = resp + '1';
               i += 8; //pula para a proxima operacao logica a ser resolvida           
		      }
            //or(0,0,0) = 0
            else if(expressao.charAt(i) == 'o' && 
                   (expressao.charAt(i+3) == '0' && expressao.charAt(i+5) == '0' && expressao.charAt(i+7) == '0') &&
                   (expressao.charAt(i+4) == ',' && expressao.charAt(i+6) == ',') && (expressao.charAt(i+8) == ')')){
               resp = resp + '0';
               i += 8; //pula para a proxima operacao logica a ser resolvida
		      }

            //and(0,0,0,0) ate' and(1,1,1,0) = 0
		      else if(expressao.charAt(i) == 'a' && 
                   (expressao.charAt(i+4) == '0' || expressao.charAt(i+6) == '0' || expressao.charAt(i+8) == '0' || expressao.charAt(i+10) == '0') &&
                   (expressao.charAt(i+5) == ',' && expressao.charAt(i+7) == ',' && expressao.charAt(i+9) == ',') && (expressao.charAt(i+11) == ')')){
               resp = resp + '0';
               i += 11; //pula para a proxima operacao logica a ser resolvida    
		      }
            //and(1,1,1,1) = 1
            else if(expressao.charAt(i) == 'a' && 
                   (expressao.charAt(i+4) == '1' && expressao.charAt(i+6) == '1' && expressao.charAt(i+8) == '1' && expressao.charAt(i+10) == '1') &&
                   (expressao.charAt(i+5) == ',' && expressao.charAt(i+7) == ',' && expressao.charAt(i+9) == ',') && (expressao.charAt(i+11) == ')')){
               resp = resp + '1';
               i += 11; //pula para a proxima operacao logica a ser resolvida
		      }
            //or(0,0,0,1) ate' or(1,1,1,1) = 1
            else if(expressao.charAt(i) == 'o' && 
                   (expressao.charAt(i+3) == '1' || expressao.charAt(i+5) == '1' || expressao.charAt(i+7) == '1' || expressao.charAt(i+9) == '1') &&
                   (expressao.charAt(i+4) == ',' && expressao.charAt(i+6) == ',' && expressao.charAt(i+8) == ',') && (expressao.charAt(i+10) == ')')){
               resp = resp + '1';
               i += 10; //pula para a proxima operacao logica a ser resolvida           
		      }
            //or(0,0,0,0) = 0
            else if(expressao.charAt(i) == 'o' && 
                   (expressao.charAt(i+3) == '0' && expressao.charAt(i+5) == '0' && expressao.charAt(i+7) == '0' && expressao.charAt(i+9) == '0') &&
                   (expressao.charAt(i+4) == ',' && expressao.charAt(i+6) == ',' && expressao.charAt(i+8) == ',') && (expressao.charAt(i+10) == ')')){
               resp = resp + '0';
               i += 10; //pula para a proxima operacao logica a ser resolvida
		      }
            else{
               resp = resp + (char) expressao.charAt(i);
            }
         }       
         i++;
	   }
    
      return resp;
   }

   public static boolean expressaoBooleana (int qtd, int[] entradas, String expressao){
      boolean resp = false;

      expressao = stringTrim(expressao);
      expressao = replaceLetters(expressao,entradas);
      while(expressao.charAt(0) != '0' && expressao.charAt(0) != '1'){
         expressao = replaceLogicalOps(expressao);
      }
      if(expressao.charAt(0) == '1'){
         resp = true;
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
            if(expressaoBooleana(qtd, entradas, expressao)){
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

