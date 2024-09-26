import java.util.ArrayList;

class Aluno {
    private String nome;
    private ArrayList<Double> notas;

    public Aluno(String nome) {
        this.nome = nome;
        this.notas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarNota(double nota) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
        }
        notas.add(nota);
    }

    public double calcularMedia() {
        if (notas.isEmpty()) {
            return 0;
        }
        return notas.stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }

    public String verificarAprovacao() {
        double media = calcularMedia();
        return (media >= 7) ? "Aprovado" : "Reprovado";
    }

    public void listarNotas() {
        System.out.println("Notas de " + nome + ": " + notas);
    }
}