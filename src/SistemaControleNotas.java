import java.util.ArrayList;
import java.util.Scanner;

public class SistemaControleNotas {
    private static ArrayList<Aluno> alunos = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nSistema de Controle de Notas");
            System.out.println("1. Adicionar aluno");
            System.out.println("2. Adicionar nota a aluno");
            System.out.println("3. Calcular média do aluno");
            System.out.println("4. Verificar aprovação");
            System.out.println("5. Listar alunos e notas");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            continuar = switch (opcao) {
                case "1" -> {
                    adicionarAluno();
                    yield true;
                }
                case "2" -> {
                    adicionarNota();
                    yield true;
                }
                case "3" -> {
                    calcularMedia();
                    yield true;
                }
                case "4" -> {
                    verificarAprovacao();
                    yield true;
                }
                case "5" -> {
                    listarAlunos();
                    yield true;
                }
                case "6" -> {
                    System.out.println("Saindo do sistema...");
                    yield false;
                }
                default -> {
                    System.out.println("Opção inválida!");
                    yield true;
                }
            };
        }
    }

    private static void adicionarAluno() {
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();
        if (buscarAluno(nome) != null) {
            System.out.println("Erro: Já existe um aluno com esse nome!");
            return;
        }
        Aluno aluno = new Aluno(nome);
        alunos.add(aluno);
        System.out.println("Aluno " + nome + " adicionado com sucesso!");
    }

    private static void adicionarNota() {
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();
        Aluno aluno = buscarAluno(nome);
        if (aluno != null) {
            System.out.print("Digite a nota: ");
            String entradaNota = scanner.nextLine();
            try {
                Double nota = Double.parseDouble(entradaNota);
                aluno.adicionarNota(nota);
                System.out.println("Nota " + nota + " adicionada ao aluno " + nome);
            } catch (NumberFormatException e) {
                System.out.println("Erro: A nota deve ser um número.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    private static void calcularMedia() {
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();
        Aluno aluno = buscarAluno(nome);
        if (aluno != null) {
            double media = aluno.calcularMedia();
            System.out.println("Média do aluno " + nome + ": " + String.format("%.2f", media));
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    private static void verificarAprovacao() {
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();
        Aluno aluno = buscarAluno(nome);
        if (aluno != null) {
            String resultado = aluno.verificarAprovacao();
            System.out.println("Aluno " + nome + " está " + resultado);
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    private static void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado!");
        } else {
            alunos.forEach(Aluno::listarNotas);
        }
    }

    private static Aluno buscarAluno(String nome) {
        return alunos.stream()
                .filter(aluno -> aluno.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }
}