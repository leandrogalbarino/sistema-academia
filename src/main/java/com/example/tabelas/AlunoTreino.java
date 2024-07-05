package com.example.tabelas;

import com.example.util.Util;
import com.example.dao.ExerciciosUtils;
import com.example.dto.AlunoCargaDto;
import com.example.dto.AlunoDadosDto;
import com.example.dto.AlunoListaPresencaDto;
import com.example.dto.AlunoTreinoDto;
import com.example.dto.AlunoTreinoExercicioDto;
import com.example.dto.ExerciciosDto;
import com.example.dto.AlunoPlanoDto;

import java.time.LocalDate;
import java.util.List;

import com.example.dao.AlunoDadosDao;
import com.example.dao.AlunoCargaDao;
import com.example.dao.AlunoListaPresencaDao;
import com.example.dao.AlunoTreinoDao;
import com.example.dao.AlunoPlanoDao;

public class AlunoTreino {

    AlunoDadosDao banco_dados = new AlunoDadosDao();
    ExerciciosUtils banco_exercicios = new ExerciciosUtils();
    AlunoTreinoDao banco_treino = new AlunoTreinoDao();
    AlunoPlanoDao banco_plano = new AlunoPlanoDao();
    AlunoCargaDao banco_carga = new AlunoCargaDao();

    private boolean montar_treino(AlunoTreinoDto aluno_treino) {
        int num;
        Exercicios.exibirExercicios();
        // VERIFICAR SE EXISTEM EXERCICIOS PARA MONTAR O TREINO
        System.out.println("---MONTANDO TREINO---");
        System.out.println("Digite -1 para encerrar!");
        do {
            num = Util.solicitarNum("Digite o numero do exercicio que deseja adicionar ao treino:");
            if (banco_exercicios.procurarExercicio(num) != null) {
                if (banco_treino.buscarExercicioId(aluno_treino.getId_treino(), num) == null) {
                    AlunoTreinoExercicioDto aluno = new AlunoTreinoExercicioDto();
                    aluno.setId_treino(aluno_treino.getId_treino());
                    aluno.setId_exercicio(num);
                    aluno.setSeries(Util.solicitarNum("Digite o numero de series:"));
                    aluno.setMin_rep(Util.solicitarNum("Digite o numero minimo de repeticoes:"));
                    aluno.setMax_rep(Util.solicitarNum("Digite o numero maximo de repeticoes:"));
                    aluno.setCarga(Util.solicitarNum("Digite a carga em quilos:"));
                    aluno.setTempo_descanso(Util.solicitarNum("Digite o tempo de descanso em minutos:"));
                    if (banco_treino.adicionarExercicio(aluno)) {
                        registrarCarga(aluno);
                        System.out.printf("Exercicio adicionado com sucesso!\n");
                    } else {
                        System.out.printf("Erro ao adicionar exercicio!\n");
                    }
                } else {
                    System.out.printf("O exercicio ja pertence ao treino!\n");
                }
            } else if (num != -1) {
                System.out.printf("Esse exercicio nao existe, forneca o numero do exercicio corretamente!\n");
            }
        } while (num != -1);

        return (banco_treino.buscarTreino(aluno_treino.getId_treino()).size() > 0);
    }

    public void cadastrarTreino() {
        String cpf = Util.solicitarCpf(" do aluno");
        AlunoDadosDto aluno = banco_dados.buscarCpf(cpf);
        if (aluno != null) {
            AlunoPlanoDto aluno_plano = banco_plano.buscarAlunoPlano(cpf);
            if (aluno_plano != null) {
                AlunoTreinoDto aluno_treino = new AlunoTreinoDto();
                aluno_treino.setCpf(cpf);
                aluno_treino.setNome_treino(Util.solicitarNome(" do treino"));
                int id_treino = banco_treino.incluir(aluno_treino);
                aluno_treino.setId_treino(id_treino);

                if (id_treino != -1) {
                    if (montar_treino(aluno_treino)) {
                        System.out.printf("Treino montado com sucesso!\n");
                    } else {
                        System.out.printf("O treino nao possui exercicios!\n");
                        if (banco_treino.remover(id_treino)) {
                            System.out.printf("Treino excluido!\n");
                        } else {
                            System.out.printf("Erro ao excluir o treino\n");
                        }
                    }

                } else {
                    System.out.printf("Erro ao montar o treino!\n");
                }
            } else {
                System.out.printf("O aluno nao possui nenhum plano cadastrado!\n");
            }

        } else {
            System.out.printf("Aluno nao cadastrado!\n");
        }
    }

    public void mostrarTreino() {
        String cpf = Util.solicitarCpf(" do aluno");
        int id_treino = Util.solicitarNum("Digite o id do treino:");
        AlunoTreinoDto aluno_treino = banco_treino.buscarId(id_treino);
        if (aluno_treino != null && cpf.equals(aluno_treino.getCpf())) {
            List<AlunoTreinoExercicioDto> lista_exercicios = banco_treino.buscarTreino(id_treino);
            System.out.printf("%s |", aluno_treino.getCpf());
            System.out.printf("%s |", aluno_treino.getNome_treino());
            System.out.printf("%d \n", aluno_treino.getId_treino());
            System.out.printf("---LISTA DE EXERCICIOS---\n");
            for (AlunoTreinoExercicioDto aluno_exercicio : lista_exercicios) {

                ExerciciosDto exercicio = banco_exercicios.procurarExercicio(aluno_exercicio.getId_exercicio());
                System.out.printf("Id exercicio: %d\n", aluno_exercicio.getId_exercicio());
                System.out.printf("Exercicio: %s\n", exercicio.getNome());
                System.out.printf("Musculos ativos: %s\n", exercicio.getMusculosAtivos());
                System.out.printf("Series: %d\n", aluno_exercicio.getSeries());
                System.out.printf("Maximo de repeticoes: %d\n", aluno_exercicio.getMax_rep());
                System.out.printf("Minimo de repeticoes: %d\n", aluno_exercicio.getMin_rep());
                System.out.printf("Carga: %d kg\n", aluno_exercicio.getCarga());
                System.out.printf("Tempo de descanso: %d minuto(s)\n\n", aluno_exercicio.getTempo_descanso());
            }
        } else {
            System.out.printf("O aluno nao possui esse treino ou nao esta cadastrado!\n");
        }
    }

    public void mostrarListaTreinos() {
        String cpf = Util.solicitarCpf(" do aluno");
        AlunoDadosDto aluno = banco_dados.buscarCpf(cpf);
        if (aluno != null) {
            List<AlunoTreinoDto> lista_alunos = banco_treino.buscarTreinosCpf(cpf);
            if (lista_alunos.size() > 0) {
                System.out.printf("---TREINOS---\n");
                for (AlunoTreinoDto aluno_treino : lista_alunos) {
                    System.out.printf("%s |", aluno_treino.getCpf());
                    System.out.printf("%s |", aluno_treino.getNome_treino());
                    System.out.printf("%d \n", aluno_treino.getId_treino());
                }
            } else {
                System.out.printf("Aluno nao tem nenhum treino cadastrado!\n");
            }
        } else {
            System.out.printf("Aluno nao cadastrado!\n");
        }
    }

    private AlunoTreinoExercicioDto alterar(AlunoTreinoExercicioDto aluno_treino_exercicio) {

        AlunoTreinoExercicioDto aluno_treino_exercicio_clonado = aluno_treino_exercicio.clone();

        System.out.println("1 - EXERCICIO!");
        System.out.println("2 - SERIES!");
        System.out.println("3 - MINIMO DE REPETICOES!");
        System.out.println("4 - MAXIMO DE REPETICOES!");
        System.out.println("5 - CARGA!");
        System.out.println("6 - TEMPO DE DESCANSO!");
        System.out.println("7 - CANCELAR!");

        int alternativas = Util.solicitarAlternativas(1, 7, "Digite o numero da opcao que deseja alterar:");
        switch (alternativas) {
            case 1:
                int num;
                num = Util.solicitarNum("Digite o numero do exercicio:");
                if (banco_exercicios.procurarExercicio(num) != null) {
                    if (banco_treino.buscarExercicioId(aluno_treino_exercicio_clonado.getId_treino(), num) == null) {
                        aluno_treino_exercicio_clonado.setId_exercicio(num);
                    } else {
                        System.out.printf("Exercicio ja pertence ao treino!\n");
                    }
                } else {
                    System.out.printf("Exercicio nao existe!\n");
                }
                break;
            case 2:
                aluno_treino_exercicio_clonado.setSeries(Util.solicitarNum("Digite o numero de series:"));
                break;
            case 3:
                aluno_treino_exercicio_clonado.setMin_rep(Util.solicitarNum("Digite o numero minimo de repeticoes:"));
                break;
            case 4:
                aluno_treino_exercicio_clonado.setMax_rep(Util.solicitarNum("Digite o numero maximo de repeticoes:"));
                break;
            case 5:
                aluno_treino_exercicio_clonado.setCarga(Util.solicitarNum("Digite a carga em quilos:"));
                break;
            case 6:
                aluno_treino_exercicio_clonado
                        .setTempo_descanso(Util.solicitarNum("Digite o tempo de descanso em minutos:"));
                break;
            default:
                return null;
        }
        return aluno_treino_exercicio_clonado;
    }

    public void alterarTreino() {
        String cpf = Util.solicitarCpf(" do aluno");
        AlunoDadosDto aluno = banco_dados.buscarCpf(cpf);
        if (aluno != null) {

            int id_treino = Util.solicitarNum("Digite o ID do treino que deseja alterar:");
            int id_exercicio = Util.solicitarNum("Digite o ID do exercicio que deseja alterar:");

            AlunoTreinoDto aluno_treino = banco_treino.buscarId(id_treino);
            AlunoTreinoExercicioDto aluno_treino_exercicio = banco_treino.buscarExercicioId(id_treino, id_exercicio);

            if (aluno_treino_exercicio != null && cpf.equals(aluno_treino.getCpf())) {

                System.out.println(aluno_treino_exercicio.getId_exercicio());
                AlunoTreinoExercicioDto aluno_treino_atualizado = alterar(aluno_treino_exercicio);

                if (aluno_treino_atualizado != null && aluno_treino_atualizado != null
                        && !aluno_treino_exercicio.equals(aluno_treino_atualizado)) {

                    if (banco_treino.alterar(aluno_treino_atualizado, aluno_treino_exercicio.getId_exercicio())) {
                        System.out.printf("Treino alterado com sucesso!\n");
                    } else {
                        System.out.printf("Erro ao alterar o treino\n");
                    }
                } else {
                    System.out.printf("Alteracao cancelada!\n");
                }
            } else {
                System.out.printf("Exercicio nao encontrado!\n");
            }
        } else {
            System.out.printf("Aluno nao cadastrado!\n");
        }
    }

    public boolean registrarCarga(AlunoTreinoExercicioDto exercicio) {
        AlunoCargaDto carga = new AlunoCargaDto();
        carga.setCarga(exercicio.getCarga());
        carga.setId_exercicio(exercicio.getId_exercicio());
        carga.setId_treino(exercicio.getId_treino());

        return banco_carga.incluir(carga);
    }

    public void relatorioCarga() {
        String cpf = Util.solicitarCpf(" do aluno");
        AlunoDadosDto aluno = banco_dados.buscarCpf(cpf);
        if (aluno != null) {
            int id_treino = Util.solicitarNum("Digite o ID do treino que deseja alterar:");
            int id_exercicio = Util.solicitarNum("Digite o ID do exercicio que deseja alterar:");

            AlunoTreinoDto aluno_treino = banco_treino.buscarId(id_treino);
            AlunoTreinoExercicioDto aluno_treino_exercicio = banco_treino.buscarExercicioId(id_treino, id_exercicio);

            if (aluno_treino_exercicio != null) {
                if (cpf.equals(aluno_treino.getCpf())) {
                    List<AlunoCargaDto> evolucaoCarga = banco_carga
                            .buscarEvolucaoCargaExercicio(aluno_treino_exercicio);
                    if (evolucaoCarga.size() > 0) {
                        for (AlunoCargaDto alunoCargaDto : evolucaoCarga) {
                            System.out.printf("ID treino:%d -", alunoCargaDto.getId_treino());
                            System.out.printf("ID exercicio:%d -", alunoCargaDto.getId_exercicio());
                            System.out.printf("Carga:%d\n", alunoCargaDto.getCarga());
                        }
                    } else {
                        System.out.printf("Treino nao possui exercicios");
                    }
                } else {
                    System.out.printf("O treino nao pertece ao aluno %s!\n", aluno.getNome());
                }
            } else {
                System.out.printf("Treino nao encontrado!\n");
            }
        } else {
            System.out.printf("Aluno nao cadastrado!\n");
        }
    }

    public void alterarCarga() {
        String cpf = Util.solicitarCpf(" do aluno");
        AlunoDadosDto aluno = banco_dados.buscarCpf(cpf);
        if (aluno != null) {

            int id_treino = Util.solicitarNum("Digite o ID do treino que deseja alterar:");
            int id_exercicio = Util.solicitarNum("Digite o ID do exercicio que deseja alterar:");

            AlunoTreinoDto aluno_treino = banco_treino.buscarId(id_treino);
            AlunoTreinoExercicioDto aluno_treino_exercicio = banco_treino.buscarExercicioId(id_treino, id_exercicio);

            if (aluno_treino_exercicio != null) {
                if (cpf.equals(aluno_treino.getCpf())) {
                    int antiga_carga = aluno_treino_exercicio.getCarga();
                    aluno_treino_exercicio.setCarga(Util.solicitarNum("Digite o peso da nova carga em kg:"));

                    if (antiga_carga != aluno_treino_exercicio.getCarga()) {
                        if (banco_treino.alterar(aluno_treino_exercicio, aluno_treino_exercicio.getId_exercicio())) {
                            System.out.printf("Carga atualizada com sucesso!\n");
                            if (registrarCarga(aluno_treino_exercicio)) {
                                System.out.printf("Carga registrada com sucesso!\n");
                            } else {
                                System.out.printf("Erro ao registrar a nova carga no banco de cargas!\n");
                            }
                        } else {
                            System.out.printf("Erro ao alterar o treino\n");
                        }
                    } else {
                        System.out.printf("Carga nao alterada!\n");
                    }
                } else {
                    System.out.printf("O treino nao pertece ao aluno %s!\n", aluno.getNome());
                }
            } else {
                System.out.printf("Treino nao encontrado!\n");
            }
        } else {
            System.out.printf("Aluno nao cadastrado!\n");
        }
    }

    public void excluirTreino() {
        String cpf = Util.solicitarCpf(" do aluno");
        AlunoDadosDto aluno = banco_dados.buscarCpf(cpf);
        if (aluno != null) {
            int id_treino = Util.solicitarNum("Digite o ID do treino que deseja excluir:");
            AlunoTreinoDto aluno_treino = banco_treino.buscarId(id_treino);
            if (aluno_treino != null) {
                if (cpf.equals(aluno_treino.getCpf())) {
                    if (banco_treino.remover(id_treino)) {
                        System.out.printf("Treino excluido com sucesso!\n");
                    } else {
                        System.out.printf("Erro ao excluir o treino\n");
                    }
                } else {
                    System.out.printf("O treino nao pertece ao aluno %s!\n", aluno.getNome());
                }
            } else {
                System.out.printf("Treino nao encontrado!\n");
            }
        } else {
            System.out.printf("Aluno nao cadastrado!\n");
        }
    }

    public void realizarTreino() {
        String cpf = Util.solicitarCpf(" do aluno");
        int id_treino = Util.solicitarNum("Digite o id do treino:");
        AlunoTreinoDto aluno_treino = banco_treino.buscarId(id_treino);
        if (aluno_treino != null && cpf.equals(aluno_treino.getCpf())) {
            List<AlunoTreinoExercicioDto> lista_exercicios = banco_treino.buscarTreino(id_treino);
            System.out.printf("%s |", aluno_treino.getCpf());
            System.out.printf("%s |", aluno_treino.getNome_treino());
            System.out.printf("%d \n", aluno_treino.getId_treino());
            System.out.printf("---LISTA DE EXERCICIOS---");
            for (int i = 0; i < lista_exercicios.size(); i++) {

                ExerciciosDto exercicio = banco_exercicios.procurarExercicio(lista_exercicios.get(i).getId_exercicio());

                System.out.printf("ID treino: %d\n", lista_exercicios.get(i).getId_treino());
                System.out.printf("Id exercicio: %d\n", lista_exercicios.get(i).getId_exercicio());
                System.out.printf("Exercicio: %s\n", exercicio.getNome());
                System.out.printf("Musculos ativos: %s\n", exercicio.getMusculosAtivos());
                System.out.printf("Series: %d\n", lista_exercicios.get(i).getSeries());
                System.out.printf("Maximo de repeticoes: %d\n",
                        lista_exercicios.get(i).getMax_rep());
                System.out.printf("Minimo de repeticoes: %d\n",
                        lista_exercicios.get(i).getMin_rep());
                System.out.printf("Carga: %d kg\n", lista_exercicios.get(i).getCarga());
                System.out.printf("Tempo de descanso: %d segundos\n\n",
                        lista_exercicios.get(i).getTempo_descanso());

                System.out.printf("1 - Exercicio concluido!\n");
                System.out.printf("2 - Encerrar Treino!!\n");
                if (Util.solicitarAlternativas(1, 2, "Digite o numero da opcao:") == 1) {
                    if (i == lista_exercicios.size() - 1) {
                        System.out.printf("Treino concluido com sucesso!\n");
                        AlunoListaPresencaDao banco_presenca = new AlunoListaPresencaDao();
                        AlunoListaPresencaDto presenca = new AlunoListaPresencaDto();
                        presenca.setData_treino(LocalDate.now());
                        presenca.setId_treino(lista_exercicios.get(i).getId_treino());
                        presenca.setCpf(cpf);
                        if (banco_presenca.incluir(presenca)) {
                            System.out.printf("Treino registrado com sucesso!\n");
                        } else {
                            System.out.printf("Erro ao registrar o treino!\n");
                        }
                    }

                } else {
                    System.out.printf("Treino encerrado!\n");
                    System.out.printf("O treino nao foi registrado!\n");
                    break;
                }
                System.out.printf("\n\n");
            }

        } else {
            System.out.printf("O aluno nao possui esse treino!\n");
        }
    }
}