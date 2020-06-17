import Stock.Itens_Stock;
import Stock.Livro;
import Utilizadores.Funcionario_Ablazon;
import Utilizadores.Funcionario_Biblioteca;
import static extras.funcoes_uteis.*;
import interfaces.*;

public class main {
    public static void main(String args[]){
        Repositorio_Ablazon repo = new Repositorio_Ablazon();

        //criacao de utilizadores de teste
        //funcionarios
        repo.adicionarUtilizador(new Funcionario_Ablazon("José Carlos", "123456", "José Carlos", "rua nao sei", 123456786, 921234567, "email@email.com"));
        repo.adicionarUtilizador(new Funcionario_Ablazon("BrunoLopes", "123456", "Bruno Lopes", "rua nao sei", 123456789, 921234567, "email@email.com"));

        //clientes
        repo.adicionarUtilizador(new Funcionario_Biblioteca("PedroCosta", "123456", "Pedro Costa", "rua nao sei", 123456787, 921234567, "email@email.com"));
        repo.adicionarUtilizador(new Funcionario_Biblioteca("GoncaloCosta", "123456", "Goncalo Costa", "rua nao sei", 123456788, 921234567, "email@email.com"));

        //testar os gets user
        repo.getUserNIF(123456788).show();
        repo.getUserNIF(123456789).show();
        repo.getUserNIF(123456787).show();
        repo.getUserNIF(123456786).show();

        //criacao de livros
        Livro livro1 = new Livro("livro 1", "ninguem", "ninguem tambem", "algo", 2020);
        Livro livro2 = new Livro("livro 2", "ninguem", "ninguem tambem", "algo", 2019);

        //registo dos livros no repositorio
        repo.adicionarLivro(livro1, 3);
        ecreverQuantLivros(repo, "livro 1", "ninguem", 2020);
        repo.adicionarLivro(livro2, 0);
        ecreverQuantLivros(repo, "livro 2", "ninguem", 2019);


        //teste das protecoes
        repo.adicionarLivro(livro1, 2);
        repo.removerUmLivro(livro1);
        repo.removerUmLivro(livro2);
        ecreverQuantLivros(repo, "livro 1", "ninguem", 2020);
        ecreverQuantLivros(repo, "livro 2", "ninguem", 2019);

        //criacao de estados das encomendas
        repo.adicionarEstados("A aguardar processamento");
        repo.adicionarEstados("Processada");
        repo.adicionarEstados("Enviada");
        repo.adicionarEstados("Entregue");

        //teste de insercao e incremento do ID
        print("Estado com id = 2: " + repo.getEstadoEncomendaID(2).getDesignacao());
        print("Estado com id = 4: " + repo.getEstadoEncomendaID(4).getDesignacao());
    }

    private static void ecreverQuantLivros(Repositorio_Ablazon repo, String titulo, String autor, Integer ano_edicao){
        Itens_Stock aux;
        aux = repo.getLivroTituloAutorAnoEdicao(titulo, autor, ano_edicao);
        print("quantidade [" + aux.getLivro().getTitulo() + "]: " + aux.getQuantidade());
    }
}
