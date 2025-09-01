import java.util.Scanner;

public class GroceryCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter coupon amount as a decimal (e.g., .10 for 10%): ");
        double coupon = sc.nextDouble();
        if (coupon <= 0 || coupon > 1) {
            System.out.println("Invalid coupon. Defaulting to 10%.");
            coupon = 0.10;
        }

        double total = 0;
        for (int week = 1; week <= 4; week++) {
            System.out.print("Enter grocery bill for week " + week + ": ");
            total += sc.nextDouble();
        }

        double weeklyAvg = total / 4.0;
        double monthlyWithCoupon = total - (total * coupon);
        double weeklyWithCoupon = monthlyWithCoupon / 4.0;

        System.out.println("\n--- Grocery Bill Summary ---");
        System.out.printf("Monthly Total without coupon: $%.2f%n", total);
        System.out.printf("Weekly Average without coupon: $%.2f%n", weeklyAvg);
        System.out.printf("Monthly Total with coupon: $%.2f%n", monthlyWithCoupon);
        System.out.printf("Weekly Average with coupon: $%.2f%n", weeklyWithCoupon);
        sc.close();
    }
}
