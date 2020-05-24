package Stock;

public class Itens_Stock {
    Livro livro;
    Integer quantidade;

    public Itens_Stock(Livro livro, Integer quantidade){
        this.livro = livro;
        this.quantidade = quantidade;
    }

    public Livro getLivro(){
        return livro;
    }

    public Integer getQuantidade(){
        return quantidade;
    }

    public void setQuantidade(Integer quantidade){
        this.quantidade = quantidade;
    }
}
