package Stock;

import static extras.funcoes_uteis.print;

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

    public void setQuantidade(Integer quantidade){
        this.quantidade = quantidade;
    }

    public void show(){
        print(quantidade + " livros com ISBN [" + livro.getISBN() + "]");
    }
}
