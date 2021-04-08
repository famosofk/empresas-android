# empresas-android

#### Fork: Infelizmente não sei fazer fork no bitbucket, então criei esse repositório no Github. Espero que não seja problema.

## Bibliotecas adicionadas:

### Navigation Componenent: 
Básica, para fazer o sistema de navegação entre os fragments.

### Coroutines: 
Foi solicitado a utilização, além de que, como é necessário fazer requests para a API, necessário.

### retrofit: 
Comunicar com a API.

### Glide: 
Load e cache das imagens.

### Hilt: 
Biblioteca de DI para android. Entretanto, não foi utilizada porque encontrei um estranho bug na hora de injetar o retrofit no ViewModel.Estou acostumado a utiliza-la e implementei corretamente, acredito. Posso ter deixado algum detalhe escapar o que ocasionou o bug, mas devido ao curto prazo não consegui investigar, então optei por criar duas instâncias do retrofit mesmo sendo uma péssima prática, por ser algo muito custoso.

### Testes:
Foi solicitado, então implementei algumas bibliotecas e até escrevi o projeto pronto para aplicar TDD. Porém, novamente, devido ao prazo, tive que abrir mão disso. Quero só fazer um parêntese sobre a biblioteca Truth da Google que permite escrever testes muito mais legíveis.

## Como executar a aplicação:
### 1) Clone o repositório
### 2) Abra-o no Android Studio.
### 3) Execute em um emulador ou conectado a um dispositivo android

## Observações:
### LiveData
Utilizei LiveData porque foi solicitado diretamente, porém eu prefiro utilizar StateFlow porque é uma ferramenta mais poderosa.

### Material Design
O design aplicado foi totalmente baseado no disponibilizado pelo Zeplin. 

### Arquitetura
Foi utilizado o MVVM. Com exceção do último fragment, pois ele simplesmente exibe as informações da empresa, então seria um pouco de exagero.

### Mudanças:
Particularmente, eu não vi sentido algum em utilizar as rotas da API para fazer as consultas, então eu optei por fazer um fetch e salvar todas as empresas localmente. Quando o usuário buscava por algum termo, a lista exibida era filtrada. Preferi fazer assim, pois tinha uma quantidade pequena de dados, então com 2 segundos tudo estava disponível ao usuário e proporciona uma experiência muito mais satisfatória, evitando inúmeras requisições.

### Tempo
Eu acredito que 3 dias é prazo mais que suficiente pra desenvolver a aplicação proposta, porém por ser três dias da semana, eu tive pouco tempo para trabalhar no projeto. Cerca de 5 horas, pois trabalho e estudo. Porém, se eu tivesse a primeira coisa que faria seria implementar persistência de dados, utilizando o Room para que não fosse necessário comunicar tanto com a api. Se fosse um projeto meu, eu adicionaria um campo updated_at nela e com base na data enviada pelo android eu mandaria apenas a diff da entidade, diminuindo bastante o tráfego de dados. 
Além disso, eu investigaria o motivo do problema que tive com o Hilt, basicamente, estava dizendo que não forneci o construtor com inject, porém eu o fiz.
Escreveria testes para aplicação. Por ser algo bastante simples, acho que poderíamos aplicar testes de ponta a ponta, pois mesmo demorando bastante pra executar, são poucas coisas a serem testadas. Claro que pensando em expansão seria inteligente cobrir tudo com unitários.
Por fim, eu melhoraria o design da aplicação, eu trabalhei cerca de 6 horas nela e metade disso foi no design, porém não tive a oportunidade para seguir todas as guidelines, como, por exemplo, a expansão/compressão da toolbar no scroll.
