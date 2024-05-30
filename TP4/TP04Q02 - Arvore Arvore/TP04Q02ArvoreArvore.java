/*----------------------------------------------------------------
Atividade: TP04Q02 - Arvore Binaria de Arvores Binarias
------------------------------------------------------------------
Aluno: Caio Eduardo Ramos Araes
------------------------------------------------------------------
Matricula: 738811
----------------------------------------------------------------*/
import java.util.*;

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
 * No da primeira arvore
 */
class No {
	public char elemento; // Conteudo do no.
	public No esq; // No da esquerda.
	public No dir; // No da direita.
    public No2 outro;
	
    /**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 */
	No(char elemento) {
		this.elemento = elemento;
		this.esq = this.dir = null;
        this.outro = null;
	}

	/**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 * @param esq No da esquerda.
	 * @param dir No da direita.
	 */
	No(char elemento, No esq, No dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
        this.outro = null;
	}
}

/**
 * No da segunda arvore
 */
class No2 {
	public Serie elemento; // Conteudo do no.
	public No2 esq; // No da esquerda.
	public No2 dir; // No da direita.
	
    /**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 */
	No2(Serie elemento) {
		this.elemento = elemento;
		this.esq = this.dir = null;
	}

	/**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 * @param esq No2 da esquerda.
	 * @param dir No2 da direita.
	 */
	No2(Serie elemento, No2 esq, No2 dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
	}
}

/** 
 * Arvore de Arvore Binarias de pesquisa
 */
class ArvoreArvore {
	private No raiz; // Raiz da arvore.
    public static int comp_num;

	/**
	 * Construtor da classe.
	 */
	public ArvoreArvore() throws Exception {
		raiz = null;
        inserir('D');
        inserir('R');
        inserir('Z');
        inserir('X');
        inserir('V');
        inserir('B');
        inserir('F');
        inserir('P');
        inserir('U');
        inserir('I');
        inserir('G');
        inserir('E');
        inserir('J');
        inserir('L');
        inserir('H');
        inserir('T');
        inserir('A');
        inserir('W');
        inserir('S');
        inserir('O');
        inserir('M');
        inserir('N');
        inserir('K');
        inserir('C');
        inserir('Y');
        inserir('Q');
	}

    /**
	 * Metodo publico iterativo para inserir elemento (letra)
     * na primeira arvore.
	 * @param x Letra a ser inserida.
	 * @throws Exception Se a letra ja existir.
	 */
	public void inserir(char x) throws Exception {
		raiz = inserir(x, raiz);
	}

	/**
	 * Metodo privado recursivo para inserir elemento (letra)
     * na primeira arvore.
	 * @param x Letra a ser inserida.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se a letra ja existir.
	 */
	private No inserir(char x, No i) throws Exception {
		if (i == null) {
            i = new No(x);
            comp_num++;

        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);
            comp_num += 2;

        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);
            comp_num += 3;

        } else {
            throw new Exception("Erro ao inserir!");
        }

		return i;
	}

    public void inserir(Serie x) throws Exception {
        inserir(x, raiz);
    }

    public void inserir(Serie x, No i) throws Exception {
	    if (i == null) {
            throw new Exception("Erro ao inserir: caractere invalido!");

        } else if (x.getName().charAt(0) < i.elemento) {
            inserir(x, i.esq);
            comp_num += 2;

        } else if (x.getName().charAt(0) > i.elemento) {
            inserir(x, i.dir);
            comp_num += 3;

        } else {
            i.outro = inserir(x, i.outro);
            comp_num += 3;
        }
    }

	private No2 inserir(Serie x, No2 i) throws Exception {
	    if (i == null) {
            i = new No2(x);
            comp_num++;
        
        } else if (x.getName().compareTo(i.elemento.getName()) < 0) {
            i.esq = inserir(x, i.esq);
            comp_num += 2;
        
        } else if (x.getName().compareTo(i.elemento.getName()) > 0) {
            i.dir = inserir(x, i.dir);
            comp_num += 3;
        
        } else {
           throw new Exception("Erro ao inserir: elemento existente!");
        }
		return i;
	}

    public void mostrar(){
        mostrar(raiz);
    }

    public void mostrar(No i){
        if (i != null){
            mostrar(i.esq);
            System.out.println("Letra: " + i.elemento);
            mostrar(i.outro);
            mostrar(i.dir);
        }
    }

    public void mostrar(No2 i){
        if (i != null){
            mostrar(i.esq);
            System.out.println(i.elemento.getName());
            mostrar(i.dir);
        }
    }

    /**
	 * Metodo publico iterativo para pesquisar elemento
     * pela chave name, registrando o trajeto feito.
	 * @param name chave do elemento que sera procurado.
	 * @return <code>true</code> se o elemento existir,
	 *         <code>false</code> em caso contrario.
	 */
	public boolean mostrarANDpesquisarMapeado(String name) {
        if (raiz != null) {
            System.out.print(" raiz");
        } comp_num++;

		return mostrarANDpesquisarMapeado(raiz, name);
	}

	private boolean mostrarANDpesquisarMapeado(No no, String x) {
        boolean resp;
	    
        if (no == null) { 
            resp = false;
            comp_num++;

        } else if (pesquisarSegundaArvoreMapeado(no.outro, x)) {
            resp = true; 
            comp_num++;
        
        } else { 
            System.out.print(" esq");
            resp = mostrarANDpesquisarMapeado(no.esq, x);

            if (!resp) {
                System.out.print(" dir");
                resp = mostrarANDpesquisarMapeado(no.dir, x);
            }

            comp_num++;
        }
      
        return resp;
	}

    private boolean pesquisarSegundaArvoreMapeado(No2 no, String x) {
        boolean resp;

	    if (no == null) {
            resp = false;
            comp_num++;

        } else if (x.equals(no.elemento.getName())) {
            resp = true;
            comp_num += 2; 
             
        } else { 
            System.out.print(" ESQ"); 
            resp = pesquisarSegundaArvoreMapeado(no.esq, x);

            if (!resp) {
                System.out.print(" DIR"); 
                resp = pesquisarSegundaArvoreMapeado(no.dir, x);
            }
            
            comp_num += 2;
        }
        
        return resp;
	}
}


public class TP04Q02ArvoreArvore {
    //Verifica se a string recebida corresponde a "FIM"
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    /**
    * Retorna o timestamp atual
    * @return timestamp atual
    */
    public static long now(){
        return new Date().getTime();
    }

    public static void main(String[] args) throws Exception {
        String[] entrada1 = new String[1000];
        String[] entrada2 = new String[1000];
        String comando;
        String complemento = "";
        String dir = "/tmp/series/";
        String path = "";
        Serie item = new Serie();
        Serie clone = new Serie();
        ArvoreArvore arvore = new ArvoreArvore();
        int n = 0, numEntrada1 = 0, numEntrada2 = 0;
        double start = 0, end = 0; 
        
        ArvoreArvore.comp_num = 0;
        start = now();
        //Leitura da primeira parte da entrada padrao
        do {
           entrada1[numEntrada1] = MyIO.readLine();
        } while (isFim(entrada1[numEntrada1++]) == false);
        numEntrada1--;   //Desconsiderar ultima linha contendo a palavra FIM

        //Preenche a arvore com a primeira parte da entrada
        for(int i = 0; i < numEntrada1; i++){
            path = dir + entrada1[i];
            item.read(path);
            clone = item.clone();
            arvore.inserir(clone);
        }

        //Leitura da segunda parte da entrada padrao
        do {
            entrada2[numEntrada2] = MyIO.readLine();
        } while (isFim(entrada2[numEntrada2++]) == false);
        numEntrada2--;   //Desconsiderar ultima linha contendo a palavra FIM

        
        //Pesquisa na arvore os elementos cujas chaves foram lidas
        for(int i = 0; i < numEntrada2; i++){
            if (arvore.mostrarANDpesquisarMapeado(entrada2[i])) {
                System.out.print("  SIM");
            } else {
                System.out.print("  NAO");
            }      
            System.out.print("\n");
        }
        end = now();
             
        Arq.openWrite("matricula_arvoreArvore.txt");
        Arq.print("738811" + "\t" + ArvoreArvore.comp_num + "\t" + (end-start)/1000.0);
        Arq.close();
    }
}
