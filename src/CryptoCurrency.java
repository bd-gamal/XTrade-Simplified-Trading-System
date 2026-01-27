public class CryptoCurrency extends Asset {
    public CryptoCurrency(String code, String label, double price) {
        super(code, label, price);
    }

    public String toString() {
        return "Crypto : " + super.toString();
    }
}
