import java.util.HashMap;
import java.util.Map;

public class Portfolio<T extends Asset> {
    private Map<String, Double> positions = new HashMap<>();

    public void addPosition(String assetCode, double quantity) {
        positions.put(assetCode, positions.getOrDefault(assetCode, 0.0) + quantity);
    }

    public void removePosition(String assetCode, double quantity) throws Exception {
        if (!positions.containsKey(assetCode)) {
            throw new Exception("Asset not found in the portfolio");
        }
        double curreentQty = positions.get(assetCode);
        if (curreentQty < quantity) {
            throw new Exception("Insufficient quantity to sell");
        }

        double newQty = curreentQty - quantity;
        if (newQty == 0) {
            positions.remove(assetCode);
        } else {
            positions.put(assetCode, quantity);
        }
    }

    public Map<String, Double> getPositions() {
        return positions;
    }

    public double getQuantity (String assetCode) {
        return positions.getOrDefault(assetCode, 0.0);
    }
}