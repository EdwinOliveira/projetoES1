package Stock;

public class Livro {
    private String titulo;
    private String autor;
    private String editora;
    private String ISBN;
    private Integer ano_edicao;

    public Livro(String titulo, String autor, String editora, String ISBN, Integer ano_edicao){
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.ISBN = ISBN;
        this.ano_edicao = ano_edicao;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getAutor(){
        return autor;
    }

    public String getEditora(){
        return editora;
    }

    public String getISBN(){
        return ISBN;
    }

    public Integer getAnoEdicao(){
        return ano_edicao;
    }

}
