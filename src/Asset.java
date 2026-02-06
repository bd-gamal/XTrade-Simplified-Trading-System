public abstract class Asset {
    protected String code;
    protected String label;
    protected double price;
    private double quantity;

    public Asset(String code, String label, double price, double quantity) {
        this.code = code;
        this.label = label;
        this.price = price;
        this.quantity = quantity;
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity() {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return "Code : " + code + " | Label : " + label + " | Price : " + price + " $ | Quantity : " + quantity;
    }
}
