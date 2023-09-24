import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private String nome;
    private double preco;

    public Product(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}

class Order {
    private List<Product> items = new ArrayList<>();

    public void addItem(Product item) {
        items.add(item);
    }

    public double calculoTotal() {
        double total = 0;
        for (Product item : items) {
            total += item.getPreco();
        }
        return total;
    }

    public List<Product> getItems() {
        return items;
    }
}

public class OrderManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Order> orders = new ArrayList<>();

        while (true) {
            printMenu();
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    createOrder(orders);
                    break;
                case 2:
                    addProductToOrder(orders, scanner);
                    break;
                case 3:
                    calculoOrderTotal(orders);
                    break;
                case 4:
                    listOrderItems(orders);
                    break;
                case 5:
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Criar Pedido");
        System.out.println("2. Adicionar Produto ao Pedido");
        System.out.println("3. Calcular Total do Pedido");
        System.out.println("4. Listar Produtos do Pedido");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void createOrder(List<Order> orders) {
        Order newOrder = new Order();
        orders.add(newOrder);
        System.out.println("Pedido criado.");
    }

    private static void addProductToOrder(List<Order> orders, Scanner scanner) {
        if (orders.isEmpty()) {
            System.out.println("Crie um pedido primeiro.");
            return;
        }

        System.out.print("Digite o nome do produto: ");
        String productNome = scanner.next();
        System.out.print("Digite o preço do produto: ");
        double productPreco = scanner.nextDouble();

        Product product = new Product(productNome, productPreco);
        Order lastOrder = orders.get(orders.size() - 1);
        lastOrder.addItem(product);
        System.out.println("Produto adicionado ao pedido.");
    }

    private static void calculoOrderTotal(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("Crie um pedido primeiro.");
            return;
        }

        Order currentOrder = orders.get(orders.size() - 1);
        double total = currentOrder.calculoTotal();
        System.out.println("Total do pedido: " + total);
    }

    private static void listOrderItems(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("Crie um pedido primeiro.");
            return;
        }

        Order orderToDisplay = orders.get(orders.size() - 1);
        List<Product> orderItems = orderToDisplay.getItems();

        System.out.println("Produtos do pedido:");
        for (Product item : orderItems) {
            System.out.println(item.getNome() + ": " + item.getPreco());
        }
    }
}
