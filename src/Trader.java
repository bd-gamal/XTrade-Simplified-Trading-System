public class Trader extends Person {
    private String id;
    private double balance;
    private Portfolio<Asset> portfolio;

    public Trader(String name, String id, double balance, Portfolio<Asset> portfolio) {
        super(name);
        this.id = id;
        this.balance = balance;
        this.portfolio = portfolio;
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void updateBalance(double balance) {

    }
}
