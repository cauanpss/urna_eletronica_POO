package urnaeletronica;

public class Candidato {

    private int numero;
    private String nome;
    private String partido;
    private int votos;

    public Candidato(int numero, String nome, String partido) {
        this.numero = numero;
        this.nome = nome;
        this.partido = partido;
        this.votos = 0;
    }

    // Incrementa a quantidade de votos
    public void registrarVoto() {
        votos++;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public String getPartido() {
        return partido;
    }

    public int getVotos() {
        return votos;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }
}