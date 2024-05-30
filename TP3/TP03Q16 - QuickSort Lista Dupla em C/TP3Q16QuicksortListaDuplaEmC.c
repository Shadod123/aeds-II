/*----------------------------------------------------------------
Atividade: TP03Q16 - QuickSort Lista Dupla em C
------------------------------------------------------------------
Aluno: Caio Eduardo Ramos Araes
------------------------------------------------------------------
Matricula: 738811
----------------------------------------------------------------*/
#include<stdlib.h>
#include<stdio.h>
#include<string.h>
#include<stdbool.h>
#include<limits.h>

    int comp_num = 0;
    int mov_num = 0;

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
     * Celula dupla (lista dupla)
     */
    typedef struct CelulaDupla{
        Serie elemento;
        struct CelulaDupla *prox, *ant;
    }CelulaDupla;
    
    CelulaDupla* new_celula_dupla(Serie elemento){
        CelulaDupla *temp = (CelulaDupla*)malloc(sizeof(CelulaDupla));
        temp->elemento = elemento;
        temp->ant = NULL;
        temp->prox = NULL;

        return temp;
    }


    /**
     * Lista dupla dinamica
     */
    typedef struct ListaDupla{
        struct CelulaDupla *primeiro, *ultimo;
        int size;
    } ListaDupla;
    

    ListaDupla new_lista_dupla(){
        Serie x;
        ListaDupla temp;
        temp.primeiro = temp.ultimo = new_celula_dupla(x);
        temp.size = 0;

        return temp;
    }
    

    int size_lista_dupla(ListaDupla *l){
        return l->size;
    }
    

    void insert_begin_dupla(ListaDupla *l, Serie elemento){
        Serie x;
        CelulaDupla *temp = new_celula_dupla(x);
        temp->prox = l->primeiro;

        l->primeiro->elemento = elemento; 
        l->primeiro->ant = temp;   
        l->primeiro = temp;
        l->size++;
    }
    

    void insert_end_dupla(ListaDupla *l, Serie elemento){    
        l->ultimo->prox = new_celula_dupla(elemento);
        l->ultimo->prox->ant = l->ultimo;
        l->ultimo = l->ultimo->prox;
        l->size++;
    }
    

    void insert_at_dupla(ListaDupla *l, Serie elemento, int pos){
        if(pos < 0 || pos > l->size)
            printf("Erro ao tentar inserir na posicao (%d/ tamanho = %d) invalida!", pos, l->size);
        else if (pos == 0)
            insert_begin_dupla(l, elemento);
        else if (pos == l->size)
            insert_end_dupla(l, elemento);
        else{

            CelulaDupla *ant = l->primeiro;
            for(int i=0; i<pos;i++) 
                ant = ant->prox;

            CelulaDupla *temp = new_celula_dupla(elemento);  
            temp->prox = ant->prox;
            temp->prox->ant = temp;
            temp->ant = ant;
            ant->prox = temp;
            l->size++;
        }
    }
    

    Serie remove_end_dupla(ListaDupla *l){
        if(l->primeiro == l->ultimo){
            printf("\nA lista esta vazia!\n");
        }

        CelulaDupla *temp = l->ultimo;
        Serie elemento = temp->elemento;

        l->ultimo = l->ultimo->ant;
        l->ultimo->prox = NULL;
        l->size--;

        free(temp);

        return elemento;
    }
    

    Serie remove_at_dupla(ListaDupla *l, int pos){
        if(l->primeiro == l->ultimo){
            printf("\nA lista esta vazia!\n");
        }else if(pos < 0 || pos > l->size-1)
            printf("Erro ao tentar remover item da posicao (%d/ tamanho = %d) invalida!", pos, l->size);
        else if(pos == l->size-1)
            remove_end_dupla(l);
        else{

            CelulaDupla *ant = l->primeiro;
            for(int i=0; i<pos;i++) 
                ant = ant->prox;

            CelulaDupla *temp = ant->prox;  
            Serie elemento = temp->elemento;

            temp->prox->ant = ant;
            ant->prox = temp->prox;
            free(temp);       

            l->size--;

            return elemento;
        }
    }
    

    Serie remove_begin_dupla(ListaDupla *l){
        return remove_at_dupla(l, 0);
    }
    

    bool pesquisar_lista_dupla(ListaDupla *l, Serie elemento){
        CelulaDupla *i;

        for (i = l->primeiro->prox; i != NULL; i = i->prox)
            if(i->elemento.name == elemento.name)
                return true;

        return false;
    }
    

    void print_lista_dupla(ListaDupla *l){
        CelulaDupla *i;

        for (i = l->primeiro->prox; i != NULL; i = i->prox)
        {
            print(i->elemento);
        }
    }
    

    void print_lista_dupla_inverso(ListaDupla *l){
        CelulaDupla *i;

        for (i = l->ultimo; i != l->primeiro; i = i->ant)
        {
             print(i->elemento);
        }
    }
    

    void delete_lista_dupla(ListaDupla *l){
        while(l->size > 0)
            remove_at_dupla(l,0);
        free(l->primeiro);
    }


    /**
	 * Verifica se uma celula "a" se encontra posicionada
     * antes de uma celula "b" em uma lista dupla.
     * 
	 * @param CelulaDupla a celula 1 da comparacao
     * @param CelulaDupla b celula 2 da comparacao
	 * @return <code>true</code> se (Posicao de a < Posicao de b),
     *         <code>false</code> em caso contrario.
	 */
	bool isCellLower(ListaDupla* lista, CelulaDupla a, CelulaDupla b) {
		bool resp = false;

        if (a != b){
            for (CelulaDupla* i = &a; i != NULL; i = i->prox) {
                if(i == &b){
                    resp = true;
                    i = lista->ultimo;
                }
            }
        }
		
		return resp;
	}
 
    /**
     * Ordena, em ordem alfabetica, os elementos da lista, 
     * com base no atributo country e por meio do algoritmo
     * de ordenacao QuickSort.
     * 
     * @param CelulaDupla esq inicio da lista a ser ordenada
     * @param CelulaDupla dir fim da lista a ser ordenada
     */
    void quickSortCountry(ListaDupla *lista, CelulaDupla esq, CelulaDupla dir) {
        CelulaDupla i = esq, j = dir;
        CelulaDupla* pivo = &esq;
        int n = 0;
        //Encontra o pivo ao deslocar, a partir da celula inicial da lista (esq),
        //a metade do deslocamento necessario para ir da celula esq ate' a dir 
        for (CelulaDupla* x = &esq; x != &dir; x = x->prox, n++);
        for (int y = (n+1)/2; y > 0; pivo = pivo->prox, y--);
        
        while ( isCellLower(lista,i,j) || i == j ) {
            while ( strcmp( stringTrim(i.elemento.country,strlen(i.elemento.country)), stringTrim(pivo->elemento.country,strlen(pivo->elemento.country)) ) < 0 ){
                comp_num++;
                i = i.prox;
            } comp_num++;
            while ( strcmp ( stringTrim(j.elemento.country,strlen(j.elemento.country)), stringTrim(pivo->elemento.country,strlen(pivo->elemento.country)) ) > 0 ){ 
                comp_num++;
                j = j.ant;
            } comp_num++;

            if ( isCellLower(lista,i,j) || i == j ) {
                //swap(i,j);
                Serie tmp = i.elemento;
                tmp = i.elemento;
                i.elemento = j.elemento;
                j.elemento = tmp;
                mov_num += 3;

                i = i.prox;
                j = j.ant;
            }
        }
        
        if (isCellLower(lista,esq,j))  quickSortCountry(esq, j);
        if (isCellLower(lista,i,dir))  quickSortCountry(i, dir);
    }


    /**
	 * Algoritmo de ordenacao Quicksort.
	 */
    void quickSort(ListaDupla* lista) {
        quickSortCountry(lista, lista->primeiro, lista->ultimo);
    }


    /**
     * Serve como criterio de desempate de uma ordenacao feita 
     * anteriormente sobre o atributo country. Reordena, em ordem
     * alfabetica, os items da lista que compartilham do mesmo 
     * valor para o atributo country.
     */
    void selectionSortName(){
        for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
            CelulaDupla menor = i;

            if ( i.prox != null && stringTrim(i.elemento.getCountry()).equals(stringTrim(i.prox.elemento.getCountry())) ) {
                for (CelulaDupla j = i.prox; j != null && stringTrim(menor.elemento.getCountry()).equals(stringTrim(j.elemento.getCountry())); j = j.prox) {
                    if (menor.elemento.getName().compareTo(j.elemento.getName()) > 0) {
                       menor = j;
                    }
                    comp_num += 2; //for e if
                } comp_num++;
    
                //swap(menor,i)
                Serie tmp = menor.elemento;
                tmp = menor.elemento;
                menor.elemento = i.elemento;
                i.elemento = tmp;
                mov_num += 3;
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
        ListaDupla lista_dupla;
        start();
        Serie item;
        Serie clone;
        
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
                inserir(clone);
                n++; 
            }              
        } while (!isFim(entrada));

        mostrar();
        //free(pilha.prox);
    }