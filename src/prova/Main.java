
package prova;
// Nome: Felipe Del Col
// RM: 556176

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        List<Chamado> chamados = new ArrayList<>();

        chamados.add(new Chamado("CH-101", "Financeiro", 3, 2.5, List.of("vpn", "acesso"), "RESOLVIDO"));
        chamados.add(new Chamado("CH-102", "TI", 5, 6.0, List.of("servidor", "backup"), "ABERTO"));
        chamados.add(new Chamado("CH-103", "RH", 2, 1.0, List.of("impressora"), "RESOLVIDO"));
        chamados.add(new Chamado("CH-104", "Financeiro", 4, 3.5, List.of("ERP", "acesso", "erro"), "RESOLVIDO"));
        chamados.add(new Chamado("CH-105", "Marketing", 1, 0.5, List.of("email"), "CANCELADO"));
        chamados.add(new Chamado("CH-106", "TI", 5, 4.0, List.of("servidor", "rede"), "RESOLVIDO"));
        chamados.add(new Chamado("CH-107", "RH", 3, 2.0, List.of("acesso", "vpn"), "ABERTO"));
        chamados.add(new Chamado("CH-102", "TI", 2, 1.0, List.of("impressora"), "RESOLVIDO"));
        chamados.add(new Chamado("CH-108", "Marketing", 4, 3.0, List.of("rede", "vpn"), "ABERTO"));
        chamados.add(new Chamado("CH-109", "Financeiro", 2, 4.0, List.of("erp"), "ABERTO"));
        chamados.add(new Chamado("CH-110", "TI", 3, 2.0, List.of("backup"), "resolvido"));
        chamados.add(new Chamado("CH-103", "Marketing", 5, 9.0, List.of("email"), "ABERTO"));
        chamados.add(new Chamado("CH-111", "RH", 4, 5.5, List.of("vpn", "Acesso"), "RESOLVIDO"));
        chamados.add(new Chamado("CH-112", "TI", 1, 0.5, List.of("email", "rede"), "CANCELADO"));
        chamados.add(new Chamado("CH-113", "RH", 2, 1.5, List.of("VPN"), "aberto"));

        CentralChamados central = new CentralChamados(chamados);

        System.out.println("== A ==");
        for (Chamado chamado : central.semDuplicados()) {
            System.out.println(chamado.getCodigo() + ";" + chamado.getPrioridade());
        }

        System.out.println("== B ==");
        List<String> setores = new ArrayList<>(central.abertosPorSetor().keySet());

        Collections.sort(setores);

        for (String setor : setores) {
            System.out.println(setor + ";" + central.abertosPorSetor().get(setor));
        }


        System.out.println("== C ==");
        for (ResumoSetor resumo : central.resumoResolvidos()) {
            System.out.println(resumo.setor() + ";" + resumo.quantidade() + ";" + String.format(Locale.US, "%.2f", resumo.totalHoras()));
        }


        System.out.println("== D ==");
        System.out.println(central.tagsMaisFrequentes(3));
    }
}

