package Encomenda;

public class Voucher {
    Float desconto;
    String titulo;
    String descricao;
    String data_validade;

    public Voucher(Float desconto, String titulo, String descricao, String data_validade){
        this.desconto = desconto;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data_validade = data_validade;
    }

    public Float getDesconto(){
        return desconto;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getDescricao(){
        return descricao;
    }

    public String getData_validade(){
        return data_validade;
    }
}
