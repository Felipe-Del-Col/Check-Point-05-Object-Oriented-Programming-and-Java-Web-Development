
package prova;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CentralChamados {

    private List<Chamado> chamados;

    public CentralChamados(List<Chamado> chamados) {
        this.chamados = new ArrayList<>(chamados);
    }

    public List<Chamado> semDuplicados() {

        List<Chamado> resultado = new ArrayList<>();
        Set<String> codigos = new HashSet<>();

        for (Chamado chamado : chamados) {
            if (!codigos.contains(chamado.getCodigo())) {
                codigos.add(chamado.getCodigo());
                resultado.add(chamado);
            }
        }

        Collections.sort(resultado, Comparator.comparingInt(Chamado::getPrioridade).reversed().thenComparing(Chamado::getCodigo));

        return resultado;
    }

    public Map<String, List<String>> abertosPorSetor() {

        Map<String, List<String>> resultado = new HashMap<>();

        for (Chamado chamado : semDuplicados()) {

            if (chamado.getStatus().equals("ABERTO")) {

                String setor = chamado.getSetor();

                if (!resultado.containsKey(setor)) {
                    resultado.put(setor, new ArrayList<>());
                }

                resultado.get(setor).add(chamado.getCodigo());
            }
        }

        for (String setor : resultado.keySet()) {
            Collections.sort(resultado.get(setor));
        }

        return resultado;
    }
    public List<ResumoSetor> resumoResolvidos() {

        Map<String, Long> quantidades = new HashMap<>();
        Map<String, Double> horas = new HashMap<>();

        for (Chamado chamado : chamados) {

            if (chamado.getStatus().equals("RESOLVIDO")) {

                String setor = chamado.getSetor();

                if (!quantidades.containsKey(setor)) {
                    quantidades.put(setor, 0L);
                    horas.put(setor, 0.0);
                }

                quantidades.put(setor, quantidades.get(setor) + 1);

                horas.put(setor, horas.get(setor) + chamado.getHoras());
            }
        }

        List<ResumoSetor> resultado = new ArrayList<>();

        for (String setor : quantidades.keySet()) {
            resultado.add(new ResumoSetor(setor, quantidades.get(setor), horas.get(setor)));
        }

        Collections.sort(resultado, Comparator.comparingDouble(ResumoSetor::totalHoras).reversed().thenComparing(ResumoSetor::setor));

        return resultado;
    }

    public List<String> tagsMaisFrequentes(int n) {

        if (n < 1) {
            throw new IllegalArgumentException();
        }

        Map<String, Integer> frequencia = new HashMap<>();

        for (Chamado chamado : chamados) {

            if (!chamado.getStatus().equals("CANCELADO")) {

                Set<String> tagsDoChamado = new HashSet<>();

                for (String tag : chamado.getTags()) {
                    tagsDoChamado.add(tag);
                }

                for (String tag : tagsDoChamado) {

                    if (!frequencia.containsKey(tag)) {
                        frequencia.put(tag, 0);
                    }

                    frequencia.put(tag, frequencia.get(tag) + 1);
                }
            }
        }
        List<String> resultado = new ArrayList<>(frequencia.keySet());

        Collections.sort(resultado, Comparator.comparing((String tag) -> frequencia.get(tag)).reversed().thenComparing(tag -> tag));

        if (resultado.size() > n) {
            return new ArrayList<>(resultado.subList(0, n));
        }

        return resultado;
    }
}

