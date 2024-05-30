/*----------------------------------------------------------------
Atividade: TP03Q04 - Shellsort em C
------------------------------------------------------------------
Aluno: Caio Eduardo Ramos Araes
------------------------------------------------------------------
Matricula: 738811
----------------------------------------------------------------*/
#include<stdlib.h>
#include<stdio.h>
#include<string.h>
#include<stdbool.h>
#include<time.h>

    int mov_num = 0;
    int comp_num = 0;

    typedef struct {
        char name [100]; //nome
        char format [100]; //formato
        char length [100]; //duracao
        char country [100]; //paisDeOrigem
        char original_language [100]; //idiomaOriginal
        char media [100]; //emissoraDeTelevisao
        char original_airing [100]; //transmissaoOriginal
        int seasons; //numeroTemporadas
        int episodes; //numeroEpisodios
    } Serie;
    
    /**
    * clonar - Cria uma duplicata do objeto da struct Serie recebido como parametro.
    *
    * entrada(s): serie - objeto a ser clonado
    * 
    * saida: copy - clone/copia do objeto recebido como parametro
    */ 
    Serie clonar (Serie serie){
        static Serie copy;

        strcpy(copy.name,serie.name);
        strcpy(copy.format,serie.format);
        strcpy(copy.length,serie.length);
        strcpy(copy.country,serie.country);
        strcpy(copy.original_language,serie.original_language);
        strcpy(copy.media,serie.media);
        strcpy(copy.original_airing,serie.original_airing);
        copy.seasons = serie.seasons;
        copy.episodes = serie.episodes;

        return copy;
    }

    /**
    * print - Imprime na tela os atributos de um objeto da struct Serie no 
    *         seguinte formato:
    *         "name format length country original_language media original_airing seasons episodes"
    * 
    * entrada(s): serie - objeto da struct Serie
    */ 
    void print (Serie serie){
        printf("%s %s %s %s %s %s %s %d %d\n", serie.name, serie.format, serie.length, serie.country, serie.original_language, serie.media, serie.original_airing, serie.seasons, serie.episodes);
    }

    /**
    * cToStr - Converte um caractere recebido em uma string. 
    *          (Facilita a strcat() a concatenar um caractere
    *           a uma string)
    *
    * entrada(s): c - caractere a ser convertido para string
    * 
    * saida: cToStr - caractere recebido convertido para string
    */ 
    char* cToStr (char c){
        char* cToStr = (char*) malloc(sizeof(2));
        
        cToStr[1] = '\0';
        cToStr[0] = c;
      
        return cToStr;
    }

    /**
    * stringTrim - Remove, recursivamente, todos os espacos em branco [(char) 32 / ' ']
    *              da string recebida. 
    * entrada(s): s - string a ter seus espacos em branco removidos;
    *             n - variavel auxiliar no processo recursivo (tamanho da string)
    * 
    * saida: resp - string com os espacos em branco removidos  
    */ 
    char* stringTrim (char* s, int n){
        char* resp = (char*) malloc(sizeof(strlen(s)));
  
        //Reconstroi a string recebida, removendo os espacos em branco
        if(n >= 0){
            strcpy(resp,stringTrim(s,n-1));
            if(s[n] != ' '){
                strcat(resp,cToStr(s[n]));
            }
        }
      
        return resp;
    }

    /**
    * removeTags - Remove as tags de uma linha de um arquivo .html.
    *
    * entrada(s): line - linha inalterada do arquivo .html 
    * 
    * saida: noTags - linha recebida como parametro com todas as tags removidas
    */ 
    char* removeTags (char* line){
        int i = 0;
        int n = 0;
        char* noTags = (char*) malloc(sizeof(strlen(line)));
        
        for(i = 0; i < strlen(line); i++){
            //Ignora as tags, ou seja, tudo que estiver entre '<' e '>'
            if(line[i] == '<'){
                for(n = 0; line[i+n] != '>'; n++){
                }
                i = i + n;
            }
            else if(line[i] == '\n'){
                i = strlen(line);
            }
            //Conteudo que existe fora de tags
            else{
                strcat(noTags,cToStr(line[i]));
            }
        }

        return noTags;
    }

    /**
    * extractInfoString - Extrai a informacao textual desejada, removidas as tags, 
    *                     de uma linha de um arquivo .html.
    *                  
    * entrada(s): noTagsLine - linha sem tags do arquivo .html 
    * 
    * saida: info - conteudo textual extraido da linha recebida como parametro
    */ 
    char* extractInfoString (char* noTagsLine){
        int i = 0;
        int n = 0;
        char* info = (char*) malloc(sizeof(100));
        
        for(i = 0; i < strlen(noTagsLine); i++){
                //Ignora &nbsp; e &#160;
                if(strlen(noTagsLine)-i >=6 && ((noTagsLine[i] == '&' && noTagsLine[i+1] == 'n' && noTagsLine[i+2] == 'b' && noTagsLine[i+3] == 's' && noTagsLine[i+4] == 'p' && noTagsLine[i+5] == ';') ||
                                                (noTagsLine[i] == '&' && noTagsLine[i+1] == '#' && noTagsLine[i+2] == '1' && noTagsLine[i+3] == '6' && noTagsLine[i+4] == '0' && noTagsLine[i+5] == ';'))){
                    i = i + 5;
                }
                //Filtragem do conteudo relevante da linha
                else{
                    if(strlen(noTagsLine)-i >= 2 && (noTagsLine[i-1] == '>' && noTagsLine[i] == ')' && noTagsLine[i+1] == '<')){
                        strcat(info,cToStr(')'));
                    }
                    else if(!(strlen(noTagsLine)-i >= 2 && (noTagsLine[i-1] == '>' && noTagsLine[i+1] == '<'))){
                            strcat(info,cToStr(noTagsLine[i]));
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
    char* extractInfoInt (char* line){
        int i = 0;
        int n = 0;
        char* info = (char*) malloc(sizeof(100));
        
        for(i = 0; i < strlen(line); i++){
            //Ignora as tags, ou seja, tudo que estiver entre '<' e '>'
            if(line[i] == '<'){
                for(n = 0; line[i+n] != '>'; n++){
                }
                i = i + n;
            }
            //Filtragem do conteudo relevante da linha
            else{
                for(n = 0; line[i+n] != '<' && line[i+n] != ' '; n++){
                    if(line[i+n] == '0' || line[i+n] == '1' || line[i+n] == '2' || line[i+n] == '3' || line[i+n] == '4' ||
                       line[i+n] == '5' || line[i+n] == '6' || line[i+n] == '7' || line[i+n] == '8' || line[i+n] == '9'){
                        strcat(info,cToStr(line[i+n]));
                    } 
                }
                i = strlen(line);
            }
        }
        strcpy(info,stringTrim(info,strlen(info)-1));

        return info;
    }

    /**
    * read - Faz a leitura de um arquivo .html, armazena as informacoes desejadas
    *        em um objeto da struct Serie e retorna o mesmo.
    * 
    * entradas(s): path - caminho para o arquivo .html de onde as informacoes serao
    *                     extraidas
    * 
    * saida: serie - objeto da struct Serie que armazena as informacoes extraidas 
    */ 
    Serie read (const char* path){
        int i = 0;
        int n = 0;
        char c;
        char line [2000];
        char name [100];
        char* endptr; //strtol
        bool eof = false;
        bool end = false;
        Serie serie;
        //Garante que todos os atributos foram redefinidos para os seus valores padrao
        strcpy(serie.name,"");
        strcpy(serie.format,"");
        strcpy(serie.length,"");
        strcpy(serie.country,"");
        strcpy(serie.original_language,"");
        strcpy(serie.media,"");
        strcpy(serie.original_airing,"");
        serie.seasons = 0;
        serie.episodes = 0;

        /*
        for (int i1 = 0; i1 < strlen(path); i1++){
            c = path[i1];
            printf("%c\n", c);
        }
        */
        
        FILE* fp = fopen(path,"r");
        //Verifica se a tentativa de abrir o arquivo para leitura teve sucesso
        if(fp == NULL)
        {
            //perror("Error");
            printf("Erro ao abrir o arquivo\n");
            return serie;
        }
        
        //Fazer enquanto o fim do arquivo nao for alcancado ou a ultima informacao nao for encontrada 
        do {
            fgets(line,2000,fp);
            //EOF do .html (</html>)
            if(strlen(line) >= 7){
                for(n = 0; n < strlen(line); n++){
                    if(strlen(line)-n >= 7 && (line[n] == '<' && line[n+1] == '/' && line[n+2] == 'h' && line[n+3] == 't' && line[n+4] == 'm' && line[n+5] == 'l' && line[n+6] == '>')){
                        eof = true;
                        i = strlen(line);
                    }
                }  
            }
            //Varre cada linha do arquivo em busca do nome e do inicio da table infobox_v2
            for(i = 0; i < strlen(line); i++){
                //Secao Nome encontrada
                if(strlen(line)-i >= 8 && (line[i] == '"' && line[i+1] == 'p' && line[i+2] == 't' && line[i+3] == '"' && line[i+4] == '>' && line[i+5] == '<' && line[i+6] == 'i' && line[i+7] == '>')){
                    i = i + 8;
                    for(n = 0; line[i+n] != '<'; n++){
                        strcat(serie.name,cToStr(line[i+n]));
                    }
                    //Evita a leitura desnecessaria do resto da linha
                    i = strlen(line);                
                }
                //Inicio da table infobox_v2 foi encontrado
                else if(strlen(line)-i >= 24 && (line[i] == '<' && line[i+1] == 't' && line[i+2] == 'a' && line[i+3] == 'b' && line[i+4] == 'l' && line[i+5] == 'e' &&
                                                 line[i+14] == 'i' && line[i+15] == 'n' && line[i+16] == 'f' && line[i+17] == 'o' && line[i+18] == 'b' && line[i+19] == 'o' && line[i+20] == 'x' &&
                                                 line[i+21] == '_' && line[i+22] == 'v' && line[i+23] == '2')){
                            //Fazer ate' que a ultima informacao (qtd de episodios) seja encontrada 
                            while(!end){
                                fgets(line,2000,fp);
                                //Varre cada linha do arquivo em busca de palavras chave
                                for(i = 0; i < strlen(line); i++){
                                    //Secao "Formato" encontrada
                                    if(strlen(line)-i >= 9 && (line[i] == '>' && line[i+1] == 'F' && line[i+2] == 'o' && line[i+3] == 'r' && line[i+4] == 'm' && line[i+5] == 'a' && line[i+6] == 't' && line[i+7] == 'o' && line[i+8] == '<')){
                                        //A informacao desejada se encontra na proxima linha
                                        fgets(line,2000,fp);
                                        strcpy(serie.format,extractInfoString(removeTags(line)));
                                        //Evita a leitura desnecessaria do resto da linha
                                        i = strlen(line);
                                    }
                                    //Secao "Duracao" encontrada
                                    else if(strlen(line)-i >= 11 && (line[i] == '>' && line[i+1] == 'D' && line[i+2] == 'u' && line[i+3] == 'r' && line[i+4] == 'a' && line[i+9] == 'o' && line[i+10] == '<')){
                                        //A informacao desejada se encontra na proxima linha
                                        fgets(line,2000,fp);
                                        strcpy(serie.length,extractInfoString(removeTags(line)));
                                        //Evita a leitura desnecessaria do resto da linha
                                        i = strlen(line);
                                    }
                                    //Secao "Pais de Origem" encontrada
                                    else if(strlen(line)-i >= 15 && (line[i] == 'P' && line[i+1] == 'a' && line[i+4] == 's' && line[i+6] == 'd' && line[i+7] == 'e' &&
                                                                     line[i+9] == 'o' && line[i+10] == 'r' && line[i+11] == 'i' && line[i+12] == 'g' && line[i+13] == 'e' && line[i+14] == 'm')){
                                        //A informacao desejada se encontra na proxima linha
                                        fgets(line,2000,fp);
                                        strcpy(serie.country,extractInfoString(removeTags(line)));
                                        //Evita a leitura desnecessaria do resto da linha
                                        i = strlen(line);
                                    }
                                    //Secao "Idioma Original" encontrada
                                    else if(strlen(line)-i >= 15 && (line[i] == 'I' && line[i+1] == 'd' && line[i+2] == 'i' && line[i+3] == 'o' && line[i+4] == 'm' && line[i+5] == 'a' &&
                                                                     line[i+7] == 'o' && line[i+8] == 'r' && line[i+9] == 'i' && line[i+10] == 'g' && line[i+11] == 'i' && line[i+12] == 'n' && line[i+13] == 'a' && line[i+14] == 'l')){
                                        //A informacao desejada se encontra na proxima linha
                                        fgets(line,2000,fp);
                                        strcpy(serie.original_language,extractInfoString(removeTags(line)));
                                        //Evita a leitura desnecessaria do resto da linha
                                        i = strlen(line);
                                    }
                                    //Secao "Emissora de Televisao" encontrada
                                    else if(strlen(line)-i >= 22 && (line[i] == 'E' && line[i+1] == 'm' && line[i+2] == 'i' && line[i+3] == 's' && line[i+4] == 's' && line[i+5] == 'o' && line[i+6] == 'r' && line[i+7] == 'a' && line[i+9] == 'd' && line[i+10] == 'e' && 
                                                                     line[i+12] == 't' && line[i+13] == 'e' && line[i+14] == 'l' && line[i+15] == 'e' && line[i+16] == 'v' && line[i+17] == 'i' && line[i+18] == 's' && line[i+21] == 'o')){
                                        //A informacao desejada se encontra na proxima linha
                                        fgets(line,2000,fp);
                                        strcpy(serie.media,extractInfoString(removeTags(line)));
                                        //Evita a leitura desnecessaria do resto da linha
                                        i = strlen(line);
                                    }
                                    //Secao "Transmissao Original" encontrada
                                    else if(strlen(line)-i >= 21 && (line[i] == 'T' && line[i+1] == 'r' && line[i+2] == 'a' && line[i+3] == 'n' && line[i+4] == 's' && line[i+5] == 'm' && line[i+6] == 'i' && line[i+7] == 's' && line[i+8] == 's' && line[i+11] == 'o' &&
                                                                     line[i+13] == 'o' && line[i+14] == 'r' && line[i+15] == 'i' && line[i+16] == 'g' && line[i+17] == 'i' && line[i+18] == 'n' && line[i+19] == 'a' && line[i+20] == 'l')){
                                        //A informacao desejada se encontra na proxima linha
                                        fgets(line,2000,fp);
                                        strcpy(serie.original_airing,extractInfoString(removeTags(line)));
                                        //Evita a leitura desnecessaria do resto da linha
                                        i = strlen(line);
                                    }
                                    //Secao "Numero de Temporadas" encontrada
                                    else if(strlen(line)-i >= 18 && (line[i] == 'N' && line[i+1] == '.' && line[i+5] == 'd' && line[i+6] == 'e' &&
                                                                     line[i+8] == 't' && line[i+9] == 'e' && line[i+10] == 'm' && line[i+11] == 'p' && line[i+12] == 'o' && line[i+13] == 'r' && line[i+14] == 'a' && line[i+15] == 'd' && line[i+16] == 'a' && line[i+17] == 's')){
                                        //A informacao desejada se encontra na proxima linha
                                        fgets(line,2000,fp);
                                        serie.seasons = strtol(extractInfoInt(line),&endptr,10);
                                        //Evita a leitura desnecessaria do resto da linha
                                        i = strlen(line);
                                    }
                                    //Secao "Numero de Episodios" encontrada
                                    else if(strlen(line)-i >= 18 && (line[i] == 'N' && line[i+1] == '.' && line[i+5] == 'd' && line[i+6] == 'e' &&
                                                                     line[i+8] == 'e' && line[i+9] == 'p' && line[i+10] == 'i' && line[i+11] == 's' && line[i+14] == 'd' && line[i+15] == 'i' && line[i+16] == 'o' && line[i+17] == 's')){
                                        //A informacao desejada se encontra na proxima linha
                                        fgets(line,2000,fp);
                                        serie.episodes = strtol(extractInfoInt(line),&endptr,10);
                                        //Determina o fim do laco while apos a ultima informacao ter sido encontrada
                                        end = true;
                                        //Finaliza a leitura do arquivo quando todas as informacoes forem encontradas
                                        eof = end;
                                        //Evita a leitura desnecessaria do resto da linha
                                        i = strlen(line);
                                    }
                                }
                            }                 
                }
            }
        } while(!eof);

        fclose(fp);

        return serie; 
    }


    /**
     * Lista sequencial
     */

    #define MAXTAM 100

    
    typedef struct {
        //Serie series [MAXTAM];
        Serie* series;    
        int n;
    } Lista;


    Lista new_Lista() {
        Lista tmpLista;

        tmpLista.series = malloc (MAXTAM * sizeof(Serie));
        tmpLista.n = 0;
        
        return tmpLista;
    }


    /**
     * Insere um elemento na primeira posicao da lista e move os demais
     * elementos para o fim dela.
     * @param lista - lista na qual a insercao sera' feita.
     * @param x - objeto a ser inserido.
     */
    void inserirInicio(Lista* lista, Serie x) {
       int i;

       //validar insercao
       if(lista->n >= MAXTAM){
          printf("Erro ao inserir!");
          exit(1);
       } 

       //levar elementos para o fim do array
       for(i = lista->n; i > 0; i--){
          lista->series[i] = lista->series[i-1];
       }

       lista->series[0] = x;
       lista->n++;
    }


    /**
     * Insere um elemento em uma posicao especifica e move os demais
     * elementos para o fim dela.
     * @param lista - lista na qual a insercao sera' feita.
     * @param x - objeto a ser inserido.
     * @param pos - posicao de insercao.
     */
    void inserir(Lista* lista, Serie x, int pos) {
       int i;

       //validar insercao
       if(lista->n >= MAXTAM || pos < 0 || pos > lista->n){
          printf("Erro ao inserir!");
          exit(1);
       }

       //levar elementos para o fim do array
       for(i = lista->n; i > pos; i--){
          lista->series[i] = lista->series[i-1];
       }

       lista->series[pos] = x;
       lista->n++;
    }


    /**
     * Insere um elemento na ultima posicao da lista.
     * @param lista - lista na qual a insercao sera' feita.
     * @param x - objeto a ser inserido.
     */
    void inserirFim(Lista* lista, Serie x) {

       //validar insercao
       if(lista->n >= MAXTAM){
          printf("Erro ao inserir!");
          exit(1);
       }

       lista->series[lista->n] = x;
       lista->n++;
    }


    /**
     * Remove um elemento da primeira posicao da lista e movimenta 
     * os demais elementos para o inicio da mesma.
     * @param lista - lista na qual a remocao sera' feita.
     * @return resp - elemento a ser removido.
     */
    Serie removerInicio(Lista* lista) {
       int i;
       Serie resp;

       //validar remocao
       if (lista->n == 0) {
          printf("Erro ao remover!");
          exit(1);
       }

       resp = lista->series[0];
       lista->n--;

       for(i = 0; i < lista->n; i++){
          lista->series[i] = lista->series[i+1];
       }

       return resp;
    }


    /**
     * Remove um elemento de uma posicao especifica da lista e 
     * movimenta os demais elementos para o inicio da mesma.
     * @param lista - lista na qual a remocao sera' feita.
     * @param pos - posicao de remocao.
     * @return resp - elemento a ser removido.
     */
    Serie remover(Lista* lista, int pos) {
        int i;
        Serie resp;
 
        //validar remocao
        if (lista->n == 0 || pos < 0 || pos >= lista->n) {
           printf("Erro ao remover!");
           exit(1);
        }
 
        resp = lista->series[pos];
        lista->n--;
 
        for(i = pos; i < lista->n; i++){
           lista->series[i] = lista->series[i+1];
        }
 
        return resp;
    }


    /**
     * Remove um elemento da ultima posicao da lista.
     * @param lista - lista na qual a remocao sera' feita. 
     * @return resp - elemento a ser removido.
     */
    Serie removerFim(Lista* lista) {

        //validar remocao
        if (lista->n == 0) {
           printf("Erro ao remover!");
           exit(1);
        }
 
        return lista->series[--lista->n];
    }


    /**
     * Mostra os elementos, um em cada linha.
     * @param lista - lista a ter seus elementos exibidos.
     */
    void mostrar(Lista* lista){
        int i;
 
        for(i = 0; i < lista->n; i++){
           print(lista->series[i]);
        }
    }


    /**
    * stringTrim2 - Remove todos os espacos em branco [(char) 32 / ' ']
    *              da string recebida. 
    * entrada(s): s - string a ter seus espacos em branco removidos
    * 
    * saida: resp - string com os espacos em branco removidos  
    */
    char* stringTrim2 (char* s){
        char* resp = (char*) malloc(sizeof(strlen(s)));
  
        //Reconstroi a string recebida, removendo os espacos em branco
        for(int i = 0; i < strlen(s); i++){
            if(s[i] != ' '){
                strcat(resp,cToStr(s[i]));
            }
        }
      
        return resp;
    }


    /**
     * Algoritmo de ordenacao por insercao.
     * Metodo auxiliar do algoritmo ShellSort 
     * da funcao shellSortOgLanguage.
     * 
     * @param lista - lista a ter seus elementos exibidos.
     * @param n - tamanho da lista.
     * @param cor - cor do subvetor a ser ordenado.
     * @param h - distancia comum entre os elementos do subvetor.
     */
    void insercaoPorCor(Lista* lista, int n, int cor, int h){
        for (int i = (h + cor); i < n; i+=h) {
            Serie tmp = lista->series[i];
            int j = i - h;
            
            while ( (j >= 0) && (strcmp( stringTrim2(lista->series[j].original_language) , stringTrim2(tmp.original_language) ) > 0) ) {
                comp_num++;

                lista->series[j + h] = lista->series[j];
                mov_num++;
                
                j-=h;
            } comp_num++;
            lista->series[j + h] = tmp;
            mov_num++;
        }
    }


    /**
     * Ordena, em ordem alfabetica, os elementos da lista, 
     * com base no atributo original_language e por meio do 
     * algoritmo de ordenacao ShellSort. 
     * 
     * @param lista - lista a ter seus elementos ordenados.
     */
    void shellSortOgLanguage(Lista* lista) {
        int h = 1;

        do { h = (h * 3) + 1; } while (h < lista->n);

        do {
            h /= 3;
            for(int cor = 0; cor < h; cor++){
                insercaoPorCor(lista, lista->n, cor, h);
            }
        } while (h != 1);
    }


    /**
     * Serve como criterio de desempate de uma ordenacao feita 
     * anteriormente sobre o atributo original_language. Reordena, 
     * em ordem alfabetica, os items da lista que compartilham do 
     * mesmo valor para o atributo original_language.
     */
    void selectionSortName(Lista* lista){
        Serie tmp;

        for (int i = 0; i < (lista->n - 1); i++) {
            int menor = i;

            if ( strcmp( stringTrim2(lista->series[i].original_language) , stringTrim2(lista->series[i+1].original_language) ) == 0 ) {
                for (int j = (i + 1); j < lista->n && strcmp( stringTrim2(lista->series[menor].original_language) , stringTrim2(lista->series[j].original_language) ) == 0; j++) {
                    if (strcmp( stringTrim2(lista->series[menor].name) , stringTrim2(lista->series[j].name) ) > 0) {
                       menor = j;
                    }
                    comp_num = comp_num + 2; //for e if
                } comp_num++;

                //swap(menor,i)
                tmp = lista->series[menor];
                lista->series[menor] = lista->series[i];
                lista->series[i] = tmp;
                mov_num = mov_num + 3;
            } comp_num++;  
        }  
    }


    //Verifica se a string recebida corresponde a "FIM"
    bool isFim (char* s){
        return (s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
    }
    

    int main ( ){
        int i = 0;
        int n = 0;
        int qtdOps = 0;
        char blankSpace;
        const char dir [50] = "/tmp/series/";
        char path [200];
        char entrada [100];
        Lista lista = new_Lista();
        Serie item;
        Serie clone;
        FILE* fp;
        clock_t start, end;
        double exec_time;
        
        //Leitura da primeira parte da entrada padrao
        do {
            //Garante que os vetores entrada e path fiquem limpos/sem lixo para cada ciclo 
            memset(entrada, 0, 100);
            memset(path, 0, 200);
            strncpy(path,dir,sizeof(dir));
            //strcpy(path,dir);

            fgets(entrada, 100, stdin);
            setbuf(stdin, NULL);
            //Elimina o \n da leitura feita pelo fgets
            if(entrada[strlen(entrada)-1] == '\n'){ entrada[strlen(entrada)-1] = '\0'; }     
            if (!isFim(entrada)){
                //Concatena o nome do arquivo com o diretorio em que ele se encontra
                for(i = 0; i < strlen(entrada); i++){
                    path[12+i] = entrada[i];
                }
                //Apos finalizada a montagem do caminho para o arquivo, posicionar manualmente o fim da string
                path[12+i] = '\0';
                item = read(path);         
                clone = clonar(item);
                inserir(&lista, clone, n);
                n++; 
            }              
        } while (!isFim(entrada));

        comp_num = 0;
        mov_num = 0;
        start = clock();
        shellSortOgLanguage(&lista);
        selectionSortName(&lista);
        end = clock();

        mostrar(&lista);
        free(lista.series);

        fp = fopen("matricula_shellsort.txt","w");
        exec_time = ( (double) end-start ) / 1000.0;
        fprintf(fp,"738811\t%d\t%d\t%f", comp_num, mov_num, exec_time);
        fclose(fp);
    }