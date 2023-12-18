public class No {
    public String valor;
    public No esquerda;
    public No direita;
    private No pai;

    public int balanceamento = 0;
    public ListaDinamica linhas = new ListaDinamica();

    public No(String valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
        setBalanceamento(0);
    }
    @Override
    public  String toString(){
        return valor + linhas.toString();
    }

    public int getBalanceamento() {
        return balanceamento;
    }

    public void setBalanceamento(int balanceamento) {
        this.balanceamento = balanceamento;
    }

    public No getDireita() {
        return direita;
    }

    public No setDireita(No direita) {
        this.direita = direita;
        return direita;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getPai() {
        return pai;
    }

    public No setPai(No pai) {
        this.pai = pai;
        return pai;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(int String) {
        this.valor = valor;
    }
}