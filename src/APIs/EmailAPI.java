package APIs;
import interfaces.EmailInterface;
import static extras.funcoes_uteis.*;

public class EmailAPI implements EmailInterface{
    private String Origem;
    private String Destino;
    private String Mensagem;

    public void enviarEmail(){
        print("Novo email!!");
        print("Origem: " + Origem);
        print("Destino: " + Destino);
        print("Mensagem:\n" + Mensagem + "\n");
    }

    public void setOrigem(String Origem){
        this.Origem = Origem;
    }

    public void setDestino(String Destino){
        this.Destino = Destino;
    }

    public void setMensagem(String Mensagem){
        this.Mensagem = Mensagem;
    }
}
