import java.text.Normalizer;

public class TabelaHash {
    ArvoreBinariaBusca[] v;

    public TabelaHash() {
        v = new ArvoreBinariaBusca[26];
    }

    public void add(String elemento) {
        int posicao = h(elemento);

        if(v[posicao] == null) {
            v[posicao] = new ArvoreBinariaBusca();
        }

        v[posicao].adicionar(elemento);
    }


    public void remove(String elemento) {
        int posicao = h(elemento);

        if(v[posicao] != null) {
            v[posicao].remover(elemento);
        }
    }


    public int clear() {
        return 0;
    }

    public int h(String x) {
        String letrasComAcento = Normalizer.normalize(x,Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        x = letrasComAcento;
        char c = x.toUpperCase().charAt(0);
        int valorLetra = c - 'A';
        return valorLetra;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < v.length; i++) {
            if (v[i] != null) {
                // Chama o método exibirEmOrdem apenas se a posição não for null
                str.append(((ArvoreBinariaBusca) v[i]).exibirEmOrdem());
            }

        }

        return str.toString();
    }
}