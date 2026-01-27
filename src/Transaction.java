import java.time.LocalDateTime;

public class Transaction {
    public enum TransactionType { BUY, SELL }
    private TransactionType type;
    private Asset asset;
    private double quantity;
    private double priceAtTransaction;
    private LocalDateTime date;

    public Transaction(TransactionType type, Asset asset, double quantity, double priceAtTransaction, LocalDateTime date) {
        this.type = type;
        this.asset = asset;
        this.quantity = quantity;
        this.priceAtTransaction = priceAtTransaction;
        this.date = LocalDateTime.now();
    }

    public String toString() {
        return date + " | " + type + " | " + asset.getCode() + " | Quantity: " + quantity + " | Price: " + priceAtTransaction;
    }
}
