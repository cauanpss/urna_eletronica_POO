package urnaeletronica;

import javax.swing.*;
import java.awt.*;

public class TelaUrna extends JFrame {

    private Votacao votacao;
    private Apuracao apuracao;

    private String numeroDigitado;

    private JLabel lblNumero;
    private JLabel lblNome;
    private JLabel lblPartido;

    public TelaUrna(Votacao votacao) {

        this.votacao = votacao;
        this.apuracao = new Apuracao();
        this.numeroDigitado = "";

        setTitle("Urna Eleitoral");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel de exibição
        JPanel painelDisplay = new JPanel();
        painelDisplay.setLayout(new GridLayout(4, 1, 5, 5));
        painelDisplay.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblTitulo = new JLabel(
                "URNA ELETRÔNICA", SwingConstants.CENTER);

        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        lblNumero = new JLabel("Número: ", SwingConstants.CENTER);
        lblNumero.setFont(new Font("Arial", Font.BOLD, 22));

        lblNome = new JLabel("Candidato: ", SwingConstants.CENTER);
        lblPartido = new JLabel("Partido: ", SwingConstants.CENTER);

        painelDisplay.add(lblTitulo);
        painelDisplay.add(lblNumero);
        painelDisplay.add(lblNome);
        painelDisplay.add(lblPartido);

        add(painelDisplay, BorderLayout.NORTH);

        // Painel do teclado numérico
        JPanel painelTeclado = new JPanel();
        painelTeclado.setLayout(new GridLayout(4, 3, 10, 10));
        painelTeclado.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Cria os botões de 1 até 9
        for (int i = 1; i <= 9; i++) {

            JButton botao = new JButton(String.valueOf(i));
            botao.setFont(new Font("Arial", Font.BOLD, 20));

            botao.addActionListener(e ->
                    digitarNumero(botao.getText()));

            painelTeclado.add(botao);
        }

        // Espaço vazio
        painelTeclado.add(new JLabel(""));

        // Botão 0
        JButton btn0 = new JButton("0");
        btn0.setFont(new Font("Arial", Font.BOLD, 20));

        btn0.addActionListener(e -> digitarNumero("0"));

        painelTeclado.add(btn0);

        // Espaço vazio
        painelTeclado.add(new JLabel(""));

        add(painelTeclado, BorderLayout.CENTER);

        // Painel dos botões de ação
        JPanel painelAcoes = new JPanel();
        painelAcoes.setLayout(new GridLayout(1, 4, 5, 5));

        JButton btnCorrige = new JButton("CORRIGE");
        btnCorrige.setBackground(Color.ORANGE);

        btnCorrige.addActionListener(e -> corrigir());

        JButton btnBranco = new JButton("BRANCO");
        btnBranco.setBackground(Color.WHITE);

        btnBranco.addActionListener(e -> votarBranco());

        JButton btnConfirma = new JButton("CONFIRMA");
        btnConfirma.setBackground(Color.GREEN);

        btnConfirma.addActionListener(e -> confirmarVoto());

        JButton btnApuracao = new JButton("APURAÇÃO");

        btnApuracao.addActionListener(e -> mostrarApuracao());

        painelAcoes.add(btnBranco);
        painelAcoes.add(btnCorrige);
        painelAcoes.add(btnConfirma);
        painelAcoes.add(btnApuracao);

        add(painelAcoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Digita o número do candidato
    private void digitarNumero(String numero) {

        if (numeroDigitado.length() < 2) {

            numeroDigitado += numero;

            lblNumero.setText("Número: " + numeroDigitado);

            verificarCandidato();
        }
    }

    // Verifica se o candidato existe
    private void verificarCandidato() {

        if (numeroDigitado.length() == 2) {

            int numero = Integer.parseInt(numeroDigitado);

            Candidato candidato = votacao.buscarCandidato(numero);

            if (candidato != null) {

                lblNome.setText(
                        "Candidato: " + candidato.getNome());

                lblPartido.setText(
                        "Partido: " + candidato.getPartido());

            } else {

                lblNome.setText("Candidato inexistente");
                lblPartido.setText("");
            }

        } else {

            lblNome.setText("Candidato: ");
            lblPartido.setText("Partido: ");
        }
    }

    // Limpa os campos para digitar novamente
    private void corrigir() {

        numeroDigitado = "";

        lblNumero.setText("Número: ");
        lblNome.setText("Candidato: ");
        lblPartido.setText("Partido: ");
    }

    // Confirma o voto
    private void confirmarVoto() {

        if (numeroDigitado.length() != 2) {

            JOptionPane.showMessageDialog(
                    this, "Digite o número completo do candidato!");

            return;
        }

        int numero = Integer.parseInt(numeroDigitado);

        Candidato candidato = votacao.buscarCandidato(numero);

        if (candidato != null) {

            int resposta = JOptionPane.showConfirmDialog(
                    this,
                    "Confirmar voto em " + candidato.getNome() + "?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION);

            if (resposta == JOptionPane.YES_OPTION) {

                // Cria um objeto de voto para o candidato
                Voto voto = new VotoCandidato(numero);

                // Registra o voto através do polimorfismo
                votacao.registrarVoto(voto);

                JOptionPane.showMessageDialog(
                        this, "VOTO CONFIRMADO!");

                corrigir();
            }

        } else {

            int resposta = JOptionPane.showConfirmDialog(
                    this,
                    "Número inexistente.\nDeseja confirmar o voto NULO?",
                    "Voto Nulo",
                    JOptionPane.YES_NO_OPTION);

            if (resposta == JOptionPane.YES_OPTION) {

                // Cria um objeto de voto nulo
                Voto voto = new VotoNulo();

                // Registra o voto através do polimorfismo
                votacao.registrarVoto(voto);

                JOptionPane.showMessageDialog(
                        this, "VOTO NULO CONFIRMADO!");

                corrigir();
            }
        }
    }

    // Registra voto em branco
    private void votarBranco() {

        int resposta = JOptionPane.showConfirmDialog(
                this,
                "Confirmar voto em BRANCO?",
                "Voto Branco",
                JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {

            // Cria um objeto de voto branco
            Voto voto = new VotoBranco();

            // Registra o voto através do polimorfismo
            votacao.registrarVoto(voto);

            JOptionPane.showMessageDialog(
                    this, "VOTO EM BRANCO CONFIRMADO!");

            corrigir();
        }
    }

    // Exibe a apuração
    private void mostrarApuracao() {

        String resultado = apuracao.gerarResultado(votacao);

        JOptionPane.showMessageDialog(
                this,
                resultado,
                "Apuração",
                JOptionPane.INFORMATION_MESSAGE);
    }

}