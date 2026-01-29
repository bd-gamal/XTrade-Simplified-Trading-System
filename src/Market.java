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

        transactions.add(new Transaction(Transaction.TransactionType.PURSHASE, asset, quantity, asset.getPrice()));
        System.out.println("Successful purchase !");
    }

    public void sellAsset(String traderId, String assetCode, double quantity) throws Exception {
        if (quantity <= 0) throw new Exception("Quantity must be positive !");

        Trader trader = findTraderById(traderId);
        Asset asset = findAssetByCode(assetCode);

        if (trader == null || asset == null) throw new Exception("Trader or Asset not found !");

        trader.getPortfolio().removePosition(asset.getCode(), quantity);
    }
}
