package models;

public class FuelOrder {
    private String fuelType;
    private double quantity;
    private String location;
    private double pricePerLiter;
    private double totalPrice;

    public FuelOrder(String fuelType, double quantity, String location, double pricePerLiter) {
        this.fuelType = fuelType;
        this.quantity = quantity;
        this.location = location;
        this.pricePerLiter = pricePerLiter;
        this.totalPrice = (quantity * pricePerLiter)+50;
    }

    public void displayOrder() {
        System.out.println("\n===== Order Summary =====");
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Quantity: " + quantity + " liters");
        System.out.println("Location: " + location);
        System.out.println("Price per Liter: Rs" + pricePerLiter);
	System.out.println("additional charges : Rs"+"50");
        System.out.println("Total Price: Rs" + totalPrice);
        
    }
	public void display2()
		{
			System.out.println("No Orders");
		}
}