package Utilizadores;

public class Funcionario_Ablazon extends Utilizador {

    public Funcionario_Ablazon(String nome_utilizador, String palavra_passe, String nome, String morada, Integer nif, Integer telefone, String email) {
        super(nome_utilizador, palavra_passe, nome, morada, nif, telefone, email);
    }

    @Override
    public String tipoUser(){
        return "FuncionarioAblazon";
    }
}
