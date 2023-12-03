import java.util.*;

class Pet {
    private String type;
    private String breed;
    private double price;

    public Pet(String type, String breed, double price) {
        this.type = type;
        this.breed = breed;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getBreed() {
        return breed;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return type + " (" + breed + ") - Price: $" + price;
    }
}

class Customer {
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private List<Pet> purchasedPets;
    private String address;

    public Customer(String name, String phoneNumber, String email, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.purchasedPets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void addPurchasedPet(Pet pet) {
        purchasedPets.add(pet);
    }

    public List<Pet> getPurchasedPets() {
        return purchasedPets;
    }
}

public class OnlinePetShop {
    private static List<Pet> pets = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static Customer currentCustomer = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializePets();
        showWelcomeMessage();

        boolean loggedIn = false;

        while (!loggedIn) {
            int choice = showMainMenu();

            switch (choice) {
                case 1:
                    registerAccount();
                    break;
                case 2:
                    currentCustomer = login();
                    if (currentCustomer != null) {
                        loggedIn = true;
                    }
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        while (true) {
            showPetCategories();
            choosePet();
            System.out.println("Do you want to continue shopping? (1. Yes / 2. No)");
            int continueShoppingChoice = scanner.nextInt();
            if (continueShoppingChoice != 1) {
                break;
            }
        }

        System.out.println("Thank you for visiting. Goodbye!");
    }

    private static void initializePets() {
        pets.add(new Pet("Dog", "Labrador", 500.0));
        pets.add(new Pet("Cat", "Siamese", 300.0));
        pets.add(new Pet("Bird", "Parrot", 150.0));
        // Add more pets as needed
    }

    private static void showWelcomeMessage() {
        System.out.println("Welcome to the Online Pet Shop!");
    }

    private static int showMainMenu() {
        System.out.println("1. Register an account");
        System.out.println("2. Log in");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private static void registerAccount() {
        System.out.print("Enter your name: ");
        scanner.nextLine(); // Consume the newline character
        String name = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.next();
        System.out.print("Enter your email: ");
        String email = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        Customer newCustomer = new Customer(name, phoneNumber, email, password);
        customers.add(newCustomer);
        System.out.println("Account registered successfully!");
    }

    private static Customer login() {
        System.out.print("Enter your name: ");
        scanner.nextLine(); // Consume the newline character
        String name = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        for (Customer customer : customers) {
            if (customer.getName().equals(name) && customer.getPassword().equals(password)) {
                System.out.println("Login successful!");
                return customer;
            }
        }

        System.out.println("Invalid credentials. Please try again.");
        return null;
    }

    private static void showPetCategories() {
        System.out.println("Available pet categories:");

        for (int i = 0; i < pets.size(); i++) {
            System.out.println((i + 1) + ". " + pets.get(i).getType());
        }
    }

    private static void choosePet() {
        System.out.print("Choose a pet category (enter the number): ");
        int categoryChoice = scanner.nextInt();

        if (categoryChoice < 1 || categoryChoice > pets.size()) {
            System.out.println("Invalid choice. Exiting.");
            System.exit(0);
        }

        Pet selectedPet = pets.get(categoryChoice - 1);
        System.out.println("Selected pet: " + selectedPet);

        // Customer decides to buy or go back
        System.out.print("Do you want to buy this pet? (1. Yes / 2. Go back): ");
        int buyChoice = scanner.nextInt();

        if (buyChoice == 1) {
            purchasePet(selectedPet);
        }
    }

    private static void purchasePet(Pet pet) {
        System.out.print("Enter your address: ");
        scanner.nextLine(); // Consume the newline character
        String address = scanner.nextLine();
        currentCustomer.setAddress(address);

        // Payment options
        System.out.println("Select a payment option:");
        System.out.println("1. Cash");
        System.out.println("2. Card");
        System.out.println("3. Bkash");
        System.out.println("4. Nagad");

        int paymentChoice = scanner.nextInt();
        switch (paymentChoice) {
            case 1:
                System.out.println("Payment successful! Thank you for your purchase.");
                break;
            case 2:
                processCardPayment();
                break;
            case 3:
            case 4:
                processMobilePayment(paymentChoice);
                break;
            default:
                System.out.println("Invalid choice. Exiting.");
                System.exit(0);
        }

        System.out.println("Pet details:\n" + pet);
        System.out.println("Deliver to: " + currentCustomer.getAddress());
        System.out.println("Thank you for shopping with us, " + currentCustomer.getName() + "!");
        currentCustomer.addPurchasedPet(pet);
    }

    private static void processCardPayment() {
        System.out.println("Enter card details for verification (for demonstration purposes):");
        System.out.print("Card number: ");
        String cardNumber = scanner.next();
        System.out.print("Expiration date: ");
        String expirationDate = scanner.next();
        System.out.print("CVV: ");
        String cvv = scanner.next();

        // For demonstration purposes, we're not performing actual card verification.
        System.out.println("Card details verified. Payment successful!");
    }

    private static void processMobilePayment(int paymentChoice) {
        System.out.print("Enter your mobile number for verification (for demonstration purposes): ");
        String mobileNumber = scanner.next();

        // For demonstration purposes, we're not sending an actual verification code.
        System.out.println("Verification code sent to " + mobileNumber);

        System.out.print("Enter the verification code: ");
        String verificationCode = scanner.next();

        System.out.println("Verification successful! Payment completed.");
    }
}