package Encomenda;

import Stock.Livro;
import Utilizadores.Funcionario_Biblioteca;

public class Feedback_Encomenda {
    Integer id;
    Encomenda encomenda;
    String feedback;
    Integer avaliacao;
    Funcionario_Biblioteca cliente;

    public Feedback_Encomenda(Encomenda encomenda, String feedback, Integer avaliacao, Funcionario_Biblioteca cliente){
        this.encomenda = encomenda;
        this.feedback = feedback;
        this.avaliacao = avaliacao;
        this.cliente = cliente;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Encomenda getEncomenda(){
        return encomenda;
    }

    public String getFeedback(){
        return feedback;
    }

    public Integer getAvaliacao(){
        return avaliacao;
    }

    public Funcionario_Biblioteca getCliente(){
        return cliente;
    }
}
