public class ArvoreBinariaBusca {
    public No raiz;

    public ArvoreBinariaBusca() {
        raiz = null;
    }

    public void adicionar(String valor) {
        if (raiz == null) {
            raiz = new No(valor);
        }
        else {
            adicionar(valor, raiz);
        }
    }

    private void adicionar(String valor, No raiz) {
        if (valor.compareToIgnoreCase(raiz.valor) < 0) {
            if (raiz.esquerda == null) {
                raiz.esquerda = new No(valor);
                verificarBalanceamento(raiz);

            } else {
                adicionar(valor, raiz.esquerda);
            }
        } else if (valor.compareToIgnoreCase(raiz.valor) > 0) {
            if (raiz.direita == null) {
                raiz.direita = new No(valor);
                verificarBalanceamento(raiz);

            } else {
                adicionar(valor, raiz.direita);
            }
        }
    }


    public boolean pesquisar(String valor) {
        if (raiz == null) {
            return false;
        }
        else {
            return pesquisar(valor, raiz);
        }
    }

    private boolean pesquisar(String valor, No raiz) {
        if (valor.compareTo(raiz.valor) == 0) {
            return true;
        }
        else if (valor.compareTo(raiz.valor) < 0) {
            if (raiz.esquerda == null) {
                return false;
            }
            else {
                return pesquisar(valor, raiz.esquerda);
            }
        }
        else if (valor.compareTo(raiz.valor) > 0) {
            if (raiz.direita == null) {
                return false;
            }
            else {
                return pesquisar(valor, raiz.direita);
            }
        }

        return false;
    }

    public No getNo(String valor) {
        if (raiz == null) {
            return null;
        }
        else {
            return getNo(valor, raiz);
        }
    }

    private No getNo(String valor, No raiz) {
        if (valor.compareTo(raiz.valor) == 0) {
            return raiz;
        }
        else if (valor.compareTo(raiz.valor) < 0) {
            if (raiz.esquerda == null) {
                return null;
            }
            else {
                return getNo(valor, raiz.esquerda);
            }
        }
        else if (valor.compareTo(raiz.valor) > 0) {
            if (raiz.direita == null) {
                return null;
            }
            else {
                return getNo(valor, raiz.direita);
            }
        }

        return null;
    }

    public void remover(String valor) {
        if (raiz != null) {
            remover(valor, raiz, null);
        }
    }

    private void remover(String valor, No raiz, No pai) {
        if (valor.compareTo(raiz.valor) == 0) {
            if (raiz.esquerda == null && raiz.direita == null) {      // Nó não tem filhos
                if (raiz == pai.esquerda)    // Nó é filho esquerdo?
                    pai.esquerda = null;
                else
                    pai.direita = null;
            }
            else if (raiz.esquerda != null && raiz.direita == null) { // Nó tem um filho (esquerdo)
                if (raiz == pai.esquerda)    // Nó é filho esquerdo?
                    pai.esquerda = raiz.esquerda;
                else
                    pai.direita = raiz.esquerda;
            }
            else if (raiz.esquerda == null && raiz.direita != null) { // Nó tem um filho (direito)
                if (raiz == pai.esquerda)    // Nó é filho esquerdo?
                    pai.esquerda = raiz.direita;
                else
                    pai.direita = raiz.direita;
            }
            else if (raiz.esquerda != null && raiz.direita != null) { // Nó tem dois filhos
                raiz.valor = menorValor(raiz.direita);
                remover(raiz.valor, raiz.direita, raiz);
            }
        }
        else if (valor.compareTo(raiz.valor) < 0) {
            if (raiz.esquerda != null)
                remover(valor, raiz.esquerda, raiz);
        }
        else if (valor.compareTo(raiz.valor) > 0) {
            if (raiz.direita != null)
                remover(valor, raiz.direita, raiz);
        }
    }

    private String menorValor(No raiz) {
        if (raiz.esquerda == null)
            return raiz.valor;
        else
            return menorValor(raiz.esquerda);
    }



    public String exibirEmOrdem() {
        StringBuilder result = new StringBuilder();

        if (raiz != null) {
            exibirEmOrdem(raiz, result);
        }

        return result.toString();
    }

    private void exibirEmOrdem(No raiz, StringBuilder result) {
        if (raiz.esquerda != null) {
            exibirEmOrdem(raiz.esquerda, result);
        }

        result.append(raiz).append(" \n");

        if (raiz.direita != null) {
            exibirEmOrdem(raiz.direita, result);
        }
    }

    public void verificarBalanceamento(No atual) {
        setBalanceamento(atual);
        int balanceamento = atual.getBalanceamento();

        if (balanceamento < -1) {

            if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
                atual = rotacaoDireita(atual);

            } else {
                atual = duplaRotacaoEsquerdaDireita(atual);
            }

        } else if (balanceamento > 1) {

            if (altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())) {
                atual = rotacaoEsquerda(atual);

            } else {
                atual = duplaRotacaoDireitaEsquerda(atual);
            }
        }

        if (atual.getPai() != null) {
            verificarBalanceamento(atual.getPai());
        } else {
            this.raiz = atual;
        }
    }


    public No rotacaoEsquerda(No inicial) {

        No direita = inicial.getDireita();
        direita.setPai(inicial.getPai());

        inicial.setDireita(direita.getEsquerda());

        if (inicial.getDireita() != null) {
            inicial.getDireita().setPai(inicial);
        }

        direita.setEsquerda(inicial);
        inicial.setPai(direita);

        if (direita.getPai() != null) {

            if (direita.getPai().getDireita() == inicial) {
                direita.getPai().setDireita(direita);

            } else if (direita.getPai().getEsquerda() == inicial) {
                direita.getPai().setEsquerda(direita);
            }
        }

        setBalanceamento(inicial);
        setBalanceamento(direita);

        return direita;
    }

    public No rotacaoDireita(No inicial) {

        No esquerda = inicial.getEsquerda();
        esquerda.setPai(inicial.getPai());

        inicial.setEsquerda(esquerda.getDireita());

        if (inicial.getEsquerda() != null) {
            inicial.getEsquerda().setPai(inicial);
        }

        esquerda.setDireita(inicial);
        inicial.setPai(esquerda);

        if (esquerda.getPai() != null) {

            if (esquerda.getPai().getDireita() == inicial) {
                esquerda.getPai().setDireita(esquerda);

            } else if (esquerda.getPai().getEsquerda() == inicial) {
                esquerda.getPai().setEsquerda(esquerda);
            }
        }

        setBalanceamento(inicial);
        setBalanceamento(esquerda);

        return esquerda;
    }

    public No duplaRotacaoEsquerdaDireita(No inicial) {
        inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
        return rotacaoDireita(inicial);
    }

    public No duplaRotacaoDireitaEsquerda(No inicial) {
        inicial.setDireita(rotacaoDireita(inicial.getDireita()));
        return rotacaoEsquerda(inicial);
    }

    private void setBalanceamento(No no) {
        no.setBalanceamento(altura(no.getDireita()) - altura(no.getEsquerda()));
    }

    private int altura(No atual) {
        if (atual == null) {
            return -1;
        }

        if (atual.getEsquerda() == null && atual.getDireita() == null) {
            return 0;

        } else if (atual.getEsquerda() == null) {
            return 1 + altura(atual.getDireita());

        } else if (atual.getDireita() == null) {
            return 1 + altura(atual.getEsquerda());

        } else {
            return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
        }
    }

}