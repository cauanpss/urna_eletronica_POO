package urnaeletronica;

public class VotoCandidato extends Voto {

    private int numeroCandidato;

    public VotoCandidato(int numeroCandidato) {
        this.numeroCandidato = numeroCandidato;
    }

    @Override
    public void registrar(Votacao votacao) {
        votacao.registrarVotoCandidato(numeroCandidato);
    }

}