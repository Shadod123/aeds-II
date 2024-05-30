/*----------------------------------------------------------------
Atividade: TP01Q08 - Leitura de Pagina HTML em Java
------------------------------------------------------------------
Aluno: Caio Eduardo Ramos Araes
------------------------------------------------------------------
Matricula: 738811
----------------------------------------------------------------*/
import java.io.*;
import java.net.*;

class TP01Q08LeituraDePaginaHTML {
   public static boolean isFim(String s){
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }

   public static String getHtml(String endereco){
      URL url;
      InputStream is = null;
      BufferedReader br;
      String resp = "", line;

      try {
         url = new URL(endereco);
         is = url.openStream();  // throws an IOException
         br = new BufferedReader(new InputStreamReader(is));

         while ((line = br.readLine()) != null) {
            resp += line + "\n";
         }
      } catch (MalformedURLException mue) {
         mue.printStackTrace();
      } catch (IOException ioe) {
         ioe.printStackTrace();
      } 

      try {
         is.close();
      } catch (IOException ioe) {
         // nothing to see here

      }

      return resp;
   }

   public static boolean identificaConsoante (char c){
      return ((c >= 'a' && c <= 'z') && (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u'));
   }

   public static void searchHtml (String nome, String html){
      int x1 = 0;
      int x2 = 0;
      int x3 = 0;
      int x4 = 0;
      int x5 = 0; 
      int x6 = 0;
      int x7 = 0;
      int x8 = 0;
      int x9 = 0;
      int x10 = 0;
      int x11 = 0;
      int x12 = 0;
      int x13 = 0;
      int x14 = 0;
      int x15 = 0;
      int x16 = 0;
      int x17 = 0;
      int x18 = 0;
      int x19 = 0;
      int x20 = 0;
      int x21 = 0;
      int x22 = 0;
      int x23 = 0;
      int x24 = 0;
      int x25 = 0;
      int x26 = 0;
	   
	   //Percorre a string recebida ate sua metade, comparando a primeira posicao com a ultima, a segunda com a penultima, etc
	   for(int i = 0; i < html.length(); i++){
         if(html.charAt(i) == 'a'){
            x1++;
         }
         else if(html.charAt(i) == (char)225){
            x6++;
         }
         else if(html.charAt(i) == (char)224){
            x11++;
         }
         else if(html.charAt(i) == (char)227){
            x16++;
         }
         else if(html.charAt(i) == (char)226){
            x18++;
         }
         else if(html.charAt(i) == 'e'){
            x2++;
         }
         else if(html.charAt(i) == (char)233){
            x7++;
         }
         else if(html.charAt(i) == (char)232){
            x12++;
         }
         else if(html.charAt(i) == (char)234){
            x19++;
         }
         else if(html.charAt(i) == 'i'){
            x3++;
         }
         else if(html.charAt(i) == (char)237){
            x8++;
         }
         else if(html.charAt(i) == (char)236){
            x13++;
         }
         else if(html.charAt(i) == (char)238){
            x20++;
         }
         else if(html.charAt(i) == 'o'){
            x4++;
         }
         else if(html.charAt(i) == (char)243){
            x9++;
         }
         else if(html.charAt(i) == (char)242){
            x14++;
         }
         else if(html.charAt(i) == (char)245){
            x17++;
         }
         else if(html.charAt(i) == (char)244){
            x21++;
         }
         else if(html.charAt(i) == 'u'){
            x5++;
         }
         else if(html.charAt(i) == (char)250){
            x10++;
         }
         else if(html.charAt(i) == (char)249){
            x15++;
         }
         else if(html.charAt(i) == (char)251){
            x22++;
         }
         else if(identificaConsoante(html.charAt(i))){
            x23++;
         }
         else if(html.charAt(i) == '<' && html.charAt(i+1) == 'b' && html.charAt(i+2) == 'r' && html.charAt(i+3) == '>'){
            x24++;
         }
         else if(html.charAt(i) == '<' && html.charAt(i+1) == 't' && html.charAt(i+2) == 'a' && html.charAt(i+3) == 'b' && html.charAt(i+4) == 'l' && html.charAt(i+5) == 'e' && html.charAt(i+6) == '>'){
            x25++;
         }
	   }
      //MyIO.setCharset("ISO-8859-1");
      MyIO.println("a("+ x1 +")"+" "+"e("+x2+")"+" "+"i("+x3+")"+" "+"o("+x4+")"+" "+"u("+x5+")"+" "+(char)225+"("+x6+")"+" "+(char)233+
                   "("+x7+")"+" "+(char)237+"("+x8+")"+" "+(char)243+"("+x9+")"+" "+(char)250+"("+x10+")"+" "+(char)224+"("+x11+")"+" "+(char)232+
                   "("+x12+")"+" "+(char)236+"("+x13+")"+" "+(char)242+"("+x14+")"+" "+(char)249+"("+x15+")"+" "+(char)227+"("+x16+")"+" "+(char)245+
                   "("+x17+")"+" "+(char)226+"("+x18+")"+" "+(char)234+"("+x19+")"+" "+(char)238+"("+x20+")"+" "+(char)244+"("+x21+")"+" "+(char)251+
                   "("+x22+")"+" "+"consoante"+"("+x23+")"+" "+"<br>"+"("+x24+")"+" "+"<table>"+"("+x25+")"+" "+nome);
      /*
      MyIO.println("a("+ x1 +")"+" "+"e("+x2+")"+" "+"i("+x3+")"+" "+"o("+x4+")"+" "+"u("+x5+")"+" "+'\u00e1'+"("+x6+")"+" "+'\u00e9'+
                   "("+x7+")"+" "+'\u00ed'+"("+x8+")"+" "+'\u00f3'+"("+x9+")"+" "+'\u00fa'+"("+x10+")"+" "+'\u00e0'+"("+x11+")"+" "+'\u00e8'+
                   "("+x12+")"+" "+'\u00ec'+"("+x13+")"+" "+'\u00f2'+"("+x14+")"+" "+'\u00f9'+"("+x15+")"+" "+'\u00e3'+"("+x16+")"+" "+'\u00f5'+
                   "("+x17+")"+" "+'\u00e2'+"("+x18+")"+" "+'\u00ea'+"("+x19+")"+" "+'\u00ee'+"("+x20+")"+" "+'\u00f4'+"("+x21+")"+" "+'\u00fb'+
                   "("+x22+")"+" "+"consoante"+"("+x23+")"+" "+"<br>"+"("+x24+")"+" "+"<table>"+"("+x25+")"+" "+nome);
      */
   }

   public static void main (String[] args){
      String[] entrada = new String[1000];
      String html;
      int numEntrada = 0;

      //Leitura da entrada padrao
      do {
         entrada[numEntrada] = MyIO.readLine();
      } while (isFim(entrada[numEntrada++]) == false);
      numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

      //Para cada linha de entrada, gerando uma saida contendo informacoes sobre o codigo da pagina web
      for(int i = 0; i < numEntrada; i+=2){
         html = getHtml(entrada[i+1]);
         searchHtml(entrada[i],html);
      }   
   }
}
