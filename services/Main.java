package main;
import services.MobileFuel;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static final String RESET = "\u001B[0m"; // Reset color
    public static final String GREEN = "\u001B[32m"; // Green text
    public static final String PURPLE = "\u001B[35m"; // Purple text
    public static final String CYAN = "\u001B[36m"; // Cyan text
    public static final String WHITE = "\u001B[37m"; // White text
    public static final String BG_RED = "\u001B[41m"; // Background red

    public static void main(String[] args) throws InterruptedException {
        String[] levels = {
            "[E] *--------- [F]",  // Empty
            "[E] **-------- [F]",  // 10%
            "[E] ****------ [F]",  // 30%
            "[E] ******---- [F]",  // 50%
            "[E] ********-- [F]",  // 70%
            "[E] ********** [F]"   // Full Tank
        };

        for (String level : levels) {
            System.out.print("\r" + level);
            Thread.sleep(500);
        }
        System.out.println();

        System.out.println(WHITE + "############################################################################" + RESET);
        System.out.println(WHITE + "#" + RESET + GREEN + "   *********   *       *  *********  **********  **********  *         *" + RESET + WHITE + "  #" + RESET);
        System.out.println(WHITE + "#" + RESET + GREEN + "   *           **      *  *          *        *  *            *       *" + RESET + WHITE + "   #" + RESET);
        System.out.println(WHITE + "#" + RESET + GREEN + "   *           * *     *  *          *        *  *             *     *" + RESET + WHITE + "    #" + RESET);
        System.out.println(WHITE + "#" + RESET + GREEN + "   *           *  *    *  *          *        *  *              *   *" + RESET + WHITE + "     #" + RESET);
        System.out.println(WHITE + "#" + RESET + GREEN + "   *********   *   *   *  *********  * ********  *  *******      * *" + RESET + WHITE + "      #" + RESET);
        System.out.println(WHITE + "#" + RESET + GREEN + "   *           *    *  *  *          * *         *        *       *" + RESET + WHITE + "       #" + RESET);
        System.out.println(WHITE + "#" + RESET + GREEN + "   *           *     * *  *          *   *       *        *       *" + RESET + WHITE + "       #" + RESET);
        System.out.println(WHITE + "#" + RESET + GREEN + "   *           *      **  *          *     *     *        *       *" + RESET + WHITE + "       #" + RESET);
        System.out.println(WHITE + "#" + RESET + GREEN + "   *********   *       *  *********  *       *   **********       *" + RESET + WHITE + "       #" + RESET);
        System.out.println(WHITE + "############################################################################" + RESET);


	
        while (true) {
            System.out.println(CYAN + "\n1. Register\n2. Login\n3. Exit" + RESET);
            System.out.print("Choose an option: ");
            int choice = -1;
            while (choice < 1 || choice > 3) {
                try {
                    choice = sc.nextInt();
                    if (choice < 1 || choice > 3) {
                        System.out.println(BG_RED + "Number should be in range 1 - 3" + RESET);
                    }
                } catch (InputMismatchException e) {
                    System.out.println(BG_RED + "Enter Valid Number!" + RESET);
                    sc.nextLine(); // Clear the invalid input
                }
            }

            switch (choice) {
                case 1:
                    MobileFuel.register();
                    break;
                case 2:
                    MobileFuel.login();
                    break;
                case 3:
                    System.out.println(PURPLE + "Exiting..." + RESET);
                    System.exit(0);
                default:
                    System.out.println(BG_RED + "Invalid choice!" + RESET);
            }
        }
	
    }
}
