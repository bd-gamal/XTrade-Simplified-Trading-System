import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    // ===================================================
    //          2nd PART : ANALYZING WITH STREAMS
    // ===================================================


    public void displayTransactionsByTrader(String traderId) {
        System.out.println("----- HISTORY OF TRADER : " + traderId + " -----");
        transactions.stream().filter(t -> t.getTraderID().equals(traderId)).forEach(System.out::println);
    }

    public void filterTransactions(Transaction.TransactionType type, String assetCode, LocalDateTime startDate, LocalDateTime endDate) {
        System.out.println("---- FILTERED TRANSACTION ----");
        transactions.stream().filter(t -> type == null || t.getType() == type).filter(t -> assetCode == null || t.getAsset().getCode().equalsIgnoreCase(assetCode)).filter(t -> startDate == null || t.getDate().isAfter(startDate)).filter(t -> endDate == null || t.getDate().isBefore(endDate)).forEach(System.out::println);
    }

    public void sortedTransactions(boolean sortedByAmount) {
        System.out.println("---- SORTED TRANSACTION (" + (sortedByAmount ? "Amount" : "Date") + ") ----");
        transactions.stream().sorted(sortedByAmount ? Comparator.comparingDouble(t-> t.getQuantity() * t.getPriceAtTransaction()) : Comparator.comparing(Transaction::getDate)).forEach(System.out::println);
    }

    public void displayVolumePerAsset() {
        System.out.println("---- VOLUME (Quantity) PER ASSET ----");
        Map<String, Double> volume = transactions.stream().collect(Collectors.groupingBy(t-> t.getAsset().getCode(), Collectors.summingDouble(Transaction::getQuantity)));
        volume.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    public void displayTopTraders(int n) {
        System.out.println("---- TOP " + n + "TRADERS (per Volume $) ----");
        transactions.stream().collect(Collectors.groupingBy(Transaction::getTraderID, Collectors.summingDouble(t -> t.getQuantity() * t.getPriceAtTransaction()))).entrySet().stream().sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue())).limit(n).forEach(e-> System.out.println("Trader " + e.getKey() + " : " + String.format("%.2f", e.getValue()) + "$"));
    }

    public void displayTotalMarketValue() {
        double total = transactions.stream().mapToDouble(t-> t.getQuantity() * t.getPriceAtTransaction()).sum();
        System.out.println("---- Total Amount of Market : "+ String.format("%.2f", total) + " $ ----");
    }

    public void displayTraderVolume(String traderId) {
        double total = transactions.stream().filter(t-> t.getTraderID().equals(traderId)).mapToDouble(t-> t.getQuantity() * t.getPriceAtTransaction()).sum();
        System.out.println("---- Trader Volume " + traderId + " : " + String.format("%.2f", total) + "$ ----");
    }

    public void displayTraderOrderCount(String traderId) {
        long count = transactions.stream().filter(t-> t.getTraderID().equals(traderId)).count();
        System.out.println("---- Order number for " + traderId + " : " + count + " ----");
    }

    public void displayMostTradedAssets() {
        System.out.println("---- MOST TRADED ASSETS ----");
        transactions.stream().collect(Collectors.groupingBy(t->t.getAsset().getCode(), Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).ifPresent(e-> System.out.println("Top Asset " + e.getKey() + " ( " + e.getValue() + "transactions )"));
    }

    public void displayBuySellSplitted() {
        double totalBuy = transactions.stream().filter(t-> t.getType() == Transaction.TransactionType.PURCHASE).mapToDouble(t-> t.getQuantity() * t.getPriceAtTransaction()).sum();
        double totaLSell = transactions.stream().filter(t-> t.getType() == Transaction.TransactionType.SALE).mapToDouble(t-> t.getQuantity() * t.getPriceAtTransaction()).sum();

        System.out.println("TOTAL PURCHASES : " + totalBuy + " $");
        System.out.println("TOTAL SALES : " + totaLSell + " $");
    }
}
