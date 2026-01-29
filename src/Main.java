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
            System.out.println("5. Export History");
            System.out.println("6. [ADMIN] Add a new Asset in Market");
            System.out.println("7. [ADMIN] Add a new Trader");
            System.out.println("0. ");
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
                        System.out.println("Trader ID : ");
                        String buyID = scanner.nextLine();
                        System.out.println("Asset code to buy : ");
                        String buyAsset = scanner.nextLine();
                        System.out.println("Quantity : ");
                        double buyQty = scanner.nextDouble();
                        market.buyAsset();
                }
            } catch ()
        }

    }
}