package Encomenda;

public class Estado_Encomenda {
    Integer id;
    String designacao;

    public Estado_Encomenda(Integer id, String designacao){
        this.id = id;
        this.designacao = designacao;
    }

    public Integer getID(){
        return id;
    }

    public String getDesignacao(){
        return designacao;
    }

}
