package com.example.tabelas;

import java.util.List;
import com.example.util.Util;
import com.example.dao.PlanosUtils;
import com.example.dto.PlanoDto;

public class Planos {

    public static void cadastrarPlano() {
        PlanosUtils banco_plano = new PlanosUtils();
        PlanoDto plano = new PlanoDto();
        plano.setCodigo(Util.solicitarNum("Código do plano:"));
        plano.setNome(Util.solicitarNome(" do plano"));
        plano.setMensalidade(Util.solicitarNumDouble("Valor do plano:"));
        if (banco_plano.adicionarPlano(plano)) {
            System.out.println("Plano inserido com sucesso!");
        } else {
            System.out.println("Erro ao adicionar um plano!");
        }
    }

    public static void exibirPlanos() {
        PlanosUtils banco_plano = new PlanosUtils();
        List<PlanoDto> lista_planos = banco_plano.listaPlanos();
        if (lista_planos.size() > 0) {
            System.out.println("Planos:");
            for (PlanoDto plano : lista_planos) {
                System.out.println(plano.toString());
            }
        } else {
            System.out.println("Nao existem planos cadastrados!");
        }
    }

    public static PlanoDto solicitarPlano() {
        PlanosUtils banco_plano = new PlanosUtils();
        int codigo;
        PlanoDto plano = null;
        do {
            codigo = Util.solicitarNum("Digite o codigo do plano:");
            plano = banco_plano.buscarPlano(codigo);
            if (plano == null) {
                System.out.printf("Nao existem plano com codigo %d, por favor forneça um válido!!\n", codigo);
            }
        } while (plano == null);
        return plano;
    }

}