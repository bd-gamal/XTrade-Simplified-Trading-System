public class Stock extends Asset {
    private String companyName;

    public Stock(String code, String label, double price, String companyName) {
        super(code, label, price);
        this.companyName = companyName;
    }

    public String toString() {
        return "Asset " + super.toString() + " | Company name : " + companyName;
    }
}
