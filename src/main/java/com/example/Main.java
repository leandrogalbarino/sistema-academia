package com.example;

import com.example.util.*;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        boolean sair = false;

        while (!sair) {
            menu.exibirMenuPrincipal();
            int opcao = Util.solicitarAlternativas(1, 8, "DIGITE O NUMERO QUE DESEJA REALIZAR:");

            switch (opcao) {
                case 1:
                    menu.menuAlunosDados();
                    break;
                case 2:
                    menu.menuAlunoPlano();
                    break;
                case 3:
                    menu.menuAlunoTreino();
                    break;
                case 4:
                    menu.menuInstrutorTreino();
                    break;
                case 5:
                    menu.menuExercicios();
                    break;
                case 6:
                    menu.menuPlanos();
                    break;
                case 7:
                    menu.menuRelatorios();
                    break;
                case 8:
                    sair = true;
                    break;
                default:
                    break;
            }
        }

        System.out.println("Programa encerrado.");
    }
}
