package urnaeletronica;

import javax.swing.SwingUtilities;

public class UrnaEleitoral {

    public static void main(String[] args) {

        // Cria o objeto responsável pela votação
        Votacao votacao = new Votacao();

        // Cadastra os candidatos
        Candidato c1 = new Candidato(12, "Joao Silva", "ABC");
        Candidato c2 = new Candidato(45, "Maria Souza", "XYZ");
        Candidato c3 = new Candidato(77, "Carlos Santos", "DEF");

        // Adiciona os candidatos à votação
        votacao.adicionarCandidato(c1);
        votacao.adicionarCandidato(c2);
        votacao.adicionarCandidato(c3);

        // Inicia a interface gráfica
        SwingUtilities.invokeLater(() -> {
            new TelaUrna(votacao);
        });
    }

}