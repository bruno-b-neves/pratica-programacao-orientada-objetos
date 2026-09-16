# Sistema de Gestão de Biblioteca - UNIP

Projeto de atividade prática e desenvolvido em laboratório.
Projeto acadêmico desenvolvido em Java Swing com arquitetura MVC (Model-View-Controller) para cadastro e validação de livros.

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java (JDK 21)
* **Interface Gráfica:** Swing / WindowBuilder
* **Arquitetura:** MVC (Model-View-Controller)
* **IDE:** Eclipse

## 📁 Estrutura do Projeto

* `domain/`: Entidades `Livro`, `Autor` e `Categoria` contendo as regras de negócio e validações de campos obrigatórios.
* `repository/`: Classe `LivroRepository` responsável pelo armazenamento em memória da lista de livros.
* `controller/`: Classe `LivroController` fazendo a ponte entre a interface gráfica e o domínio.
* `view/`: Componentes visuais `BibliotecaFrame` e `FormLivroPanel` isolados da lógica de negócios.
* `main/`: Classe `Main` que inicializa as dependências e lança a interface na Event Dispatch Thread.

## 🚀 Como Executar
1. Clone este repositório: `git clone https://github.com/seu-usuario/seu-repositorio.git`
2. Abra o projeto no Eclipse ou na sua IDE Java de preferência.
3. Execute o arquivo `src/main/Main.java`.
