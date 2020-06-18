import APIs.FuncionarioAblazonAPI;
import APIs.FuncionarioBibliotecaAPI;
import APIs.TransportadoraAPI;
import BD.Repositorio_Ablazon;
import Encomenda.*;
import Stock.Itens_Encomenda;
import Stock.Itens_Stock;
import Stock.Livro;
import Utilizadores.Funcionario_Ablazon;
import Utilizadores.Funcionario_Biblioteca;
import Utilizadores.Utilizador;
import interfaces.FuncionarioAblazonInterface;
import interfaces.FuncionarioBibliotecaInterface;
import interfaces.TransportadoraInterface;

import static extras.funcoes_uteis.*;

import java.util.ArrayList;

public class main {
    public static void main(String args[]){
        Utilizador master = new Funcionario_Ablazon("master", "123456", "Master", "rua nao sei", 123456781, 921234561, "master@email.com");
        master.setId(Repositorio_Ablazon.getNextIdUtilizador());
        Repositorio_Ablazon.adicionarUtilizador(master);

        //----------------------------------------------------------------------------------------------------
        //                          login na API do funcionario ablazon e criacao dos utilizadores
        //----------------------------------------------------------------------------------------------------
        print("-------------------------------------------------------------------------------------------\n");
        FuncionarioAblazonInterface funcionario_ablazon = new FuncionarioAblazonAPI();

        funcionario_ablazon.login("master", "123456");
        //registar um novo utilizador
        //nif = 123456786
        funcionario_ablazon.registarNovoUtilizador(new Funcionario_Ablazon("josecarlos", "123456", "José Carlos", "rua nao sei", 123456786, 921234567, "jose.carlos@email.com"));
        //nif = 123456789
        funcionario_ablazon.registarNovoUtilizador(new Funcionario_Ablazon("brunolopes", "123456", "Bruno Lopes", "rua nao sei", 123456789, 921234566, "bruno.lopes@email.com"));
        //nif = 123456787
        funcionario_ablazon.registarNovoUtilizador(new Funcionario_Biblioteca("pedrocosta", "123456", "Pedro Costa", "rua nao sei", 123456787, 921234568, "pedro.costa@email.com"));

        //login com um utilizador negado
        funcionario_ablazon.login("pedrocosta", "123456");
        //nif = 123456788
        funcionario_ablazon.registarNovoUtilizador(new Funcionario_Biblioteca("goncalocosta", "123456", "Goncalo Costa", "rua nao sei", 123456788, 921234569, "goncalo.costa@email.com"));

        //login com um utilizador permitido
        funcionario_ablazon.login("josecarlos", "123456");
        //nif = 123456788
        funcionario_ablazon.registarNovoUtilizador(new Funcionario_Biblioteca("goncalocosta", "123456", "Goncalo Costa", "rua nao sei", 123456788, 921234569, "goncalo.costa@email.com"));


        //----------------------------------------------------------------------------------------------------
        //                          login na API do funcionario biblioteca
        //----------------------------------------------------------------------------------------------------
        print("-------------------------------------------------------------------------------------------\n");
        FuncionarioBibliotecaInterface funcionario_biblioteca = new FuncionarioBibliotecaAPI();

        //login com user nao autorizado
        funcionario_biblioteca.login("brunolopes", "123456");
        //login com user autorizado
        funcionario_biblioteca.login("goncalocosta", "123456");

        //----------------------------------------------------------------------------------------------------
        //                          apresentacao do perfil dos utilizadores
        //----------------------------------------------------------------------------------------------------
        print("-------------------------------------------------------------------------------------------\n");
        funcionario_ablazon.perfilUtilizador();
        funcionario_biblioteca.perfilUtilizador();

        //----------------------------------------------------------------------------------------------------
        //                          registar entrada de livros no armazem
        //----------------------------------------------------------------------------------------------------
        print("-------------------------------------------------------------------------------------------\n");
        funcionario_ablazon.registarNovoLivro(3, "123456", "livro 1", "ninguem", "ninguem tambem", 2020);
        funcionario_ablazon.registarNovoLivro(5, "123457", "livro 2", "ninguem", "ninguem tambem", 2019);
        funcionario_ablazon.registarNovoLivro(3, "123456");
        funcionario_ablazon.registarNovoLivro(3, "123458");

        //----------------------------------------------------------------------------------------------------
        //                          registar estados de encomenda
        //----------------------------------------------------------------------------------------------------
        print("-------------------------------------------------------------------------------------------\n");
        //nao alterar esta ordem!!!!!!
        funcionario_ablazon.registarNovoEstadoEncomenda("A aguardar processamento");
        funcionario_ablazon.registarNovoEstadoEncomenda("Processada");
        funcionario_ablazon.registarNovoEstadoEncomenda("Enviada");
        funcionario_ablazon.registarNovoEstadoEncomenda("Entregue");

        //----------------------------------------------------------------------------------------------------
        //                          registar vouchers
        //----------------------------------------------------------------------------------------------------
        print("-------------------------------------------------------------------------------------------\n");
        //defenido um maximo de 2300 no ano e minimo de 2000
        funcionario_ablazon.registarNovoVoucher((float) 20.0, "desconto 1", "desconto de teste", "20/8/2020");
        funcionario_ablazon.registarNovoVoucher((float) 20.0, "desconto 1", "desconto de teste", "29/2/2019");
        funcionario_ablazon.registarNovoVoucher((float) 20.0, "desconto 1", "desconto de teste", "29/2/2020");

        //forcar a insercao de um voucher antigo para testes
        Voucher voucherAux = new Voucher((float) 20.0, "desconto 1", "desconto de teste", "20/5/2020");
        voucherAux.setId(Repositorio_Ablazon.getNextIdVoucher());
        Repositorio_Ablazon.adicionarVouchers(voucherAux);

        //----------------------------------------------------------------------------------------------------
        //                          registar encomendas
        //----------------------------------------------------------------------------------------------------
        print("-------------------------------------------------------------------------------------------\n");

        ArrayList<Itens_Encomenda> livros = new ArrayList<>();
        livros.add(new Itens_Encomenda(Repositorio_Ablazon.getLivroISBN("123456").getLivro(), 4));
        livros.add(new Itens_Encomenda(Repositorio_Ablazon.getLivroISBN("123457").getLivro(), 3));

        funcionario_biblioteca.registarNovaEncomenda(livros, null);
        funcionario_biblioteca.registarNovaEncomenda(livros, null);
        print("-------------------------------");
        funcionario_biblioteca.login("pedrocosta", "123456");
        funcionario_biblioteca.registarNovaEncomenda(livros, Repositorio_Ablazon.getVoucherID(1));
        print("-------------------------------");
        funcionario_biblioteca.registarNovaEncomenda(livros, Repositorio_Ablazon.getVoucherID(2));

        //----------------------------------------------------------------------------------------------------
        //                          processar encomendas
        //----------------------------------------------------------------------------------------------------
        print("-------------------------------------------------------------------------------------------\n");

        funcionario_ablazon.processarEncomenda(1, "15/6/2020");
        print("-------------------------------");
        funcionario_ablazon.processarEncomenda(1, "18/9/2020");
        print("-------------------------------");
        funcionario_ablazon.processarEncomenda(1, "18/9/2020");
        print("-------------------------------");
        funcionario_ablazon.processarEncomenda(3, "18/7/2020");

        //----------------------------------------------------------------------------------------------------
        //                          enviar encomendas
        //----------------------------------------------------------------------------------------------------
        print("-------------------------------------------------------------------------------------------\n");

        funcionario_ablazon.enviarEncomenda(1);
        print("-------------------------------");
        funcionario_ablazon.enviarEncomenda(1);
        print("-------------------------------");
        funcionario_ablazon.enviarEncomenda(2);
        print("-------------------------------");
        funcionario_ablazon.enviarEncomenda(3);

        //----------------------------------------------------------------------------------------------------
        //                          entregar encomendas
        //----------------------------------------------------------------------------------------------------
        print("-------------------------------------------------------------------------------------------\n");
        TransportadoraInterface transportadora = new TransportadoraAPI();

        transportadora.entregarEncomenda(1);
        print("-------------------------------");
        transportadora.entregarEncomenda(1);
        print("-------------------------------");
        transportadora.entregarEncomenda(2);
        print("-------------------------------");
        transportadora.entregarEncomenda(3);

        //----------------------------------------------------------------------------------------------------
        //                          mostrar dados pessoais e encomendas
        //----------------------------------------------------------------------------------------------------
        print("-------------------------------------------------------------------------------------------\n");
        funcionario_biblioteca.show();
        print("-------------------------------");
        funcionario_biblioteca.showEncomendas();
        print("-------------------------------");
        funcionario_biblioteca.login("goncalocosta", "123456");
        funcionario_biblioteca.show();
        print("-------------------------------");
        funcionario_biblioteca.showEncomendas();

        //----------------------------------------------------------------------------------------------------
        //                          feedback de encomendas
        //----------------------------------------------------------------------------------------------------
        print("-------------------------------------------------------------------------------------------\n");

        funcionario_biblioteca.fornecerFeadbackEncomenda(Repositorio_Ablazon.getEncomendaId(1), "rápida entrega", 6);
        funcionario_biblioteca.fornecerFeadbackEncomenda(Repositorio_Ablazon.getEncomendaId(1), "rápida entrega", 5);
        funcionario_biblioteca.fornecerFeadbackEncomenda(Repositorio_Ablazon.getEncomendaId(2), "rápida entrega", 6);
        funcionario_biblioteca.fornecerFeadbackEncomenda(Repositorio_Ablazon.getEncomendaId(3), "rápida entrega", 5);

        //----------------------------------------------------------------------------------------------------
        //                          ver stock
        //----------------------------------------------------------------------------------------------------
        print("-------------------------------------------------------------------------------------------\n");

        funcionario_ablazon.showStock();
    }

}
