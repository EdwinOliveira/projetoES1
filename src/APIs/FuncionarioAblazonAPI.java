package APIs;

import BD.Repositorio_Ablazon;
import Encomenda.*;
import Stock.Itens_Encomenda;
import Stock.Itens_Stock;
import Stock.Livro;
import Utilizadores.Funcionario_Ablazon;
import Utilizadores.Utilizador;
import interfaces.FuncionarioAblazonInterface;

import java.util.ArrayList;

import static extras.funcoes_uteis.*;

public class FuncionarioAblazonAPI implements FuncionarioAblazonInterface {
    private Integer idUser;

    public FuncionarioAblazonAPI(){

    }

    public void login(String userName, String password){
        Utilizador user = Repositorio_Ablazon.getUserUserName(userName);

        if (user != null){
            if (user instanceof Funcionario_Ablazon){
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

    public void registarNovoUtilizador(Utilizador user){
        if(this.idUser != null) {
            user.setId(Repositorio_Ablazon.getNextIdUtilizador());
            if (Repositorio_Ablazon.adicionarUtilizador(user))
                print("Utilizador [" + user.getNome() + "] com o nif [" + user.getNif() + "] adicionado com sucesso!");
            else
                print("Já existia registado na base de dados um utilizador com o nif [" + user.getNif() + "]!");
        }
        else
            print("Faça login na API primeiro!");
    }

    public void perfilUtilizador(){
        Utilizador user = Repositorio_Ablazon.getUserId(idUser);
        user.show();
    }

    public void registarNovoLivro(Integer quantidade, String ISBN, String titulo, String autor, String editora, Integer ano_edicao){
        if(this.idUser != null) {
            Itens_Stock livroStock = Repositorio_Ablazon.getLivroISBN(ISBN);

            if (livroStock == null){
                Livro livro = new Livro(titulo, autor, editora, ISBN, ano_edicao);
                livroStock = new Itens_Stock(livro, 0);
                livroStock.setId(Repositorio_Ablazon.getNextIdItens_Stock());
            }

            livroStock.setQuantidade(livroStock.getQuantidade() + quantidade);
            Repositorio_Ablazon.adicionarLivro(livroStock);
            print("Livro registado com sucesso! Possui " + livroStock.getQuantidade() + " livros iguais a este no total!");
        }
        else
            print("Faça login na API primeiro!");
    }

    public void registarNovoLivro(Integer quantidade, String ISBN){
        if(this.idUser != null) {
            Itens_Stock livroStock = Repositorio_Ablazon.getLivroISBN(ISBN);

            if (livroStock == null){
                print("Forneça mais dados acerca deste livro para o podermos adicionar. Ele ainda não está registado na nossa base de dados!");
                return;
            }

            livroStock.setQuantidade(livroStock.getQuantidade() + quantidade);
            Repositorio_Ablazon.adicionarLivro(livroStock);
            print("Livro registado com sucesso! Possui " + livroStock.getQuantidade() + " livros iguais a este no total!");
        }
        else
            print("Faça login na API primeiro!");
    }

    public void registarNovoEstadoEncomenda(String estado){
        if(this.idUser != null) {
            Repositorio_Ablazon.adicionarEstados(estado);
            print("Estado [" + estado + "] registado com sucesso!");
        }
        else
            print("Faça login na API primeiro!");
    }

    public void registarNovoVoucher(Float desconto, String titulo, String descricao, String data_validade){
        if(this.idUser != null) {
            //validar data de validade
            Integer aux = dataAnt_e_Pos(data_validade, getActualDate());
            if (aux == 1 )
            {
                print("Voucher inválido! A data que está a tentar introduzir já é antiga!");
                return;
            }
            else if (aux == -1 )
            {
                print("Voucher inválido! A data que está a tentar introduzir não é válida!");
                return;
            }

            //validar desconto em percentagem (de 0 a 100)
            if(desconto < 0 || desconto > 100)
            {
                print("Voucher inválido! Desconto tem de estar entre 0 e 100 %!");
                return;
            }

            Voucher voucher = new Voucher(desconto, titulo, descricao, data_validade);
            voucher.setId(Repositorio_Ablazon.getNextIdVoucher());
            Repositorio_Ablazon.adicionarVouchers(voucher);
            print("Voucher [" + titulo + "] registado com sucesso!");
        }
        else
            print("Faça login na API primeiro!");
    }

    public void processarEncomenda(Integer id, String data_prevista){
        if(this.idUser != null) {
            if(!dataValida(data_prevista))
            {
                print("A data introduzida como prevista não é válida!");
                return;
            }
            else if (dataAnt_e_Pos(getActualDate(), data_prevista) <= 0)
            {
                print("A data introduzida como prevista já é antiga!");
                return;
            }

            Encomenda aux = Repositorio_Ablazon.getEncomendaId(id);

            if(aux != null) {
                if (aux.getEstado().getId() == 1) {
                    Estado_Encomenda aux2 = Repositorio_Ablazon.getEstadoEncomendaID(2);
                    aux.setData_prevista(data_prevista);

                    ArrayList<Itens_Encomenda> livrosEntregarDepois = new ArrayList<>();

                    //verificar se tem livros suficientes para entregar
                    for(Integer i = 0; i < aux.getLivros().size(); i++){
                        Itens_Encomenda auxiliar = aux.getLivros().get(i);
                        Itens_Stock emStock = Repositorio_Ablazon.getLivroISBN(auxiliar.getLivro().getISBN());

                        if(emStock.getQuantidade() < auxiliar.getQuantidade()) {  //se nao houver suficientes
                            livrosEntregarDepois.add(new Itens_Encomenda(auxiliar.getLivro(), auxiliar.getQuantidade() - emStock.getQuantidade()));  //adicionar a lista de livros a lear depois!!!
                            auxiliar.setQuantidade(emStock.getQuantidade());  //atualizar quantidade na entrega atual
                            emStock.setQuantidade(0);  //atualizar quantidade em stock
                        }
                        else
                        {
                            emStock.setQuantidade(emStock.getQuantidade() - auxiliar.getQuantidade());  //atualizar quantidade em stock
                        }
                    }

                    //verificar se e possivel realizar a encomenda!!
                    boolean possivel = false;
                    for(Integer i = 0; i < aux.getLivros().size(); i++) {
                        if (aux.getLivros().get(i).getQuantidade() != 0){
                            possivel = true;
                            break;
                        }
                    }

                    if(possivel) {  //se for possivel enviar  encomenda atual...
                        //enviar a encomenda
                        aux.setEstado(aux2);
                        print("Estado da encomenda com Id [" + aux.getId() + "] atualizado com sucesso para [" + aux2.getDesignacao() + "]!");

                        if(livrosEntregarDepois.size() != 0)  //se for necessario dividir a encomenda
                        {
                            Encomenda nova = new Encomenda(livrosEntregarDepois, aux.getCliente());
                            nova.setData_prevista(data_prevista);
                            nova.setVoucher(aux.getVoucher());
                            nova.setData_encomenda(aux.getData_encomenda());
                            nova.setId(Repositorio_Ablazon.getNextIdEncomenda());
                            nova.setEstado(aux.getId());
                            Repositorio_Ablazon.adicionarEncomenda(nova);
                        }
                    }
                    else{
                        //voltar a colocar a lista de livros anterior na encomenda
                        aux.setLivros(livrosEntregarDepois);
                    }
                } else
                    print("Não é possível processar a encomenda com id [" + aux.getId() + "]! Ela já se encontra processada!");
            }
            else
                print("A encomenda que está a tentar processar não existe!");
        }
        else
            print("Faça login na API primeiro!");
    }

    public void enviarEncomenda(Integer id){
        if(this.idUser != null) {
            Encomenda aux = Repositorio_Ablazon.getEncomendaId(id);

            if(aux != null) {
                if (aux.getEstado().getId() == 2) {
                    Estado_Encomenda aux2 = Repositorio_Ablazon.getEstadoEncomendaID(3);
                    aux.setEstado(aux2);

                    print("Estado da encomenda com Id [" + aux.getId() + "] atualizado com sucesso para [" + aux2.getDesignacao() + "]!");
                } else
                    print("Não é possível enviar a encomenda com id [" + aux.getId() + "]! Ela já se encontra enviada ou ainda não foi processada!");
            }
            else
                print("A encomenda que está a tentar enviar não existe!");
        }
        else
            print("Faça login na API primeiro!");
    }

    public void show(){
        Repositorio_Ablazon.getUserId(idUser).show();
    }

    public void showStock(){
        ArrayList<Itens_Stock> aux = Repositorio_Ablazon.getStock();
        for(Integer i = 0; i < aux.size(); i++){
            aux.get(i).show();
        }
    }

}
