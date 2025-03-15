package services;

import models.FuelOrder;
import models.Payment;
import java.security.SecureRandom;
import java.util.*;
import main.Main;
import java.util.regex.*;

public class MobileFuel {
	public static final String RESET = "\u001B[0m"; // Reset color
  public static final String RED = "\u001B[31m"; // Red text
  public static final String GREEN = "\u001B[32m"; // Green text
  public static final String YELLOW = "\u001B[33m"; // Yellow text
	public static final String PURPLE = "\u001B[35m";
	public static final String BG_RED = "\u001B[41m";
	public static final String BLUE = "\u001B[34m";
	public static final String CYAN = "\u001B[36m";
	 public static final String BG_GREEN = "\u001B[42m";
	public static final String BG_YELLOW = "\u001B[43m";
	static MobileFuel user;
	static String location;
    private String name;
    private String userID;
    static String password;
    private List<FuelOrder> orderHistory = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static HashMap<String, MobileFuel> userDatabase = new HashMap<>();
	static int c=3;

    public MobileFuel(String name, String userID, String password) {
        this.name = name;
        this.userID = userID;
        this.password = password;
    }

    public void addOrder(FuelOrder order) {
        orderHistory.add(order);
    }

    public void viewOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println(RED+"No previous orders found!!!!!!!!!!!."+RESET);
            return;
        }
	else{
        for (FuelOrder order : orderHistory) {
            order.displayOrder();
        }
	}
    }
   private static boolean isValidPassword(String password) {
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&*()\\-+=!])[A-Za-z\\d@#$%^&*()\\-+=!]{6,}$";
        return Pattern.matches(pattern, password);
    }
	


    public static void register() {
    System.out.print(YELLOW + "Enter Your Name: " + RESET);
    String name = sc.next();

    String mobileNumber;
    while (true) {
        System.out.print(YELLOW + "Enter Your 10-digit Mobile Number: " + RESET);
        mobileNumber = sc.next();
        
        if (mobileNumber.matches("[6789][0-9]{9}")) {
            break;
        } else {
            System.out.println(BG_RED + "Invalid mobile number! It must be 10 digits long and start with 6, 7, 8, or 9." + RESET);
        }
    }

    while (true) {
        System.out.print(YELLOW + "Enter Your Password: " + RESET);
        password = sc.next();
        if (isValidPassword(password)) {
            break;
        } else {
            System.out.println(BG_RED + "Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 number, and 1 special character and be at least 6 characters long." + RESET);
        }
    }

    String userID = generateID();
    userDatabase.put(userID, new MobileFuel(name, userID, password));
    System.out.println(GREEN + "Registration Successful!!!!!!!" + RESET);
    System.out.println(RED + "Your User ID is: " + userID + RESET);
}




    
    public static void login() {
        System.out.print(BLUE+"Enter your User ID: "+RESET);
        String userID = sc.next();
        if (userDatabase.containsKey(userID)) {
             user = userDatabase.get(userID);
            System.out.print(BLUE+"Enter your Password: "+RESET);
            String password = sc.next();
            if (user.password.equals(password)) {
                System.out.println("Login Successful! Welcome, " + user.name + "!");
                mainMenu(user);
            } else {
                System.out.println(BG_RED+"Incorrect password!!!!!!"+RESET);
		System.out.println(BG_RED+"Please Try Again!!!!!!!!!"+RESET);
            }
        } else {
            System.out.println(BG_YELLOW+" OOOOPPPPSSSSS! User ID not found!"+RESET);
        }
    }

    private static String generateID() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder id = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            id.append(characters.charAt(random.nextInt(characters.length())));
        }
        return id.toString();
    }





    private static void mainMenu(MobileFuel user) {
        while (true) {
            System.out.println(PURPLE+"\n1. Place Order\n2. View Order History\n3. Logout"+RESET);
            System.out.print("Choose an option: ");
	
		int choice = -1;
		while(choice < 1 || choice > 4) {
			try {
				choice = sc.nextInt();
				if(choice < 1 || choice > 3) {
					System.out.println(BG_RED+"Number should be in range 1 - 3"+RESET);
					continue;
				}
				else{
					break;
				}	
				
			}catch(InputMismatchException e) {
				System.out.println(BG_RED+"Enter Valid Number!!!!!!"+RESET);
				sc.nextLine();
				continue;
				
				
			}
		}
		

           
            switch (choice) {
                case 1:
                    placeOrder(user);
                    break;
                case 2:
                    user.viewOrderHistory();
                    break;
                case 3:
                      try {
                    Main.main(null); // Redirect to Main
                } catch (InterruptedException e) {
                    System.out.println("An error occurred while returning to the main menu.");
                    e.printStackTrace();
                }
                return;
                default:
                    System.out.println("Invalid choice!!!!!!");
            }
        }
    }



 static FuelOrder order;


    private static void placeOrder(MobileFuel user) {
        System.out.println(YELLOW+"\n1. Petrol (Rs90/liter)\n2. Diesel (Rs70/liter)\n3. CNG (Rs65/liter)"+RESET);
	System.out.print("Choose an option: ");

        int choice = -1;
		while(choice < 1 || choice > 4) {
			try {
				choice = sc.nextInt();
				if(choice < 1 || choice > 3) {
					System.out.println(BG_RED+"Number should be in range 1 - 3"+RESET);
					continue;
				}
				else{
					break;
				}	
				
			}catch(Exception e) {
				System.out.println("Enter Valid Number!");
				sc.nextLine();
				continue;
				
				
			}
		}

        String fuelType = "";
        double pricePerLiter = 0;
        switch (choice) {
            case 1:
                fuelType = "Petrol";
                pricePerLiter = 90;
                break;
            case 2:
                fuelType = "Diesel";
                pricePerLiter = 70;
                break;
            case 3:
                fuelType = "CNG";
                pricePerLiter = 65;
                break;
            default:
                System.out.println("Invalid choice!!!!!");
                return;
        }

	//while(true){
       // System.out.print(GREEN+"Enter quantity (liters): "+RESET);
       // double quantity = sc.nextDouble();
	//if(quantity>0)
	//}

	
	 double quantity;
	while(true) {
			try {
				System.out.println(GREEN+"Enter quantity (liters): "+RESET);
				 quantity=sc.nextDouble();
				if(quantity < 1) {
					System.out.println(BG_RED+"Quantity Cannot be Zero"+RESET);
					continue;
				}
				else{
					break;
				}	
				
			}catch(Exception e) {
				System.out.println("Enter Valid Number!");
				sc.nextLine();
				continue;
				
				
			}
		}












        sc.nextLine();
        System.out.print(GREEN+"Enter delivery location: "+RESET);
	while (true) {
    try {
        System.out.println("Press 1 for Ameerpet");
        System.out.println("Press 2 for Madhapur");
        System.out.println("Press 3 for Hitech City");
        System.out.println("Press 4 for Jubilee Hills");
        System.out.println("Press 5 for Kukatpally");
        System.out.print("Enter your choice: ");

        int n = sc.nextInt();
        sc.nextLine(); // ✅ Clear the input buffer

        
        switch (n) {
            case 1:
                location = "Ameerpet";
                break;
            case 2:
                location = "Madhapur";
                break;
            case 3:
                location = "Hitech City";
                break;
            case 4:
                location = "Jubilee Hills";
                break;
            case 5:
                location = "Kukatpally";
                break;
            default:
                System.out.println("\u001B[41mInvalid choice! Please enter a number between 1 and 5.\u001B[0m");
                continue;
        }

        System.out.println("\u001B[42mSelected location: " + location + "\u001B[0m");
        break; 

    } catch (Exception e) {
        System.out.println("\u001B[41mInvalid input! Please enter a valid number (1-5).\u001B[0m");
        sc.nextLine(); 
    }
}


	
       
	//if(c!=0) 
	//{
	// FuelOrder
 order = new FuelOrder(fuelType, quantity, location, pricePerLiter);
        
        order.displayOrder();
//}
//else//
//{//
	//order.display2();
//}
        processPayment();
    }




	

    




public static void processPayment() {
        System.out.println("\u001B[36m1. COD\u001B[0m");
        System.out.println("\u001B[36m2. UPI\u001B[0m");
        System.out.print("Choose an option: ");

        int choice = -1;
        while (true) {
            try {
                choice = sc.nextInt();
                if (choice < 1 || choice > 2) {
                    System.out.println("\u001B[41mNumber should be in range 1 - 2\u001B[0m");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Enter a valid number!");
                sc.nextLine(); // Clear input buffer
            }
        }

        while (true) { // Loop to restart payment if OTP attempts fail
            switch (choice) {
                case 1:
                    System.out.println("\u001B[33m\nPayment Method: Cash on Delivery\u001B[0m");
                    Payment payment = new Payment();
                    int generatedOTP = payment.generateOTP();
                    System.out.println("Your OTP is: " + generatedOTP);
                    
                    boolean success = verifyOTP(payment);
                    if (success) {
                        System.out.println("\u001B[42mPayment Successful! Order confirmed.\u001B[0m");
			System.out.println(CYAN+"Your fuel will be delivered soon!!!!!!");
			user.addOrder(order);
                        return;
                    } else {
                        System.out.println(BG_RED + "Maximum OTP attempts reached... Returning to main menu." + RESET);
		mainMenu(user); // Ensure it loops back to the menu
                    }
                    break;

                case 2:
                    System.out.println("ONLINE PAYMENT");
                    System.out.print("Enter UPI ID: ");
                    String upi = sc.next();
                    if (upi.endsWith("@ybl")) {
                        Payment payment1 = new Payment();
                        int generatedOTP1 = payment1.generateOTP();
                        System.out.println("Your OTP is: " + generatedOTP1);
                        
                        boolean successUpi = verifyOTP(payment1);
                        if (successUpi) {
                            System.out.println("\u001B[42mPayment Successful! Order confirmed.\u001B[0m");
			System.out.println(CYAN+"Your fuel will be delivered soon!!!!!!");
				user.addOrder(order);
                            return;
                        } else {
                            System.out.println("Maximum OTP attempts reached...Please Try Again");
				mainMenu(user);
                        }
                    } else {
                        System.out.println("Invalid UPI ID");
                    }
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }











    private static boolean verifyOTP(Payment payment) {
	try{
        for (int attempts = 0; attempts < 3; attempts++) {
            System.out.print("Enter OTP to confirm order: ");
            int enteredOTP = sc.nextInt();
            if (payment.verifyOTP(enteredOTP)) {
                return true;
            } else {
                System.out.println("\u001B[41m[X] Incorrect OTP! Attempts left: " + (2 - attempts) + "\u001B[0m");
            }
        }
        
	}
	catch(InputMismatchException e){
		System.out.println("OTP should be in Numbers");	
	}
	return false;
    }
}