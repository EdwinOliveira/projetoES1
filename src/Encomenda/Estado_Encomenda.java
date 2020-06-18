package Encomenda;

public class Estado_Encomenda {
    Integer id;
    String designacao;

    public Estado_Encomenda(Integer id, String designacao){
        this.id = id;
        this.designacao = designacao;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getDesignacao(){
        return designacao;
    }

}
