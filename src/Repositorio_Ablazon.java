import Encomenda.*;
import Stock.Itens_Stock;
import Stock.Livro;
import Utilizadores.*;

import java.util.ArrayList;

public class Repositorio_Ablazon {
    private ArrayList<Encomenda> encomendas;
    private ArrayList<Utilizador> utilizadores;
    private ArrayList<Itens_Stock> livros;
    private ArrayList<Estado_Encomenda> estados;
    private ArrayList<Feedback_Encomenda> feedbacks;
    private ArrayList<Voucher> vouchers;

    public Repositorio_Ablazon(){
        encomendas = new ArrayList<>();
        utilizadores = new ArrayList<>();
        livros = new ArrayList<>();
        estados = new ArrayList<>();
        feedbacks = new ArrayList<>();
        vouchers = new ArrayList<>();
    }

    public void adicionarEncomenda(Encomenda encomenda){
        encomendas.add(encomenda);
    }

    public Boolean adicionarUtilizador(Utilizador utilizador){
        if(UtilizadorExisteNIF(utilizador.getNif()))
            return false;

        utilizadores.add(utilizador);
        return true;
    }

    public void adicionarEstados(String estado_encomenda){
        Integer id = 1;
        if(estados.size()!= 0)
            id = estados.get(estados.size()-1).getID() + 1;

        estados.add(new Estado_Encomenda(id, estado_encomenda));
    }

    public void adicionarLivro(Livro livro, Integer quantidade){
        if(quantidade < 0)
            return;

        Itens_Stock aux = null;
        for(Integer i = 0; i < livros.size(); i++){ //se o livro ja estver registado apenas aumenta a quantidade deles
            if(livros.get(i).getLivro().equals(livro)) {
                aux = livros.get(i);
                aux.setQuantidade(aux.getQuantidade() + quantidade);
            }
        }

        if(aux == null){ //se nao existir em stock acrescenta o ao mesmo
            aux = new Itens_Stock(livro, quantidade);
            livros.add(aux);
        }
    }

    public Boolean removerUmLivro(Livro livro){
        Itens_Stock aux = null;
        for(Integer i = 0; i < livros.size(); i++){
            if(livros.get(i).getLivro().equals(livro)) {
                aux = livros.get(i);
                if(aux.getQuantidade() > 0) {
                    aux.setQuantidade(aux.getQuantidade() - 1);
                    return true;
                }
                else
                    return false;
            }
        }

        return false;
    }

    private Boolean UtilizadorExisteNIF(Integer NIF){
        for(Integer i = 0; i < utilizadores.size(); i++) {
            if (utilizadores.get(i).getNif() == NIF) {
                return true;
            }
        }
        return false;
    }

    public Funcionario_Biblioteca getClienteNIF(Integer NIF){
        for(Integer i = 0; i < utilizadores.size(); i++){
            Utilizador user = utilizadores.get(i);
             if(user.tipoUser().equals("FuncionarioBiblioteca")){
                 if(user.getNif().equals(NIF)){
                     return (Funcionario_Biblioteca) user;
                 }
             }
        }

        return null;
    }

    public Funcionario_Ablazon getFuncionarioNIF(Integer NIF){
        for(Integer i = 0; i < utilizadores.size(); i++){
            Utilizador user = utilizadores.get(i);
            if(user.tipoUser().equals("FuncionarioAblazon")){
                if(user.getNif().equals(NIF)){
                    return (Funcionario_Ablazon) user;
                }
            }
        }

        return null;
    }

    public Itens_Stock getLivroTituloAutorAnoEdicao(String titulo, String autor, Integer ano_edicao){
        for(Integer i = 0; i < livros.size(); i++){
            Itens_Stock aux = livros.get(i);
            Livro aux2 = aux.getLivro();
            if(aux2.getTitulo().equals(titulo))
                if(aux2.getAutor().equals(autor))
                    if(aux2.getAnoEdicao().equals(ano_edicao))
                        return aux;
        }

        return null;
    }

    public Estado_Encomenda getEstadoEncomendaID(Integer id){
        for(Integer i = 0; i < estados.size(); i++){
            Estado_Encomenda aux = estados.get(i);
            if(aux.getID() == id)
                return aux;
        }

        return null;
    }
}
