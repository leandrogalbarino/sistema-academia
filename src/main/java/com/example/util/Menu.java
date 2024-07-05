package com.example.util;

import java.sql.Date;
import java.time.LocalDate;

import com.example.dao.AlunoListaPresencaDao;
import com.example.tabelas.AlunoDados;
import com.example.tabelas.AlunoPlano;
import com.example.tabelas.AlunoTreino;
import com.example.tabelas.Exercicios;
import com.example.tabelas.Planos;

public class Menu {

    public void exibirMenuPrincipal() {
        System.out.println("------ Menu Principal ------");
        System.out.println("1. Aluno Dados");
        System.out.println("2. Aluno Plano");
        System.out.println("3. Aluno Treino");
        System.out.println("4. Instrutor Aluno Treino");
        System.out.println("5. Exercicios");
        System.out.println("6. Planos");
        System.out.println("7. Relatórios");
        System.out.println("8. Sair");

    }

    public void menuAlunosDados() {
        boolean sair = false;
        AlunoDados alunoDados = new AlunoDados();
        while (!sair) {
            System.out.println("------ Menu Alunos ------");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Excluir Aluno");
            System.out.println("3. Listar Alunos");
            System.out.println("4. Buscar Aluno por CPF");
            System.out.println("5. Buscar Aluno por Nome");
            System.out.println("6. Voltar ao Menu Principal");
            int opcao = Util.solicitarAlternativas(1, 6, "DIGITE O NUMERO QUE DESEJA REALIZAR:");

            switch (opcao) {
                case 1:
                    alunoDados.incluirAluno();
                    break;
                case 2:
                    alunoDados.removerAluno();
                    break;
                case 3:
                    alunoDados.listarAlunos();
                    break;
                case 4:
                    alunoDados.buscarAlunoCpf();
                    break;
                case 5:
                    alunoDados.buscarAlunoNome();
                    break;
                case 6:
                    sair = true;
                    break;
                default:
                    break;
            }
        }
    }

    public void menuPlanos() {
        boolean sair = false;
        while (!sair) {
            System.out.println("------ Menu Planos ------");
            System.out.println("1. Criar Plano");
            System.out.println("2. Listar Planos");
            System.out.println("3. Voltar ao Menu Principal");
            int opcao = Util.solicitarAlternativas(1, 3, "DIGITE O NUMERO QUE DESEJA REALIZAR:");

            switch (opcao) {
                case 1:
                    Planos.cadastrarPlano();
                    break;
                case 2:
                    Planos.exibirPlanos();
                    break;
                case 3:
                    sair = true;
                    break;
                default:
                    break;
            }
        }
    }

    public void menuExercicios() {
        boolean sair = false;
        while (!sair) {
            System.out.println("------ Menu Exercícios ------");
            System.out.println("1. Cadastrar Exercício");
            System.out.println("2. Remover Exercício");
            System.out.println("3. Listar Exercícios");
            System.out.println("4. Voltar ao Menu Principal");
            int opcao = Util.solicitarAlternativas(1, 4, "DIGITE O NUMERO QUE DESEJA REALIZAR:");

            switch (opcao) {
                case 1:
                    Exercicios.cadastrarExercicio();
                    break;
                case 2:
                    Exercicios.removerExercicio();
                    break;
                case 3:
                    Exercicios.exibirExercicios();
                    break;
                case 4:
                    sair = true;
                    break;
                default:
                    break;
            }
        }
    }

    public void menuAlunoPlano() {
        boolean sair = false;
        AlunoPlano alunoPlano = new AlunoPlano();

        while (!sair) {

            System.out.println("------ Menu Aluno Plano ------");
            System.out.println("1. Cadastrar Plano");
            System.out.println("2. Cancelar Plano");
            System.out.println("3. Voltar ao Menu Principal");

            int opcao = Util.solicitarAlternativas(1, 3, "DIGITE O NUMERO QUE DESEJA REALIZAR:");

            switch (opcao) {
                case 1:
                    alunoPlano.cadastrarPlano();
                    break;
                case 2:
                    alunoPlano.cancelarPlano();
                    break;
                case 3:
                    sair = true;
                    break;
                default:
                    break;
            }
        }
    }

    public void menuInstrutorTreino() {
        boolean sair = false;
        AlunoTreino aluno_treino = new AlunoTreino();

        while (!sair) {

            System.out.println("------ Menu Instrutor Treino Aluno ------");
            System.out.println("1. Cadastrar Treino");
            System.out.println("2. Alterar treino");
            System.out.println("3. Excluir treino");
            System.out.println("4. Mostrar lista de treinos");
            System.out.println("5. Mostrar treino");
            System.out.println("6. Alterar carga de um exercicio!");
            System.out.println("7. Voltar ao Menu Principal");

            int opcao = Util.solicitarAlternativas(1, 7, "DIGITE O NUMERO QUE DESEJA REALIZAR:");

            switch (opcao) {
                case 1:
                    aluno_treino.cadastrarTreino();
                    break;
                case 2:
                    aluno_treino.alterarTreino();
                    break;
                case 3:
                    aluno_treino.excluirTreino();
                    break;
                case 4:
                    aluno_treino.mostrarListaTreinos();
                    break;
                case 5:
                    aluno_treino.mostrarTreino();
                    break;
                case 6:
                    aluno_treino.alterarCarga();
                    break;
                case 7:
                    sair = true;
                    break;
                default:
                    break;
            }
        }
    }

    public void menuAlunoTreino() {
        boolean sair = false;
        AlunoTreino aluno_treino = new AlunoTreino();

        while (!sair) {
            System.out.println("------ Menu Aluno Treino ------");
            System.out.println("1. Mostrar lista de treinos");
            System.out.println("2. Mostrar treino");
            System.out.println("3. Alterar carga de um exercicio");
            System.out.println("4. Realizar treino");
            System.out.println("5. Voltar ao Menu Principal");

            int opcao = Util.solicitarAlternativas(1, 5, "DIGITE O NUMERO QUE DESEJA REALIZAR:");
            switch (opcao) {
                case 1:
                    aluno_treino.mostrarListaTreinos();
                    break;
                case 2:
                    aluno_treino.mostrarTreino();
                    break;
                case 3:
                    aluno_treino.alterarCarga();
                    break;
                case 4:
                    aluno_treino.realizarTreino();
                    break;
                case 5:
                    sair = true;
                    break;
                default:
                    break;
            }
        }
    }

    public void menuRelatorios() {
        boolean sair = false;
        while (!sair) {
            System.out.println("------ Menu Relatórios ------");
            System.out.println("1. Relatório de presença de alunos");
            System.out.println("2. Relatório de Evolução de Carga de Exercício");
            System.out.println("3. Voltar ao Menu Principal");
            int opcao = Util.solicitarAlternativas(1, 3, "DIGITE O NUMERO QUE DESEJA REALIZAR:");

            switch (opcao) {
                case 1:
                    String cpfPresenca = Util.solicitarCpf(" do aluno");
                    LocalDate dataInicioPresenca = Util.solicitarData("Data de início");
                    LocalDate dataFimPresenca = Util.solicitarData("Data de fim");
                    AlunoListaPresencaDao alunoPresencaDao = new AlunoListaPresencaDao();
                    alunoPresencaDao.relatorioPresencaAlunoIntervalo(cpfPresenca, Date.valueOf(dataInicioPresenca), Date.valueOf(dataFimPresenca));
                    break;
                case 2:
                    AlunoTreino aluno_treino = new AlunoTreino();
                    aluno_treino.relatorioCarga();
                    break;
                case 3:
                    sair = true;
                    break;
                default:
                    break;
            }
        }
    }
}
