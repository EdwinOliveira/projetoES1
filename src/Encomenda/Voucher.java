package Encomenda;

import static extras.funcoes_uteis.print;

public class Voucher {
    Integer id;
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

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
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

    public void show(){
        print(
            "Id: " + id + "\n" +
            "Título: " + titulo + "\n" +
            "Descrição: " + descricao + "\n" +
            "Desconto: " + desconto + "\n" +
            "Data de validade: " + data_validade + "\n"
        );
    }

}
