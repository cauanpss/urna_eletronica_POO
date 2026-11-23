package urnaeletronica;

import java.util.ArrayList;

public class Votacao {

    private ArrayList<Candidato> candidatos;
    private int votosBrancos;
    private int votosNulos;

    public Votacao() {
        candidatos = new ArrayList<>();
        votosBrancos = 0;
        votosNulos = 0;
    }

    // Adiciona candidato à votação
    public void adicionarCandidato(Candidato candidato) {
        candidatos.add(candidato);
    }

    // Busca candidato pelo número
    public Candidato buscarCandidato(int numero) {
        for (Candidato c : candidatos) {
            if (c.getNumero() == numero) {
                return c;
            }
        }

        return null;
    }

    // Registra voto em candidato
    public void registrarVotoCandidato(int numero) {
        Candidato candidato = buscarCandidato(numero);

        if (candidato != null) {
            candidato.registrarVoto();
        }
    }

    // Registra voto branco
    public void registrarVotoBranco() {
        votosBrancos++;
    }

    // Registra voto nulo
    public void registrarVotoNulo() {
        votosNulos++;
    }

    // Método que recebe qualquer tipo de Voto
    public void registrarVoto(Voto voto) {
        voto.registrar(this);
    }

    public ArrayList<Candidato> getCandidatos() {
        return candidatos;
    }

    public int getVotosBrancos() {
        return votosBrancos;
    }

    public int getVotosNulos() {
        return votosNulos;
    }

    public void setCandidatos(ArrayList<Candidato> candidatos) {
        this.candidatos = candidatos;
    }

    public void setVotosBrancos(int votosBrancos) {
        this.votosBrancos = votosBrancos;
    }

    public void setVotosNulos(int votosNulos) {
        this.votosNulos = votosNulos;
    }
}