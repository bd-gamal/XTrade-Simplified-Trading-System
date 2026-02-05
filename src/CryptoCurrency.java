public class CryptoCurrency extends Asset {
    public CryptoCurrency(String code, String label, double price, double quantity) {
        super(code, label, price, quantity);
    }

    public String toString() {
        return "Crypto : " + super.toString();
    }
}
