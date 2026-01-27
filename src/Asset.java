public abstract class Asset {
    protected String code;
    protected String label;
    protected double price;

    public Asset(String code, String label, double price) {
        this.code = code;
        this.label = label;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return "Code : " + code + " Label : " + label + " Price : " + price + " $";
    }
}
