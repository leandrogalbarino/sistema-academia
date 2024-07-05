package com.example.tabelas;

import java.util.List;
import com.example.dao.ExerciciosUtils;
import com.example.dto.ExerciciosDto;
import com.example.util.Util;

public class Exercicios {

    public static void cadastrarExercicio() {
        ExerciciosUtils banco_exercicio = new ExerciciosUtils();

        ExerciciosDto exercicio = new ExerciciosDto();
        exercicio.setNome(Util.solicitarNome(" do exercicio"));
        exercicio.setMusculosAtivos(Util.solicitarString("Musculos Ativos:"));
        if (banco_exercicio.procurarNomeExercicio(exercicio.getNome()) == null) {
            if (banco_exercicio.adicionarExercicio(exercicio)) {
                System.out.println("Exercício inserido com sucesso!");
            } else {
                System.out.println("Erro ao criar exercicio!");
            }
        } else {
            System.out.println("Exercicio ja cadastrado!");
        }
    }

    public static void removerExercicio() {
        ExerciciosUtils banco_exercicio = new ExerciciosUtils();
        int num = Util.solicitarNum("Digite o numero do exercicio que deseja remover!\n");
        if (banco_exercicio.procurarExercicio(num) != null) {
            if (banco_exercicio.remover(num)) {
                System.out.println("Exercicio removido com sucesso!");
            } else {
                System.out.println("Erro ao remover exercicio!");
            }
        } else {
            System.out.println("Esse exercicio nao esta cadastrado!");
        }
    }

    public static void exibirExercicios() {
        ExerciciosUtils banco_exercicio = new ExerciciosUtils();

        List<ExerciciosDto> lista_exercicios = banco_exercicio.listaExercicios();
        if (lista_exercicios.size() > 0) {
            System.out.println("Exercícios:");
            for (ExerciciosDto exercicios : lista_exercicios) {
                System.out.println(exercicios.toString());
            }
        } else {
            System.out.println("Nao existem exercicios");
        }
    }

}
