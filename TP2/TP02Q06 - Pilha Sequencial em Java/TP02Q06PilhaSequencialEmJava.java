/*----------------------------------------------------------------
Atividade: TP02Q05 - Lista Sequencial em Java
------------------------------------------------------------------
Aluno: Caio Eduardo Ramos Araes
------------------------------------------------------------------
Matricula: 738811
----------------------------------------------------------------*/

class Serie{
    private String name; //nome
    private String format; //formato
    private String length; //duracao
    private String country; //paisDeOrigem
    private String original_language; //idiomaOriginal
    private String media; //emissoraDeTelevisao
    private String original_airing; //transmissaoOriginal
    private int seasons; //numeroTemporadas
    private int episodes; //numeroEpisodios

    //Construtor Padrao (inicia com atributos indefinidos)
    Serie ( ){
        name = "";
        format = "";
        length = "";
        country = "";
        original_language = "";
        media = "";
        original_airing = "";
        seasons = 0;
        episodes = 0;
    }
    //Construtor para Definicao de Valores Iniciais (define os valores dos atributos para os parametros recebidos)
    Serie (String name, String format, String length, 
           String country, String original_language, 
           String media, String original_airing, 
           int seasons, int episodes){
        this.name = name;
        this.format = format;      
        this.length = length;
        this.country = country;
        this.original_language = original_language;
        this.media = media;
        this.original_airing = original_airing;
        this.seasons = seasons;
        this.episodes = episodes;
    }

    //Metodos set e get dos atributos
    public void setName (String name){
        this.name = name;
    }
    public String getName ( ){
        return this.name;
    }

    public void setFormat (String format){
        this.format = format;
    }
    public String getFormat ( ){
        return this.format;
    }

    public void setLength (String length){
        this.length = length;
    }
    public String getLength ( ){
        return this.length;
    }

    public void setCountry (String country){
        this.country = country;
    }
    public String getCountry ( ){
        return this.country;
    }

    public void setOriginalLanguage (String original_language){
        this.original_language = original_language;
    }
    public String getOriginalLanguage ( ){
        return this.original_language;
    }

    public void setMedia (String media){
        this.media = media;
    }
    public String getMedia ( ){
        return this.media;
    }

    public void setOriginalAiring (String original_airing){
        this.original_airing = original_airing;
    }
    public String getOriginalAiring ( ){
        return this.original_airing;
    }

    public void setSeasons (int seasons){
        this.seasons = seasons;
    }
    public int getSeasons ( ){
        return this.seasons;
    }

    public void setEpisodes (int episodes){
        this.episodes = episodes;
    }
    public int getEpisodes ( ){
        return this.episodes;
    }
    
    /**
    * clone - Cria uma duplicata do objeto da classe Serie que chamar este metodo.
    * 
    * saida: copy - clone/copia do objeto que chamou o metodo
    */
    public Serie clone ( ){
        Serie copy = new Serie();
        copy.name = this.name;
        copy.format = this.format;
        copy.length = this.length;
        copy.country = this.country;
        copy.original_language = this.original_language;
        copy.media = this.media;
        copy.original_airing = this.original_airing;
        copy.seasons = this.seasons;
        copy.episodes = this.episodes;

        return copy;
    }

    /**
    * print - Imprime na tela os atributos de um objeto da classe Serie no 
    *         seguinte formato:
    *         "name format length country original_language media original_airing seasons episodes"
    */
    public void print ( ){
        MyIO.println(this.name + " " + this.format + " " + this.length + " " + this.country + " " + this.original_language + " " + this.media + " " + this.original_airing + " " + this.seasons + " " + this.episodes);
        //System.out.printf("%s %s %s %s %s %s %s %d %d\n", this.name, this.format, this.length, this.country, this.original_language, this.media, this.original_airing, this.seasons, this.episodes);
    }

    /**
    * stringTrim - Remove, recursivamente, todos os espacos em branco [(char) 32 / ' ']
    *              da string recebida. 
    * entrada(s): s - string a ter seus espacos em branco removidos;
    *             n - variavel auxiliar no processo recursivo
    * 
    * saida: resp - string com os espacos em branco removidos  
    */
    private String stringTrim (String s, int n){
        String resp = "";
  
        //Reconstroi a string recebida, removendo os espacos em branco
        if(n >= 0){
            resp = stringTrim(s,n-1);
            if(s.charAt(n) != ' '){
                resp = resp + (char) s.charAt(n);
            }
        }
      
        return resp;
    }

    /**
    * extractInfoString - Remove as tags e extrai a informacao textual desejada de 
    *                     uma linha de um arquivo .html.
    *                  
    * entrada(s): line - linha inalterada do arquivo .html 
    * 
    * saida: info - conteudo textual extraido da linha recebida como parametro
    */
     private String extractInfoString (String line){
        int i = 0;
        int n = 0;
        String info = "";
        
        for(i = 0; i < line.length(); i++){
            //Ignora as tags, ou seja, tudo que estiver entre '<' e '>'
            if(line.charAt(i) == '<'){
                for(n = 0; line.charAt(i+n) != '>'; n++){
                }
                i += n;
            }
            else{
                //Ignora &nbsp; e &#160;
                if(line.length()-i >=6 && ((line.charAt(i) == '&' && line.charAt(i+1) == 'n' && line.charAt(i+2) == 'b' && line.charAt(i+3) == 's' && line.charAt(i+4) == 'p' && line.charAt(i+5) == ';') ||
                                           (line.charAt(i) == '&' && line.charAt(i+1) == '#' && line.charAt(i+2) == '1' && line.charAt(i+3) == '6' && line.charAt(i+4) == '0' && line.charAt(i+5) == ';'))){
                    i += 5;
                }
                //Filtragem do conteudo relevante da linha
                else{
                    for(n = 0; line.charAt(i+n) != '<'; n++){ 
                        if(line.length()-i >= 2 && (line.charAt(i+n-1) == '>' && line.charAt(i+n) == ')' && line.charAt(i+n+1) == '<')){
                            info = info + ')';
                        }
                        else if(!(line.length()-i >= 2 && (line.charAt(i+n-1) == '>' && line.charAt(i+n+1) == '<'))){
                                info = info + (char) line.charAt(i+n);
                        }    
                    }
                    i += n-1;
                }
            }
        }

        return info;
    }

    /**
    * extractInfoInt - Remove as tags e extrai a informacao numerica desejada de 
    *                  uma linha de um arquivo .html.
    *                  
    * entrada(s): line - linha inalterada do arquivo .html 
    * 
    * saida: info - conteudo numerico extraido da linha recebida como parametro
    */
    private String extractInfoInt (String line){
        int i = 0;
        int n = 0;
        String info = "";
        
        for(i = 0; i < line.length(); i++){
            //Ignora as tags, ou seja, tudo que estiver entre '<' e '>'
            if(line.charAt(i) == '<'){
                for(n = 0; line.charAt(i+n) != '>'; n++){
                }
                i += n;
            }
            //Filtragem do conteudo relevante da linha
            else{
                for(n = 0; line.charAt(i+n) != '<' && line.charAt(i+n) != ' '; n++){
                    if(line.charAt(i+n) == '0' || line.charAt(i+n) == '1' || line.charAt(i+n) == '2' || line.charAt(i+n) == '3' || line.charAt(i+n) == '4' ||
                       line.charAt(i+n) == '5' || line.charAt(i+n) == '6' || line.charAt(i+n) == '7' || line.charAt(i+n) == '8' || line.charAt(i+n) == '9'){
                        info = info + (char) line.charAt(i+n);
                    } 
                }
                i = line.length();
            }
        }
        info = stringTrim(info,info.length()-1);

        return info;
    }

    /**
    * read - Faz a leitura de um arquivo .html e armazena as informacoes desejadas
    *        em um objeto da classe Serie.
    * 
    * entradas(s): path - caminho para o arquivo .html de onde as informacoes serao
    *                     extraidas
    */
    public void read (String path){
        int i = 0;
        int n = 0;
        String line = "";
        boolean eof = false;
        boolean end = false;
        //Garante que todos os atributos foram redefinidos para os seus valores padrao
        name = "";
        format = "";
        length = "";
        country = "";
        original_language = "";
        media = "";
        original_airing = "";
        seasons = 0;
        episodes = 0;
        
        Arq.openRead(path);

        //Fazer enquanto o fim do arquivo nao for alcancado ou a ultima informacao nao for encontrada 
        do {
            line = Arq.readLine();
            //EOF do .html (</html>)
            if(line.length() >= 7){
                for(n = 0; n < line.length(); n++){
                    if(line.length()-n >= 7 && (line.charAt(n) == '<' && line.charAt(n+1) == '/' && line.charAt(n+2) == 'h' && line.charAt(n+3) == 't' && line.charAt(n+4) == 'm' && line.charAt(n+5) == 'l' && line.charAt(n+6) == '>')){
                        eof = true;
                        i = line.length();
                    }
                }  
            }
            //Varre cada linha do arquivo em busca do nome e do inicio da table infobox_v2
            for(i = 0; i < line.length(); i++){
                //Secao Nome encontrada
                if(line.length()-i >= 8 && (line.charAt(i) == '"' && line.charAt(i+1) == 'p' && line.charAt(i+2) == 't' && line.charAt(i+3) == '"' && line.charAt(i+4) == '>' && line.charAt(i+5) == '<' && line.charAt(i+6) == 'i' && line.charAt(i+7) == '>')){
                    i += 8;
                    for(n = 0; line.charAt(i+n) != '<'; n++){
                        name = name + (char) line.charAt(i+n);
                    }
                    //Evita a leitura desnecessaria do resto da linha
                    i = line.length();                
                }
                //Inicio da table infobox_v2 foi encontrado
                else if(line.length()-i >= 24 && (line.charAt(i) == '<' && line.charAt(i+1) == 't' && line.charAt(i+2) == 'a' && line.charAt(i+3) == 'b' && line.charAt(i+4) == 'l' && line.charAt(i+5) == 'e' &&
                        line.charAt(i+14) == 'i' && line.charAt(i+15) == 'n' && line.charAt(i+16) == 'f' && line.charAt(i+17) == 'o' && line.charAt(i+18) == 'b' && line.charAt(i+19) == 'o' && line.charAt(i+20) == 'x' &&
                        line.charAt(i+21) == '_' && line.charAt(i+22) == 'v' && line.charAt(i+23) == '2')){
                            //Fazer ate' que a ultima informacao (qtd de episodios) seja encontrada 
                            while(!end){
                                line = Arq.readLine();
                                //Varre cada linha do arquivo em busca de palavras chave
                                for(i = 0; i < line.length(); i++){
                                    //Secao "Formato" encontrada
                                    if(line.length()-i >= 9 && (line.charAt(i) == '>' && line.charAt(i+1) == 'F' && line.charAt(i+2) == 'o' && line.charAt(i+3) == 'r' && line.charAt(i+4) == 'm' && line.charAt(i+5) == 'a' && line.charAt(i+6) == 't' && line.charAt(i+7) == 'o' && line.charAt(i+8) == '<')){
                                        //A informacao desejada se encontra na proxima linha
                                        line = Arq.readLine();
                                        format = extractInfoString(line);
                                        //Evita a leitura desnecessaria do resto da linha
                                        i = line.length();
                                    }
                                    //Secao "Duracao" encontrada
                                    else if(line.length()-i >= 11 && (line.charAt(i) == '>' && line.charAt(i+1) == 'D' && line.charAt(i+2) == 'u' && line.charAt(i+3) == 'r' && line.charAt(i+4) == 'a' && line.charAt(i+9) == 'o' && line.charAt(i+10) == '<')){
                                        //A informacao desejada se encontra na proxima linha
                                        line = Arq.readLine();
                                        length = extractInfoString(line);
                                        //Evita a leitura desnecessaria do resto da linha
                                        i = line.length();
                                    }
                                    //Secao "Pais de Origem" encontrada
                                    else if(line.length()-i >= 15 && (line.charAt(i) == 'P' && line.charAt(i+1) == 'a' && line.charAt(i+4) == 's' && line.charAt(i+6) == 'd' && line.charAt(i+7) == 'e' &&
                                            line.charAt(i+9) == 'o' && line.charAt(i+10) == 'r' && line.charAt(i+11) == 'i' && line.charAt(i+12) == 'g' && line.charAt(i+13) == 'e' && line.charAt(i+14) == 'm')){
                                        //A informacao desejada se encontra na proxima linha
                                        line = Arq.readLine();
                                        country = extractInfoString(line);
                                        //Evita a leitura desnecessaria do resto da linha
                                        i = line.length();
                                    }
                                    //Secao "Idioma Original" encontrada
                                    else if(line.length()-i >= 15 && (line.charAt(i) == 'I' && line.charAt(i+1) == 'd' && line.charAt(i+2) == 'i' && line.charAt(i+3) == 'o' && line.charAt(i+4) == 'm' && line.charAt(i+5) == 'a' &&
                                            line.charAt(i+7) == 'o' && line.charAt(i+8) == 'r' && line.charAt(i+9) == 'i' && line.charAt(i+10) == 'g' && line.charAt(i+11) == 'i' && line.charAt(i+12) == 'n' && line.charAt(i+13) == 'a' && line.charAt(i+14) == 'l')){
                                        //A informacao desejada se encontra na proxima linha
                                        line = Arq.readLine();
                                        original_language = extractInfoString(line);
                                        //Evita a leitura desnecessaria do resto da linha
                                        i = line.length();
                                    }
                                    //Secao "Emissora de Televisao" encontrada
                                    else if(line.length()-i >= 22 && (line.charAt(i) == 'E' && line.charAt(i+1) == 'm' && line.charAt(i+2) == 'i' && line.charAt(i+3) == 's' && line.charAt(i+4) == 's' && line.charAt(i+5) == 'o' && line.charAt(i+6) == 'r' && line.charAt(i+7) == 'a' && line.charAt(i+9) == 'd' && line.charAt(i+10) == 'e' && 
                                            line.charAt(i+12) == 't' && line.charAt(i+13) == 'e' && line.charAt(i+14) == 'l' && line.charAt(i+15) == 'e' && line.charAt(i+16) == 'v' && line.charAt(i+17) == 'i' && line.charAt(i+18) == 's' && line.charAt(i+21) == 'o')){
                                        //A informacao desejada se encontra na proxima linha
                                        line = Arq.readLine();
                                        media = extractInfoString(line);
                                        //Evita a leitura desnecessaria do resto da linha
                                        i = line.length();
                                    }
                                    //Secao "Transmissao Original" encontrada
                                    else if(line.length()-i >= 21 && (line.charAt(i) == 'T' && line.charAt(i+1) == 'r' && line.charAt(i+2) == 'a' && line.charAt(i+3) == 'n' && line.charAt(i+4) == 's' && line.charAt(i+5) == 'm' && line.charAt(i+6) == 'i' && line.charAt(i+7) == 's' && line.charAt(i+8) == 's' && line.charAt(i+11) == 'o' &&
                                            line.charAt(i+13) == 'o' && line.charAt(i+14) == 'r' && line.charAt(i+15) == 'i' && line.charAt(i+16) == 'g' && line.charAt(i+17) == 'i' && line.charAt(i+18) == 'n' && line.charAt(i+19) == 'a' && line.charAt(i+20) == 'l')){
                                        //A informacao desejada se encontra na proxima linha
                                        line = Arq.readLine();
                                        original_airing = extractInfoString(line);
                                        //Evita a leitura desnecessaria do resto da linha
                                        i = line.length();
                                    }
                                    //Secao "Numero de Temporadas" encontrada
                                    else if(line.length()-i >= 18 && (line.charAt(i) == 'N' && line.charAt(i+1) == '.' && line.charAt(i+5) == 'd' && line.charAt(i+6) == 'e' &&
                                            line.charAt(i+8) == 't' && line.charAt(i+9) == 'e' && line.charAt(i+10) == 'm' && line.charAt(i+11) == 'p' && line.charAt(i+12) == 'o' && line.charAt(i+13) == 'r' && line.charAt(i+14) == 'a' && line.charAt(i+15) == 'd' && line.charAt(i+16) == 'a' && line.charAt(i+17) == 's')){
                                        //A informacao desejada se encontra na proxima linha
                                        line = Arq.readLine();
                                        seasons = Integer.parseInt(extractInfoInt(line));
                                        //Evita a leitura desnecessaria do resto da linha
                                        i = line.length();
                                    }
                                    //Secao "Numero de Episodios" encontrada
                                    else if(line.length()-i >= 18 && (line.charAt(i) == 'N' && line.charAt(i+1) == '.' && line.charAt(i+5) == 'd' && line.charAt(i+6) == 'e' &&
                                            line.charAt(i+8) == 'e' && line.charAt(i+9) == 'p' && line.charAt(i+10) == 'i' && line.charAt(i+11) == 's' && line.charAt(i+14) == 'd' && line.charAt(i+15) == 'i' && line.charAt(i+16) == 'o' && line.charAt(i+17) == 's')){
                                        //A informacao desejada se encontra na proxima linha
                                        line = Arq.readLine();
                                        episodes = Integer.parseInt(extractInfoInt(line));
                                        //Determina o fim do laco while apos a ultima informacao ter sido encontrada
                                        end = true;
                                        //Finaliza a leitura do arquivo quando todas as informacoes forem encontradas
                                        eof = end;
                                        //Evita a leitura desnecessaria do resto da linha
                                        i = line.length();
                                    }
                                }
                            }                 
                }
            }
        } while(!eof);

        Arq.close(); 
    }
}


/**
 * Fila com Alocacao Sequencial
 */
class Fila {
    private Serie[] series;
    private int primeiro; // Remove do indice "primeiro".
    private int ultimo; // Insere no indice "ultimo".
 
 
    /**
     * Construtor da classe.
     */
    public Fila () {
       this(6);
    }
 
 
    /**
     * Construtor da classe.
     * @param tamanho - Tamanho da fila.
     */
    public Fila (int tamanho){
       series = new Serie [tamanho+1];
       primeiro = ultimo = 0;
    }
 
 
    /**
     * Insere um elemento na ultima posicao da fila.
     * @param x - objeto a ser inserido.
     * @throws Exception Se a fila estiver cheia.
     */
    public void inserir(Serie x) throws Exception {
 
       //validar insercao
       if (((ultimo + 1) % series.length) == primeiro) {
          throw new Exception("Erro ao inserir!");
       }
 
       series[ultimo] = x;
       ultimo = (ultimo + 1) % series.length;
    }
 
 
    /**
     * Remove um elemento da primeira posicao da fila e movimenta 
     * os demais elementos para o primeiro da mesma.
     * @return resp objeto a ser removido.
     * @throws Exception Se a fila estiver vazia.
     */
    public Serie remover() throws Exception {
 
       //validar remocao
       if (primeiro == ultimo) {
          throw new Exception("Erro ao remover!");
       }
 
       Serie resp = series[primeiro];
       primeiro = (primeiro + 1) % series.length;
       return resp;
    }
 
 
    /**
     * Mostra os elementos da fila, um em cada linha.
     */
    public void mostrar (){
       for(int i = primeiro; i != ultimo; i = ((i + 1) % series.length)) {
          series[i].print();
       }
    }
 
    public void mostrarRec(){
       mostrarRec(primeiro);
    }
 
    public void mostrarRec(int i){
       if(i != ultimo){
        series[i].print();
          mostrarRec((i + 1) % series.length);
       }
    }
 
 
    /**
     * Retorna um boolean indicando se a fila esta vazia
     * @return boolean indicando se a fila esta vazia
     */
    public boolean isVazia() {
       return (primeiro == ultimo); 
    }
}


/**
 * Pilha com Alocacao Sequencial
 */
class Pilha{

    private Fila f1, f2;
 
    public Pilha(){
      f1 = new Fila(6);
      f2 = new Fila(6);
    }
 
    public Pilha(int tamanho){
      f1 = new Fila (tamanho);
      f2 = new Fila (tamanho);
    }
 
    public void empilhar(Serie elemento) throws Exception {
      while(!f1.isVazia()){
        f2.inserir(f1.remover());
      }
 
      f1.inserir(elemento);
 
      while(!f2.isVazia()){
        f1.inserir(f2.remover());
      }
    }
 
    public Serie desempilhar() throws Exception {
      return f1.remover();
    }
 
    public void mostrar(){
      f1.mostrar();
    }
 
    public boolean isVazia(){
      return f1.isVazia();
    }
} 


public class TP02Q06PilhaSequencialEmJava {
    //Verifica se a string recebida corresponde a "FIM"
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) throws Exception {
        String[] entrada1 = new String[1000];
        String comando;
        String complemento = "";
        String dir = "/tmp/series/";
        String path = "";
        Serie item = new Serie();
        Serie clone = new Serie();
        Serie deleted = new Serie();
        Pilha pilha = new Pilha(100);
        int numEntrada = 0;
        int n = 0;
        
        //Leitura da primeira parte da entrada padrao
        do {
           entrada1[numEntrada] = MyIO.readLine();
        } while (isFim(entrada1[numEntrada++]) == false);
        numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM
 
        //Preenche a pilha com a primeira parte da entrada
        for(int i = 0; i < numEntrada; i++){
            path = dir + entrada1[i];
            item.read(path);
            clone = item.clone();
            pilha.empilhar(clone);
        }

        //Leitura da segunda parte da entrada padrao
        n = Integer.parseInt(MyIO.readLine());
        for(int i = 0; i < n; i++){
            //Leitura da parte que identifica o tipo da operacao a ser feita (comando)
            comando = MyIO.readString();
            //Leitura do complemento para o comando I, o unico que requer complemento
            if (comando.charAt(0) == 'I'){
                complemento = MyIO.readString();         
            }

            if (comando.equals("I")){
                path = dir + complemento;
                item.read(path);
                clone = item.clone();
                pilha.empilhar(clone);
            }
            else if (comando.equals("R")){
                deleted = pilha.desempilhar();
                MyIO.println("(R)" + " " + deleted.getName());
            }
        }

        pilha.mostrar();
    }
}