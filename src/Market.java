import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Market {
    private List<Asset> marketAssets = new ArrayList<>();
    private List<Trader> traders = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    public void  addAsset(Asset asset) {
        marketAssets.add(asset);
    }

    public void displayMarketAssets() {
        System.out.println("=== Market Assets ===");
        for (Asset a : marketAssets) {
            System.out.println(a);
        }
    }

    public Asset findAssetByCode(String code) {
        return marketAssets.stream().filter(a -> a.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }

    public void addTrader(Trader trader) {
        traders.add(trader);
    }

    public Trader findTraderById(String id) {
        return traders.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    public void buyAsset(String traderId, String assetCode, double quantity) throws Exception {
        if (quantity <= 0) throw new Exception("Quantity must be positive !");

        Trader trader = findTraderById(traderId);
        Asset asset = findAssetByCode(assetCode);

        if (trader == null || asset == null) throw new Exception("Trader or Asset not found !");

        double totalCost = asset.getPrice() * quantity;

        trader.withdraw(totalCost);
        trader.getPortfolio().addPosition(asset.getCode(), quantity);

        transactions.add(new Transaction(traderId, Transaction.TransactionType.PURCHASE, asset, quantity, asset.getPrice()));
        System.out.println("Successful purchase !");
    }

    public void sellAsset(String traderId, String assetCode, double quantity) throws Exception {
        if (quantity <= 0) throw new Exception("Quantity must be positive !");

        Trader trader = findTraderById(traderId);
        Asset asset = findAssetByCode(assetCode);

        if (trader == null || asset == null) throw new Exception("Trader or Asset not found !");

        trader.getPortfolio().removePosition(asset.getCode(), quantity);

        double totalGain = asset.getPrice() * quantity;
        trader.deposit(totalGain);

        transactions.add(new Transaction(traderId, Transaction.TransactionType.SALE, asset, quantity, asset.getPrice()));
        System.out.println("Successful sale !");
    }

    public void displayTransactions() {
        for (Transaction trans : transactions) {
            System.out.println(trans);
        }
    }

    public List<Transaction> function(double quantity) {
        return transactions.stream().filter(t -> t.getQuantity() == quantity).toList();
    }


    // ==================================================
    //          2nd PART : ANALYZING WITH STREAMS
    // ==================================================


    public void displayTransactionsByTrader(String traderId) {
        System.out.println("----- TRANSACTIONS OF TRADER " + traderId + " -----");
        transactions.stream().filter(t -> t.getTraderID().equals(traderId)).forEach(System.out::println);
    }

    public void filterTransactions(Transaction.TransactionType type, String assetCode, LocalDateTime startDate, LocalDateTime endTime) {
        System.out.println("---- FILTERED TRANSACTION ----");
        transactions.stream().filter(t -> type == null || t.getType() == type).filter(t -> assetCode == null || t.getAsset().getCode().equalsIgnoreCase(assetCode))
    }
}
