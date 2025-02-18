import java.util.*;

class Card {
    private String symbol;
    private String name;

    public Card(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Card{" + "symbol='" + symbol + '\'' + ", name='" + name + "'}";
    }
}

public class CardCollection {
    private Collection<Card> cards;

    public CardCollection() {
        cards = new ArrayList<>();
    }

    public void addCard(String symbol, String name) {
        cards.add(new Card(symbol, name));
    }

    public List<Card> findCardsBySymbol(String symbol) {
        List<Card> result = new ArrayList<>();
        for (Card card : cards) {
            if (card.getSymbol().equalsIgnoreCase(symbol)) {
                result.add(card);
            }
        }
        return result;
    }

    public void displayAllCards() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection collection = new CardCollection();

        while (true) {
            System.out.println("1. Add Card");
            System.out.println("2. Find Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter card symbol: ");
                    String symbol = scanner.nextLine();
                    System.out.print("Enter card name: ");
                    String name = scanner.nextLine();
                    collection.addCard(symbol, name);
                    break;
                case 2:
                    System.out.print("Enter symbol to search: ");
                    symbol = scanner.nextLine();
                    List<Card> foundCards = collection.findCardsBySymbol(symbol);
                    if (foundCards.isEmpty()) {
                        System.out.println("No cards found for symbol: " + symbol);
                    } else {
                        for (Card card : foundCards) {
                            System.out.println(card);
                        }
                    }
                    break;
                case 3:
                    collection.displayAllCards();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
