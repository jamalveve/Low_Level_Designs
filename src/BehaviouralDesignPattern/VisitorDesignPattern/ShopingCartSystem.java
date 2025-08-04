package BehaviouralDesignPattern.VisitorDesignPattern;

// Visitor interface
interface ShoppingCartVisitor {
    int visit(Book book);

    int visit(Fruit fruit);
}

// Element interface
interface ItemElement {
    int accept(ShoppingCartVisitor visitor);
}

// Concrete element: Book
class Book implements ItemElement {
    private int price;
    private String isbn;

    public Book(int price, String isbn) {
        this.price = price;
        this.isbn = isbn;
    }

    public int getPrice() {
        return price;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}

// Concrete element: Fruit
class Fruit implements ItemElement {
    private int pricePerKg;
    private int weight;
    private String name;

    public Fruit(int pricePerKg, int weight, String name) {
        this.pricePerKg = pricePerKg;
        this.weight = weight;
        this.name = name;
    }

    public int getPricePerKg() {
        return pricePerKg;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}

// Concrete visitor: ShoppingCartVisitorImpl
class ShoppingCartVisitorImpl implements ShoppingCartVisitor {
    @Override
    public int visit(Book book) {
        int cost = book.getPrice();
        System.out.println("Book ISBN::" + book.getIsbn() + " cost =" + cost);
        return cost;
    }

    @Override
    public int visit(Fruit fruit) {
        int cost = fruit.getPricePerKg() * fruit.getWeight();
        System.out.println(fruit.getName() + " cost = " + cost);
        return cost;
    }
}

// Client code
public class ShopingCartSystem {
    public static void main(String[] args) {
        ItemElement[] items = new ItemElement[] {
                new Book(20, "1234"),
                new Book(100, "5678"),
                new Fruit(10, 2, "Banana"),
                new Fruit(5, 5, "Apple")
        };

        int total = 0;
        ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();

        for (ItemElement item : items) {
            total += item.accept(visitor);
        }

        System.out.println("Total Cost = " + total);
    }
}
