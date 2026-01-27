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
}
