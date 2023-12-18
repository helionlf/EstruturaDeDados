import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        TabelaHash hash = new TabelaHash();

        FileInputStream chaves = new FileInputStream("C:\\Users\\Ivonizete\\Documents\\chave.txt");

        Scanner scanner = new Scanner(chaves);

        while (true){

            while (scanner.hasNextLine()) {

                String linhaChave = scanner.nextLine();
                String[] vetorChave = linhaChave.split(" ");

                for (int i = 0; i < vetorChave.length; i++) {
                    hash.add(vetorChave[i].toLowerCase());
                }
            }

            FileInputStream texto = new FileInputStream("C:\\Users\\Ivonizete\\Documents\\texto.txt");

            scanner = new Scanner(texto);

            int contLinha = 0;
            while (scanner.hasNextLine()) {
                contLinha++;
                String linhaTexto = scanner.nextLine();
                String[] vetorTexto = linhaTexto.split("[^\\p{L}-]+");
                

                for(int n = 0; n < vetorTexto.length; n++ ) {
                    if(!vetorTexto[n].isEmpty() && !vetorTexto[n].equals("-")) {

                        int pos = hash.h(vetorTexto[n]);
                        if(pos < 26 && pos > -1) {
                            if (hash.v[pos] != null) {
                                No aux = hash.v[pos].getNo(vetorTexto[n].toLowerCase());

                                if (aux != null) {
                                    aux.linhas.add(contLinha);
                                }
                            }
                        }
                    }
                }
            }


            System.out.println("\n ------------------------------------------------------------ \n");

            try {
                FileWriter myIndiceRemessivo = new FileWriter("C:\\Users\\Ivonizete\\Documents\\indiceRemissivo.txt");
                myIndiceRemessivo.close();
            } catch (IOException e) {
                System.out.println("Erro ao criar arquivo");
                e.printStackTrace();
            }

            String fileName = "C:\\Users\\Ivonizete\\Documents\\indiceRemissivo.txt";
            String encoding = "UTF-8";
            try {
                PrintWriter writer = new PrintWriter(fileName, encoding);
                writer.println(hash);
                writer.close();
            } catch (IOException e) {
                System.out.println("Erro ao escrever no arquivo");
                e.printStackTrace();
            }
            //System.out.println(hash);
            break;
        }

        System.out.println("Criamos um Indice Remissvel em: Documents\\indiceRemissivo.txt");


    }
}