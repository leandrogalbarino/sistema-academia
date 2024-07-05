package com.example.util;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Util {
    private static Scanner input = new Scanner(System.in);

    public static int solicitarAlternativas(int inicio, int fim, String str) {
        int num = 1;
        do {
            System.out.print(str);
            num = input.nextInt();
            input.nextLine();
            if (num < inicio || num > fim)
                System.out.println("Digite um número válido!");
        } while (num < inicio || num > fim);
        return num;
    }

    public static boolean solicitarSimNao(String str) {
        int num;
        do {
            System.out.println("1 - Sim");
            System.out.println("0 - Não");
            System.out.print(str);
            num = input.nextInt();
            input.nextLine();
            if (num < 0 || num > 1)
                System.out.println("Digite um número válido!");
        } while (num < 0 || num > 1);
        return num == 1 ? true : false;
    }

    public static int solicitarNum(String str) {
        int num;
        System.out.print(str);
        num = input.nextInt();
        input.nextLine();

        return num;
    }

    public static double solicitarNumDouble(String str) {
        double num;
        System.out.print(str);
        num = input.nextDouble();
        input.nextLine();

        return num;
    }

    public static String solicitarString(String str) {
        String string = "";
        System.out.print(str);
        string = input.nextLine();
        return string;
    }

    public static boolean validarNum(String str) {
        return str.matches("[0-9]+");
    }

    public static boolean validarLetras(String str) {
        return str.matches("[a-z A-Z]+");
    }

    public static String solicitarNome(String str) {
        String nome = "";
        do {
            nome = solicitarString("Digite o nome" + str + ":");
            if (!validarLetras(nome) || nome.length() < 2) {
                System.out.println("Digite um nome válido!!");
            }
        } while (!validarLetras(nome) || nome.length() < 2);
        return nome;
    }

    public static String solicitarCpf(String str) {
        String cpf;
        do {
            cpf = Util.solicitarString("Digite o cpf" + str + ":");
            if (!validarNum(cpf)) {
                System.out.println("O CPF deve conter apenas números!");
            } else if (cpf.length() != 11) {
                System.out.println("O CPF deve ter exatamente 11 números!");
            }
        } while (cpf.length() != 11 || !validarNum(cpf));
        return cpf;
    }

    public static String solicitarNumString(String str, int inicio, int fim) {
        String numero;
        do {
            numero = Util.solicitarString("Digite o numero" + str + ":");
            if (numero.length() < inicio || numero.length() > fim || !validarNum(numero)) {
                System.out.println("Por favor, forneça o numero" + str + " corretamente:");
            }
        } while (numero.length() < inicio || numero.length() > fim || !validarNum(numero));
        return numero;
    }

    public static boolean validarData2(String data) {
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("MM/yyyy");
            YearMonth data_analisada = YearMonth.parse(data, formato);
            YearMonth min_data = YearMonth.of(1900, 1);
            YearMonth max_data = YearMonth.of(2100, 12);
            return !data_analisada.isBefore(min_data) && !data_analisada.isAfter(max_data);
        } catch (Exception e) {
            return false;
        }
    }

    public static String solicitarData2(String str) {
        String data = solicitarString(str + " no formato MM/AAAA:");
        while (!validarData2(data)) {
            System.out.println(
                    "Você digitou a data no formato errado. Lembre-se de usar o formato MM/AAAA.");
            data = solicitarString("Por favor, tente novamente:");
        }
        return data;
    }

    public static LocalDate solicitarData(String str) {
        String data = solicitarString(str + " no formato DD/MM/AAAA:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            return LocalDate.parse(data, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Por favor, use o formato DD/MM/AAAA.");
            return solicitarData(str);
        }
    }

    public static String formatarDataEmPortugues(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

}
