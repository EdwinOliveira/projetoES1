package Stock;

import static extras.funcoes_uteis.print;

public class Itens_Stock {
    Integer id;
    Livro livro;
    Integer quantidade;

    public Itens_Stock(Livro livro, Integer quantidade){
        this.livro = livro;
        this.quantidade = quantidade;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
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

    public void show(){
        print(quantidade + " livros com ISBN [" + livro.getISBN() + "]");
    }
}
