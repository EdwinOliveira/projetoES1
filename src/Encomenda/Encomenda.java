package Encomenda;

import BD.Repositorio_Ablazon;
import Stock.Itens_Encomenda;
import Utilizadores.Funcionario_Biblioteca;

import java.util.ArrayList;
import interfaces.EmailInterface;
import APIs.EmailAPI;

import static extras.funcoes_uteis.print;

public class Encomenda {
    Integer id;
    Funcionario_Biblioteca cliente;
    ArrayList<Itens_Encomenda> livros;
    String data_prevista;
    String data_encomenda;
    Estado_Encomenda estado;
    Voucher voucher;

    public Encomenda(ArrayList<Itens_Encomenda> livros, Funcionario_Biblioteca cliente) {
        this.livros = livros;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Funcionario_Biblioteca getCliente() {
        return cliente;
    }

    public ArrayList<Itens_Encomenda> getLivros() {
        return livros;
    }

    public void setLivros(ArrayList<Itens_Encomenda> livros) {
        this.livros = livros;
    }

    public String getData_prevista() {
        return data_prevista;
    }

    public String getData_encomenda() {
        return data_encomenda;
    }

    public void setData_prevista(String data_prevista) {
        this.data_prevista = data_prevista;
    }

    public void setData_encomenda(String data_encomenda) {
        this.data_encomenda = data_encomenda;
    }

    public Estado_Encomenda getEstado() {
        return estado;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public void setEstado(Estado_Encomenda estado) {
        this.estado = estado;

        EmailInterface apiEmail = new EmailAPI();
        apiEmail.setOrigem("Ablazon@email.com");
        apiEmail.setDestino(cliente.getEmail());

        if (estado.getId() != 4)
            apiEmail.setMensagem("Esta mensagem foi gerada pelo sistema automaticamente!\n" +
                    "Caro " + cliente.getNome() + ",\n" +
                    "A sua encomenda com o ID [" + id + "] está agora no seguinte estado: " + estado.getDesignacao() + "\n" +
                    "Você pode fazer o tracking da mesma em www.ablazon.com/track_by_id\n" +
                    "A data prevista de entrega é [" + data_prevista + "]");
        else
            apiEmail.setMensagem("Esta mensagem foi gerada pelo sistema automaticamente!\n" +
                    "Caro " + cliente.getNome() + ",\n" +
                    "A sua encomenda com o ID [" + id + "] está agora no seguinte estado: " + estado.getDesignacao() + "\n" +
                    "Agradeciamos que preenche-se o nosso inquérito de satisfação que se encontra agora disponível no nosso site www.ablazon.com/inqueritos");

        apiEmail.enviarEmail();
    }

    public void setEstado(Integer idEncomenda) {
        this.estado = Repositorio_Ablazon.getEstadoEncomendaID(1);

        EmailInterface apiEmail = new EmailAPI();
        apiEmail.setOrigem("Ablazon@email.com");
        apiEmail.setDestino(cliente.getEmail());

            apiEmail.setMensagem("Esta mensagem foi gerada pelo sistema automaticamente!\n" +
                    "Caro " + cliente.getNome() + ",\n" +
                    "A sua encomenda com o ID [" + idEncomenda + "] Teve de ser repartida em duas por causa do nosso stock. pedimos desculpa pelo incómodo!" + "\n" +
                    "Foi criada de forma automática uma nova encomenda com o ID [" + id + "] onde estão incluídos o resto dos produtos. Estado  da encomenda: " + estado.getDesignacao() + "\n" +
                    "Você pode fazer o tracking da mesma em www.ablazon.com/track_by_id\n" +
                    "A data prevista de entrega é [" + data_prevista + "]");

        apiEmail.enviarEmail();
    }

    public void show() {
        if (voucher != null)
            print(
                    "Id: " + id + "\n" +
                            "Cliente: " + cliente.getId() + "\n" +
                            "Data encomenda: " + data_encomenda + "\n" +
                            "Data prevista: " + data_prevista + "\n" +
                            "Estado: " + estado.getDesignacao() + "\n" +
                            "Voucher: " + voucher.getId() + "\n"
            );
        else
            print(
                    "Id: " + id + "\n" +
                            "Cliente: " + cliente.getId() + "\n" +
                            "Data encomenda: " + data_encomenda + "\n" +
                            "Data prevista: " + data_prevista + "\n" +
                            "Estado: " + estado.getDesignacao() + "\n"
            );

        for (Integer i = 0; i < livros.size(); i++) {
            livros.get(i).show();
        }
        print("\n");
    }
}