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

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        if (balance < amount) throw new Exception("Insufficient balance");{
            this.balance -= amount;
        }
    }

    public Portfolio<Asset> getPortfolio() {
        return portfolio;
    }

    public String toString() {
        return "Trader: " + getName() + " | Balance: " + String.format("%.2f", balance) + "$";
    }
}
