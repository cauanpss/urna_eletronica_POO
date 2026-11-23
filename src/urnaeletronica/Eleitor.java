package urnaeletronica;

public class Eleitor {

    // Atributos
    private String nome;
    private boolean votou;

    // Construtor
    public Eleitor(String nome) {
        this.nome = nome;
        this.votou = false;
    }

    // Retornar nome
    public String getNome() {
        return nome;
    }

    // Verificar se já votou
    public boolean jaVotou() {
        return votou;
    }

    // Registrar que o eleitor votou
    public void registrarVoto() {
        votou = true;
    }
}