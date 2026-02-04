import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Market market = new Market();

        while (true) {
            System.out.println("\n==== XTrading Trade System ====");
            System.out.println("1. Display Market(List of Assets)");
            System.out.println("2. Buy an Asset");
            System.out.println("3. Sell an Asset");
            System.out.println("4. Display my Portfolio");
            System.out.println("5. Display History");
            System.out.println("6. [ADMIN] Add a new Asset in Market");
            System.out.println("7. [ADMIN] Add a new Trader");
            System.out.println("8. Market Analyze");
            System.out.println("0. Exit");
            System.out.print("Your Choice : ");

            int choice = -1;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter a Valid Number !");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        market.displayMarketAssets();
                        break;

                    case 2:
                        System.out.print("Trader ID : ");
                        String buyID = scanner.nextLine();
                        System.out.print("Asset code to buy : ");
                        String buyAsset = scanner.nextLine();
                        System.out.print("Quantity : ");
                        double buyQty = scanner.nextDouble();
                        market.buyAsset(buyID, buyAsset, buyQty);
                        break;

                    case 3:
                        System.out.print("Trader ID : ");
                        String sellID = scanner.nextLine();
                        System.out.print("Asset code to sell : ");
                        String sellAsset = scanner.nextLine();
                        System.out.print("Quantity : ");
                        double sellQty = scanner.nextDouble();
                        market.sellAsset(sellID, sellAsset, sellQty);
                        break;

                    case 4:
                        System.out.print("Trader ID : ");
                        String tradeID = scanner.nextLine();
                        Trader t = market.findTraderById(tradeID);
                        if (t != null) {
                            System.out.println(t);
                            System.out.println("---- PORTFOLIO ----");
                            if (t.getPortfolio().getPositions().isEmpty()) {
                                System.out.println("No Portfolio Found!");
                            } else {
                                for (Map.Entry<String, Double> entry : t.getPortfolio().getPositions().entrySet()) {
                                    System.out.println("- " + entry.getKey() + ": " + entry.getValue());
                                }
                            }
                        } else {
                            System.out.println("Trader not found. Create a trader first");
                        }
                        break;

                    case 5:
                        market.displayTransactions();
                        break;

                    case 6:
                        System.out.println("---- ADD ASSET TO MARKET ----");
                        System.out.println("Type : 1. Stock | 2. CryptoCurrency");
                        System.out.print("Choice : ");
                        int typeChoice = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Code (e.g BTC) : ");
                        String code = scanner.nextLine().toUpperCase();
                        System.out.print("Name (Label) : ");
                        String label = scanner.nextLine();
                        System.out.print("Price : ");
                        double price = scanner.nextDouble();
                        scanner.nextLine();

                        if (typeChoice == 1) {
                            System.out.print("Company's name : ");
                            String company = scanner.nextLine();
                            market.addAsset(new Stock(code, label, price, company));
                            System.out.println("Stock succesfully added!");
                        } else if (typeChoice == 2) {
                            market.addAsset(new CryptoCurrency(code, label, price));
                            System.out.println("Crypto succesfully added!");
                        } else {
                            System.out.println("Invalid type !");
                        }
                        break;

                    case 7:
                        System.out.println("---- ADD TRADER TO MARKET ---- ");
                        System.out.print("Unique ID : ");
                        String newID = scanner.nextLine();

                        if(market.findTraderById(newID) != null) {
                            System.out.println("Error : Trader already exists!");
                            break;
                        }

                        System.out.print("Trader name : ");
                        String newName = scanner.nextLine();
                        System.out.print("Initial Balance : ");
                        double initialBalance = scanner.nextDouble();
                        scanner.nextLine();

                        if(initialBalance < 0) {
                            System.out.println("Error : Initial Balance can't be negative!");
                        } else {
                            market.addTrader(new Trader(newName, newID, initialBalance));
                            System.out.println("Trader " + newName + " succesfully added !");
                        }
                        break;

                    case 8:
                        System.out.println("---- MARKET ANALYZE ----");
                        System.out.println("1. Trader History");
                        System.out.println("2. Volume per Asset");
                        System.out.println("3. Top Traders");
                        System.out.println("4. Total Purchase/Sale Global");
                        System.out.print("Choice : ");
                        int analysisChoices = scanner.nextInt();
                        scanner.nextLine();

                    case 0:
                        System.out.println("Closing the application ...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice !");
                }
            } catch (Exception e) {
                System.out.println("ERROR : " + e.getMessage());
                scanner.nextLine();
            }
        }
    }
}