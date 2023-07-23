import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;


public class ManipulacaoDadosPersistentes {
    private static final String ARQUIVO_PRODUTOS = "produtos.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1 - Adicionar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Quantidade Total de Produtos");
            System.out.println("4 - Sair");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();

            if (opcao == 1) {
                adicionarProduto();
            } else if (opcao == 2) {
                listarProdutos();
            } else if (opcao == 3) {
                quantidadeTotalProdutos();
            } else if (opcao == 4) {
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    private static void adicionarProduto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o preço do produto: ");
        double preco = scanner.nextDouble();
        System.out.print("Digite a quantidade do produto: ");
        int quantidade = scanner.nextInt();

        Produto produto = new Produto(nome, preco, quantidade);
        try {
            FileWriter arquivo = new FileWriter(ARQUIVO_PRODUTOS, true);
            arquivo.write(produto.nome + ";" + produto.preco + ";" + produto.quantidade + "\n");
            arquivo.close();
            System.out.println("Produto adicionado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao gravar no arquivo.");
            e.printStackTrace();
        }
    }

    private static void listarProdutos() {
        try {
            FileReader arquivo = new FileReader(ARQUIVO_PRODUTOS);
            BufferedReader leitor = new BufferedReader(arquivo);
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");
                Produto produto = new Produto(dados[0], Double.parseDouble(dados[1]), Integer.parseInt(dados[2]));
                System.out.println(produto);
            }
            arquivo.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo.");
            e.printStackTrace();
        }
    }

    private static void quantidadeTotalProdutos() {
        try {
            FileReader arquivo = new FileReader(ARQUIVO_PRODUTOS);
            BufferedReader leitor = new BufferedReader(arquivo);
            int quantidadeTotal = 0;
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");
                int quantidade = Integer.parseInt(dados[2]);
                quantidadeTotal += quantidade;
            }
            arquivo.close();
            System.out.println("Quantidade total de produtos: " + quantidadeTotal);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo.");
            e.printStackTrace();
        }
    }
}
