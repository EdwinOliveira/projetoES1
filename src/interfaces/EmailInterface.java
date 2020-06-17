package interfaces;

public interface EmailInterface {
    public void enviarEmail();
    public void setOrigem(String Origem);
    public void setDestino(String Destino);
    public void setMensagem(String Mensagem);
}
