package urnaeletronica;

public class Apuracao {

    public String gerarResultado(Votacao votacao) {

        String resultado = "===== RESULTADO =====\n\n";

        // Exibe os candidatos e seus votos
        for (Candidato c : votacao.getCandidatos()) {

            resultado += c.getNumero() + " - "
                    + c.getNome() + " - "
                    + c.getPartido() + " - "
                    + c.getVotos() + " votos\n\n";
        }

        // Exibe votos brancos e nulos
        resultado += "Votos Brancos: "
                + votacao.getVotosBrancos() + "\n";

        resultado += "Votos Nulos: "
                + votacao.getVotosNulos() + "\n";

        // Calcula total de votos
        int total = votacao.getVotosBrancos()
                + votacao.getVotosNulos();

        for (Candidato c : votacao.getCandidatos()) {
            total += c.getVotos();
        }

        resultado += "\nTotal de votos: " + total;

        return resultado;
    }

}