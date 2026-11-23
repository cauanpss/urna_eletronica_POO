package urnaeletronica;

public class VotoNulo extends Voto {

    @Override
    public void registrar(Votacao votacao) {
        votacao.registrarVotoNulo();
    }

}