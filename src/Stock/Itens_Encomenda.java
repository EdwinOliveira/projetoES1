package Stock;

public class Itens_Encomenda {
    Livro livro;
    Integer quantidade;

    public Itens_Encomenda(Livro livro, Integer quantidade){
        this.livro = livro;
        this.quantidade = quantidade;
    }

    public Livro getLivro(){
        return livro;
    }

    public Integer getQuantidade(){
        return quantidade;
    }
}
