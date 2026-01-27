import java.util.HashMap;
import java.util.Map;

public class Portfolio<T extends Asset> {
    private Map<String, Double> positions = new HashMap<>();

    public void addPosition(String assetCode, double quantity) {
        positions.put(assetCode, quantity.getOrDefault(assetCode, 0.0) + quantity);
    }

}
