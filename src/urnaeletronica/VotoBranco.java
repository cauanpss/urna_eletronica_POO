package urnaeletronica;

public class VotoBranco extends Voto {

    @Override
    public void registrar(Votacao votacao) {
        votacao.registrarVotoBranco();
    }

}