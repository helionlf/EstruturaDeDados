public class ListaDinamica {
    int cont = 0;
    NoLista primeiro = null;
    NoLista ultimo = null;

    public void add(int elemento) { // método para adicionar elemento na lista de forma ordenada
        if(!contain(elemento)) {
            NoLista novo = new NoLista(elemento);

            if (primeiro == null) {
                primeiro = novo;
                ultimo = novo;
            } else {
                ultimo.proximo = novo;
                novo.anterior=ultimo;
                ultimo = novo;

            }

            cont++;
        }
    }

    public boolean contain(int elemento) { //método que verifica se um elemento existe na lista
        NoLista aux = primeiro;

        while (aux != null) {
            if (aux.dado == elemento ){
                return true;
            } else {
                aux = aux.proximo;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        NoLista aux = primeiro;
        String str = " ";

        while (aux != null) {
            str += " " + aux.dado;
            aux = aux.proximo;
        }

        return str + " ";
    }
}