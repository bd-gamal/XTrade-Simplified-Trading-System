import java.time.LocalDateTime;

public class Transaction {
    public enum TransactionType { PURCHASE, SALE }
    private String traderID;
    private TransactionType type;
    private Asset asset;
    private double quantity;
    private double priceAtTransaction;
    private LocalDateTime date;

    public Transaction(String traderID, TransactionType type, Asset asset, double quantity, double priceAtTransaction) {
        this.traderID = traderID;
        this.type = type;
        this.asset = asset;
        this.quantity = quantity;
        this.priceAtTransaction = priceAtTransaction;
        this.date = LocalDateTime.now();
    }

    public String getTraderID() {
        return traderID;
    }

    public TransactionType getType() {
        return type;
    }

    public Asset getAsset() {
        return asset;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPriceAtTransaction() {
        return priceAtTransaction;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String toString() {
        return date + " | Trader : " + traderID + " | " + type + " | " + asset.getCode() + " | Quantity: " + quantity + " | Price: " + priceAtTransaction;
    }
}
