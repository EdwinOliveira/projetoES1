package Encomenda;

import Stock.Itens_Encomenda;
import Utilizadores.Funcionario_Biblioteca;

import java.util.ArrayList;
import interfaces.EmailInterface;
import APIs.EmailAPI;

public class Encomenda {
    Integer id;
    Funcionario_Biblioteca cliente;
    ArrayList<Itens_Encomenda> livros;
    String data_prevista;
    String data_encomenda;
    Estado_Encomenda estado;
    Voucher voucher;

    public Encomenda(ArrayList<Itens_Encomenda> livros, Funcionario_Biblioteca cliente){
        this.livros = livros;
        this.cliente = cliente;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Funcionario_Biblioteca getCliente(){
        return cliente;
    }

    public ArrayList<Itens_Encomenda> getLivros(){
        return livros;
    }

    public String getData_prevista(){
        return data_prevista;
    }

    public String getData_encomenda(){
        return data_encomenda;
    }

    public Estado_Encomenda getEstado(){
        return estado;
    }

    public Voucher getVoucher(){
        return voucher;
    }

    public void setVoucher(Voucher voucher){
        this.voucher = voucher;
    }

    public void setEstado(Estado_Encomenda estado){
        this.estado = estado;

        EmailInterface apiEmail = new EmailAPI();
        apiEmail.setOrigem("Ablazon@email.com");
        apiEmail.setDestino(cliente.getEmail());
        apiEmail.setMensagem("Esta mensagem foi gerada pelo sistema automaticamente!\n" +
                "Caro " + cliente.getNome() + ",\n" +
                "A sua encomenda com o ID [" + id + "] está agora no seguinte estado: " + estado.getDesignacao());
        apiEmail.enviarEmail();
    }
}
