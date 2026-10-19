
package prova;

public record ResumoSetor(String setor, long quantidade, double totalHoras) {

    public ResumoSetor {
        if (setor == null || setor.isBlank()) {
            throw new IllegalArgumentException();
        }

        if (quantidade < 1) {
            throw new IllegalArgumentException();
        }

        if (totalHoras < 0) {
            throw new IllegalArgumentException();
        }

        setor = setor.trim();
    }
}

