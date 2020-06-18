package interfaces;

import Encomenda.Encomenda;
import Encomenda.*;
import Stock.Itens_Encomenda;

import java.util.ArrayList;

public interface FuncionarioBibliotecaInterface {
    public void login(String userName, String password);
    public void perfilUtilizador();
    public void registarNovaEncomenda(ArrayList<Itens_Encomenda> livros, Voucher voucher);
    public void show();
    public void showEncomendas();
    public void fornecerFeadbackEncomenda(Encomenda encomenda, String feedback, Integer avaliacao);
}
