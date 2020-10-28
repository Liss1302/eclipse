# eclipse
Exercícios e Projetos Java
Utilizar IDE eclipse na view JavaEE
Os projetos Desktop manipulam arquivos internos de texto tipo CSV
Nos projetos Web (JSP) as dependências estão na pasta WebCOntent/lib
Como trata-se de aulas conceituais não utilizo nenhum gerenciador de dependencias, como Mavem por exemplo.
//Para eu lembrar
$ git config user.name ""
$ git config user.email ""
//Iniciar um repositório
$ git init
//Ou clonar um Repositório
$ git clone <repositorio>
// Adicionar alterações
$ git add .
//Criar um novo COMMIT
$ git commit -m "Mensagem"
//Enviar os dados para o repositório Remoto
$ git push
//Voltar para um commit especifico (Pegar o código do commit no repositório remoto "github")
$ git checkout <commit que necesita voltar>
$ git reset --hard <commit que necesita voltar>
//OBS: Caso esteja colonando repositório de outra pessoa e depois queira enviar para um novo repositório meu, basta excluir o diretório .git e usar o comando
$ git init
