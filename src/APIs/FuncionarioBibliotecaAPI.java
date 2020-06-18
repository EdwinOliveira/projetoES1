package APIs;

import BD.Repositorio_Ablazon;
import Encomenda.*;
import Stock.Itens_Encomenda;
import Utilizadores.Funcionario_Biblioteca;
import Utilizadores.Utilizador;
import interfaces.FuncionarioBibliotecaInterface;

import java.util.ArrayList;

import static extras.funcoes_uteis.*;

public class FuncionarioBibliotecaAPI implements FuncionarioBibliotecaInterface {
    private Integer idUser;

    public FuncionarioBibliotecaAPI(){

    }

    public void login(String userName, String password){
        Utilizador user = Repositorio_Ablazon.getUserUserName(userName);

        if (user != null){
            if (user instanceof Funcionario_Biblioteca){
                if (user.getPalavraPasse().equals(password)) {
                    this.idUser = user.getId();
                    print("Bem Vindo " + user.getNome() + "!");
                }
                else {
                    this.idUser = null;
                    print("Login Incorreto!");
                }
            }
            else {
                this.idUser = null;
                print("Você não está autorizado a usar esta API!");
            }
        }
    }

    public void perfilUtilizador(){
        Utilizador user = Repositorio_Ablazon.getUserId(idUser);
        user.show();
    }

    public void registarNovaEncomenda(ArrayList<Itens_Encomenda> livros, Voucher voucher){
        if(this.idUser != null) {
            if (voucher != null){
                if(dataAnt_e_Pos(getActualDate(), voucher.getData_validade()) <= 0)
                {
                    print("O voucher que está a tentar usar já não é válido! Expirou em [" + voucher.getData_validade() + "]");
                    return;
                }
            }
            Encomenda encomenda = new Encomenda(livros, (Funcionario_Biblioteca) Repositorio_Ablazon.getUserId(idUser));
            encomenda.setId(Repositorio_Ablazon.getNextIdEncomenda());
            encomenda.setEstado(Repositorio_Ablazon.getEstadoEncomendaID(1));
            encomenda.setVoucher(voucher);
            encomenda.setData_encomenda(getActualDate());

            Repositorio_Ablazon.adicionarEncomenda(encomenda);
            print("Encomenda adicionada. O id dela é [" + encomenda.getId() + "]!");
        }
        else
            print("Faça login na API primeiro!");
    }

    public void show(){
        Repositorio_Ablazon.getUserId(idUser).show();
    }

    public void showEncomendas(){
        ArrayList<Encomenda> aux = Repositorio_Ablazon.getEncomendasCliente((Funcionario_Biblioteca) Repositorio_Ablazon.getUserId(idUser));

        for(Integer i = 0; i < aux.size(); i++){
            aux.get(i).show();
            if(i != aux.size() - 1)
            print("-------------------------------");
        }
    }

    public void fornecerFeadbackEncomenda(Encomenda encomenda, String feedback, Integer avaliacao){
        if(this.idUser != null) {
            if (encomenda != null){
                if (encomenda.getEstado().getId() != 4){
                    print("Esta encomenda ainda não foi entregue!");
                    return;
                }
                else if (encomenda.getCliente().getId() != idUser){
                    print("Esta encomenda não lhe pertence!");
                    return;
                }
            }
            else {
                print("Encomenda inválida!");
                return;
            }

            if(avaliacao < 0 || avaliacao > 5) {
                print("A entrega precisa de ser avaliada entre 0 e 5 (números inteiros)!");
                return;
            }

            Feedback_Encomenda aux = new Feedback_Encomenda(encomenda, feedback, avaliacao, (Funcionario_Biblioteca) Repositorio_Ablazon.getUserId(idUser));
            aux.setId(Repositorio_Ablazon.getNextIdFeedback_Encomenda());

            if(Repositorio_Ablazon.adicionarFeedbackEncomendaCliente(aux))
                print("Feedback adicionado! Obrigado pela sua colaboração!");
            else
                print("Só pode dar feedback de uma encomenda apenas uma vez!");
        }
        else
            print("Faça login na API primeiro!");
    }
}
