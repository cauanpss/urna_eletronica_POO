package urnaeletronica;

public abstract class Voto implements Registravel {

    // Método que será implementado pelas subclasses
    @Override
    public abstract void registrar(Votacao votacao);

}