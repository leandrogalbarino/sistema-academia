package com.example.tabelas;

import java.util.List;

import com.example.util.Util;
import com.example.dao.AlunoDadosDao;
import com.example.dto.AlunoDadosDto;

public class AlunoDados {
    AlunoDadosDao banco = new AlunoDadosDao();

    public AlunoDados() {
        menu();
        opcoesAluno(Util.solicitarAlternativas(1, 6, "Número da operação:"));
    }

    private void menu() {
        System.out.println("Qual operação com alunos deseja realizar:");
        System.out.println("1 - Incluir aluno!");
        System.out.println("2 - Excluir aluno!");
        System.out.println("3 - Listar alunos!");
        System.out.println("4 - Buscar aluno por CPF!");
        System.out.println("5 - Buscar aluno por nome!");
        System.out.println("6 - Voltar");

    }

    private void opcoesAluno(int opcao) {
        switch (opcao) {
            case 1:
                incluirAluno();
                break;
            case 2:
                removerAluno();
                break;
            case 3:
                listarAlunos();
                break;
            case 4:
                buscarAlunoCpf();
                break;
            case 5:
                buscarAlunoNome();
                break;
            default:
                break;
        }
    }

    public void incluirAluno() {
        AlunoDadosDto aluno = new AlunoDadosDto();
        boolean bool;

        aluno.setNome(Util.solicitarNome(" do aluno"));
        aluno.setDataNascimento(Util.solicitarData("Data de Nascimento"));
        aluno.setCpf(Util.solicitarCpf(" do aluno"));

        bool = banco.incluir(aluno);
        if (bool) {
            System.out.println("Inserção realizada com sucesso!");
        } else {
            System.out.println("Já existe um aluno com esse CPF!");
        }
    }

    public void removerAluno() {
        String cpf = Util.solicitarCpf(" do aluno a ser removido");

        if (banco.buscarCpf(cpf) != null) {
            if (Util.solicitarSimNao("Deseja realmente remover o aluno:")) {
                boolean bool = banco.remover(cpf);
                if (bool) {
                    System.out.println("Remoção realizada com sucesso!");
                } else {
                    System.out.println("Erro ao remover o aluno!");
                }
            } else {
                System.out.println("Ação cancelada!");
            }
        } else {
            System.out.println("Cpf não encontrado!");
        }
    }

    public void alterarDadosAluno() {
        String cpf = Util.solicitarCpf(" do aluno a ser removido");
        AlunoDadosDto aluno = banco.buscarCpf(cpf);
        if (aluno != null) {
            System.out.println("Deseja alterar o nome ou data_de_nascimento");
            System.out.printf("Nome:%s |", aluno.getNome());
            System.out.printf("%s |", aluno.getCpf());
            System.out.printf("%s\n", Util.formatarDataEmPortugues(aluno.getDataNascimento()));
        } else {
            System.out.printf("Aluno nao cadastrado!\n");
        }

    }

    public void listarAlunos() {
        List<AlunoDadosDto> lista_alunos = banco.listarAlunos();
        if (lista_alunos.size() > 0) {
            System.out.println("Alunos");
            for (AlunoDadosDto aluno : lista_alunos) {
                System.out.printf("Nome:%s |", aluno.getNome());
                System.out.printf("%s |", aluno.getCpf());
                System.out.printf("%s\n", Util.formatarDataEmPortugues(aluno.getDataNascimento()));
            }
        } else {
            System.out.println("Não existe nenhum aluno cadastrado!");
        }

    }

    public void buscarAlunoNome() {
        List<AlunoDadosDto> lista_alunos = banco.listarAlunos();
        if (lista_alunos.size() > 0) {
            String nome = Util.solicitarNome(" do aluno");

            lista_alunos = banco.buscarNome(nome);
            if (lista_alunos.size() > 0) {
                System.out.println("Alunos");
                for (AlunoDadosDto aluno : lista_alunos) {
                    System.out.printf("Nome:%s |", aluno.getNome());
                    System.out.printf("%s |", aluno.getCpf());
                    System.out.printf("%s\n", Util.formatarDataEmPortugues(aluno.getDataNascimento()));
                }
            } else {
                System.out.printf("Não foi encontrado nenhum aluno com nome: %s!\n", nome);
            }
        } else {
            System.out.println("Não existe nenhum aluno cadastrado!");
        }
    }

    public void buscarAlunoCpf() {
        List<AlunoDadosDto> lista_alunos = banco.listarAlunos();
        if (lista_alunos.size() > 0) {
            String cpf = Util.solicitarCpf(" do aluno");

            AlunoDadosDto aluno = banco.buscarCpf(cpf);
            if (aluno != null) {
                System.out.printf("Nome:%s |", aluno.getNome());
                System.out.printf("%s |", aluno.getCpf());
                System.out.printf("%s\n", Util.formatarDataEmPortugues(aluno.getDataNascimento()));
            } else {
                System.out.printf("Não foi encontrado nenhum aluno com cpf: %s!\n", cpf);
            }
        } else {
            System.out.println("Não existe nenhum aluno cadastrado!");
        }
    }

}
