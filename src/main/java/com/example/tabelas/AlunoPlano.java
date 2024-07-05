package com.example.tabelas;

import com.example.util.Util;

import java.time.LocalDate;

import com.example.dao.AlunoDadosDao;

import com.example.dto.CartaoDto;
import com.example.dao.CartaoDao;
import com.example.dao.PlanosUtils;
import com.example.dao.AlunoPlanoDao;
import com.example.dto.AlunoPlanoDto;
import com.example.dto.AlunoDadosDto;
import com.example.dto.PlanoDto;

public class AlunoPlano {
    CartaoDao cartao_banco = new CartaoDao();
    PlanosUtils banco_plano = new PlanosUtils();
    AlunoPlanoDao banco = new AlunoPlanoDao();
    AlunoDadosDao bancoAluno = new AlunoDadosDao();

    private int cadastrarCartao() {
        System.out.println("-----DADOS DO CARTAO DE CREDITO -----");
        CartaoDto cartao = new CartaoDto();
        cartao.setNome(Util.solicitarNome(" no cartão"));
        cartao.setCvv(Util.solicitarNumString(" do cvv", 3, 3));
        cartao.setData_validade(Util.solicitarData2("Data de validade do cartao"));
        cartao.setNumero(Util.solicitarNumString(" no cartao", 13, 16));
        int id = cartao_banco.incluir(cartao);
        return id;
    }

    public void cadastrarPlano() {
        String cpf = Util.solicitarCpf(" do aluno");
        AlunoDadosDto aluno = bancoAluno.buscarCpf(cpf);
        AlunoPlanoDto aluno_plano = banco.buscarAlunoPlano(cpf);
        if (aluno != null) {
            if (aluno_plano == null) {
                if (banco_plano.listaPlanos().size() > 0) {
                    Planos.exibirPlanos();
                    int id_cartao = cadastrarCartao();
                    PlanoDto plano = Planos.solicitarPlano();

                    aluno_plano = new AlunoPlanoDto();
                    aluno_plano.setData_inicio(LocalDate.now());
                    aluno_plano.setId_cartao(id_cartao);
                    aluno_plano.setId_plano(plano.getCodigo());
                    aluno_plano.setAluno_cpf(cpf);

                    if (banco.incluir(aluno_plano)) {
                        System.out.printf("O plano foi cadastrado com sucesso para o aluno %s!\n", aluno.getNome());
                    } else {
                        System.out.printf("Erro ao cadastrar o plano para o aluno %s!\n", aluno.getNome());
                    }
                } else {
                    System.out.printf("Nao existem plano para cadastrar!\n");
                }
            } else {
                System.out.printf("O aluno %s ja tem um plano cadastrado!\n", aluno.getNome());
            }
        } else {
            System.out.println("Aluno nao cadastrado!\n");
        }
    }

    public void cancelarPlano() {
        String cpf = Util.solicitarCpf(" do aluno");
        AlunoDadosDto aluno = bancoAluno.buscarCpf(cpf);
        AlunoPlanoDto aluno_plano = null;
        if (aluno != null) {
            aluno_plano = banco.buscarAlunoPlano(cpf);
            if (aluno_plano != null) {
                if (Util.solicitarSimNao("Deseja mesmo cancelar o plano:")) {
                    if (banco.remover(cpf)) {
                        if (!cartao_banco.remover(aluno_plano.getId_cartao())) {
                            System.out.println("Erro ao cancelar o cartao de credito!");
                        }
                        System.out.printf("O plano de %s foi cancelado com sucesso!\n", aluno.getNome());
                    } else {
                        System.out.printf("Erro ao cancelar o plano de %s!\n", aluno.getNome());
                    }
                }
            } else {
                System.out.printf("O aluno %s nao tem nenhum plano cadastrado!\n", aluno.getNome());
            }

        } else {
            System.out.println("Aluno nao cadastrado!\n");

        }
    }
}
