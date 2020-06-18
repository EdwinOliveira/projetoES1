package BD;

import Encomenda.*;
import Stock.Itens_Stock;
import Stock.Livro;
import Utilizadores.*;

import java.util.ArrayList;

public class Repositorio_Ablazon {
    private static ArrayList<Encomenda> encomendas = new ArrayList<>();
    private static ArrayList<Utilizador> utilizadores = new ArrayList<>();
    private static ArrayList<Itens_Stock> livros = new ArrayList<>();
    private static ArrayList<Estado_Encomenda> estados = new ArrayList<>();
    private static ArrayList<Feedback_Encomenda> feedbacks = new ArrayList<>();
    private static ArrayList<Voucher> vouchers = new ArrayList<>();

    public static Integer getNextIdEncomenda(){
        if(encomendas.size() == 0)
            return 1;
        else
            return encomendas.get(encomendas.size() - 1).getId() + 1;
    }

    public static Integer getNextIdUtilizador(){
        if(utilizadores.size() == 0)
            return 1;
        else
            return utilizadores.get(utilizadores.size() - 1).getId() + 1;
    }

    public static Integer getNextIdItens_Stock(){
        if(livros.size() == 0)
            return 1;
        else
            return livros.get(livros.size() - 1).getId() + 1;
    }

    public static Integer getNextIdEstado_Encomenda(){
        if(estados.size() == 0)
            return 1;
        else
            return estados.get(estados.size() - 1).getId() + 1;
    }

    public static Integer getNextIdFeedback_Encomenda(){
        if(feedbacks.size() == 0)
            return 1;
        else
            return feedbacks.get(feedbacks.size() - 1).getId() + 1;
    }

    public static Integer getNextIdVoucher(){
        if(vouchers.size() == 0)
            return 1;
        else
            return vouchers.get(vouchers.size() - 1).getId() + 1;
    }

    public static boolean adicionarEncomenda(Encomenda encomenda){
        if (encomenda == null)
            return false;

        if (encomendas.size() == 0)
            encomenda.setId(1);
        else
            encomenda.setId(encomendas.get(encomendas.size()-1).getId() + 1);

        encomendas.add(encomenda);

        return true;
    }

    public static Boolean adicionarUtilizador(Utilizador utilizador){
        if(UtilizadorExiste(utilizador))
            return false;

        utilizadores.add(utilizador);
        return true;
    }

    public static void adicionarLivro(Itens_Stock livro){
        livros.add(livro);
    }

    public static void adicionarEstados(String estado_encomenda){
        estados.add(new Estado_Encomenda(getNextIdEstado_Encomenda(), estado_encomenda));
    }

    public static Utilizador getUserId(Integer Id){
        for(Integer i = 0; i < utilizadores.size(); i++){
            Utilizador user = utilizadores.get(i);
            if(user.getId().equals(Id)){
                return user;
            }
        }

        return null;
    }

    public static Utilizador getUserUserName(String userName){
        for(Integer i = 0; i < utilizadores.size(); i++){
            Utilizador user = utilizadores.get(i);
            if(user.getNomeUtilizador().equals(userName)){
                return user;
            }
        }

        return null;
    }

    public static void adicionarVouchers(Voucher voucher){
        vouchers.add(voucher);
    }

    public static ArrayList<Encomenda> getEncomendasCliente(Funcionario_Biblioteca funcionario_biblioteca){
        ArrayList<Encomenda> resultado = new ArrayList<>();

        for(Integer i = 0; i < encomendas.size(); i++){
            Encomenda aux = encomendas.get(i);
            if(aux.getCliente() == funcionario_biblioteca)
                resultado.add(aux);
        }

        return resultado;
    }

    public static Encomenda getEncomendaId(Integer id){

        for(Integer i = 0; i < encomendas.size(); i++){
            Encomenda aux = encomendas.get(i);
            if(aux.getId() == id)
                return aux;
        }

        return null;
    }

    public static Itens_Stock getLivroTituloAutorAnoEdicao(String titulo, String autor, Integer ano_edicao){
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

    public static Itens_Stock getLivroISBN(String ISBN){
        for(Integer i = 0; i < livros.size(); i++){
            Itens_Stock aux = livros.get(i);
            Livro aux2 = aux.getLivro();

            if(aux2.getISBN().equals(ISBN)) {
                return aux;
            }
        }

        return null;
    }

    public static Feedback_Encomenda getFeedbackEncomendaCliente(Integer id){
        for(Integer i = 0; i < feedbacks.size(); i++){
            Feedback_Encomenda aux = feedbacks.get(i);
            if(aux.getId() == id)
                return aux;
        }

        return null;
    }

    public static Estado_Encomenda getEstadoEncomendaID(Integer id){
        for(Integer i = 0; i < estados.size(); i++){
            Estado_Encomenda aux = estados.get(i);
            if(aux.getId() == id)
                return aux;
        }

        return null;
    }

    public static boolean adicionarFeedbackEncomendaCliente(Feedback_Encomenda feedback_encomenda){
        if(FeedbackExiste(feedback_encomenda))
            return false;

        feedbacks.add(feedback_encomenda);
        return true;
    }

    private static Boolean UtilizadorExiste(Utilizador user){
        for(Integer i = 0; i < utilizadores.size(); i++) {
            Utilizador aux = utilizadores.get(i);
            if (aux.getNif().equals(user.getNif()) || aux.getNomeUtilizador().equals(user.getNomeUtilizador()) || aux.getEmail().equals(user.getEmail()) || aux.getTelefone().equals(user.getTelefone())) {
                return true;
            }
        }
        return false;
    }

    private static Boolean FeedbackExiste(Feedback_Encomenda feedback_encomenda){
        for(Integer i = 0; i < feedbacks.size(); i++) {
            Feedback_Encomenda aux = feedbacks.get(i);
            if (aux.getEncomenda().equals(feedback_encomenda.getEncomenda())) {
                return true;
            }
        }
        return false;
    }

    public static Voucher getVoucherID(Integer id){
        for(Integer i = 0; i < vouchers.size(); i++){
            Voucher aux = vouchers.get(i);
            if(aux.getId() == id)
                return aux;
        }

        return null;
    }

    public static ArrayList<Itens_Stock> getStock(){return livros;}
}
