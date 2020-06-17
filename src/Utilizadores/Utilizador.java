package Utilizadores;
import static extras.funcoes_uteis.*;

public class Utilizador {
    private String nome_utilizador;
    private String palavra_passe;
    private String nome;
    private String morada;
    private Integer nif;
    private Integer telefone;
    private String email;

    public Utilizador(String nome_utilizador, String palavra_passe, String nome, String morada, Integer nif, Integer telefone, String email){
        this.nome_utilizador = nome_utilizador;
        this.palavra_passe = palavra_passe;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNomeUtilizador(){
        return nome_utilizador;
    }

    public String getPalavraPasse(){
        return palavra_passe;
    }

    public String getNome(){
        return nome;
    }

    public String getMorada(){
        return morada;
    }

    public Integer getNif(){
        return nif;
    }

    public Integer getTelefone(){
        return telefone;
    }

    public String getEmail(){
        return email;
    }

    public void setPalavraPasse(String palavra_passe){
        this.palavra_passe = palavra_passe;
    }

    public void setMorada(String morada){
        this.morada = morada;
    }

    public void setTelefone(Integer telefone){
        this.telefone = telefone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void show(){
        print(
                "Nome: " + nome + "\n" +
                "NIF: " + nif + "\n" +
                "Telefone: " + telefone + "\n" +
                "Morada: " + morada + "\n" +
                "Email: " + email + "\n" +
                "Username: " + nome_utilizador + "\n" +
                "Palavra-passe: " + palavra_passe + "\n"
                );


        this.morada = morada;
        this.email = email;
    }

}
