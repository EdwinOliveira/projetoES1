package interfaces;

import Encomenda.Encomenda;
import Stock.Livro;
import Utilizadores.Utilizador;

public interface FuncionarioAblazonInterface {
    public void registarNovoUtilizador(Utilizador user);
    public void login(String userName, String password);
    public void perfilUtilizador();
    public void registarNovoLivro(Integer quantidade, String ISBN, String titulo, String autor, String editora, Integer ano_edicao);
    public void registarNovoLivro(Integer quantidade, String ISBN);
    public void registarNovoEstadoEncomenda(String estado);
    public void registarNovoVoucher(Float desconto, String titulo, String descricao, String data_validade);
    public void processarEncomenda(Integer id, String data_prevista);
    public void enviarEncomenda(Integer id);
    public void show();
    public void showStock();
}
