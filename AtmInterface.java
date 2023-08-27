import java.util.Scanner;
import java.util.InputMismatchException;

class BankAccount {
	
	String name;
	String userName;
	String password;
	String accountNo;
	float balance = 100000f;
	int transactions = 0;
	String transactionHistory = "";

	public void register() {
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Your Name - ");
		this.name = sc.nextLine();
		System.out.print("\nEnter Your Username - ");
		this.userName = sc.nextLine();
		System.out.print("\nEnter Your Password - ");
		this.password = sc.nextLine();
		System.out.print("\nEnter Your Account Number - ");
		this.accountNo = sc.nextLine();
		System.out.println("\nRegistration completed..kindly login");
	}
	
	public boolean login() {
		boolean isLogin = false;
		Scanner sc = new Scanner(System.in);
		while ( !isLogin ) {
			System.out.print("\nEnter Your Username - ");
			String Username = sc.nextLine();
			if ( Username.equals(userName) ) {
				while ( !isLogin ) {
					System.out.print("\nEnter Your Password - ");
					String Password = sc.nextLine();
					if ( Password.equals(password) ) {
						System.out.print("\nLogin successful!!");
						isLogin = true;
					}
					else {
						System.out.println("\nIncorrect Password");
					}
				}
			}
			else {
				System.out.println("\nUsername not found");
			}
		}
		return isLogin;
	}
	
	public void withdraw() {
		
		System.out.print("\nEnter amount to withdraw - ");
		Scanner sc = new Scanner(System.in);
		float amount = sc.nextFloat();
		try {
			
			if ( balance >= amount ) {
				transactions++;
				balance -= amount;
				System.out.println("\nWithdraw Successfully");
				String str = amount + " Rs Withdrawed\n";
				transactionHistory = transactionHistory.concat(str);
				
			}
			else {
				System.out.println("\nInsufficient Balance");
			}
			
		}
		catch ( Exception e) {
		}
	}
	
	public void deposit() {
		
		System.out.print("\nEnter amount to deposit - ");
		Scanner sc = new Scanner(System.in);
		float amount = sc.nextFloat();
		
		try {
			if ( amount <= 100000f ) {
				transactions++;
				balance += amount;
				System.out.println("\nSuccessfully Deposited");
				String str = amount + " Rs deposited\n";
				transactionHistory = transactionHistory.concat(str);
			}
			else {
				System.out.println("\nSorry...Limit is 100000.00");
			}
			
		}
		catch ( Exception e) {
		}
	}
	
	public void transfer() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Receipent's Name - ");
		String receipent = sc.nextLine();
		System.out.print("\nEnter amount to transfer - ");
		float amount = sc.nextFloat();
		
		try {
			if ( balance >= amount ) {
				if ( amount <= 50000f ) {
					transactions++;
					balance -= amount;
					System.out.println("\nSuccessfully Transfered to " + receipent);
					String str = amount + " Rs transfered to " + receipent + "\n";
					transactionHistory = transactionHistory.concat(str);
				}
				else {
					System.out.println("\nSorry...Limit is 50000.00");
				}
			}
			else {
				System.out.println("\nInsufficient Balance");
			}
		}
		catch ( Exception e) {
		}
	}
	
	public void checkBalance() {
		System.out.println("\n" + balance + " Rs");
	}
	
	public void transHistory() {
		if ( transactions == 0 ) {
			System.out.println("\nEmpty");
		}
		else {
			System.out.println("\n" + transactionHistory);
		}
	}
}
public class AtmInterface {

    public static int takeIntegerInput(int limit) {
        int input = 0;
        boolean flag = false;

        while (!flag) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;

                if (input > limit || input < 1) {
                    System.out.println("Choose a valid number");
                    flag = false; // Set flag to false to continue looping
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return input;
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        while (true) {
            System.out.println("Welcome to the ATM Interface");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            // Add more menu options here

            int choice = takeIntegerInput(3); // Assuming you have three menu options

            switch (choice) {
                case 1:
                    account.register();
                    break;
                case 2:
                    boolean loggedIn = account.login();
                    if (loggedIn) {
                        boolean loggedInMenu = true;
                        while (loggedInMenu) {
                            System.out.println("Logged in as " + account.userName);
                            System.out.println("1. Withdraw");
                            System.out.println("2. Deposit");
                            System.out.println("3. Transfer");
                            System.out.println("4. Check Balance");
                            System.out.println("5. Transaction History");
                            System.out.println("6. Logout");

                            int accountChoice = takeIntegerInput(6);

                            switch (accountChoice) {
                                case 1:
                                    account.withdraw();
                                    break;
                                case 2:
                                    account.deposit();
                                    break;
                                case 3:
                                    account.transfer();
                                    break;
                                case 4:
                                    account.checkBalance();
                                    break;
                                case 5:
                                    account.transHistory();
                                    break;
                                case 6:
                                    loggedInMenu = false;
                                    System.out.println("Logged out.");
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please choose a valid option.");
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exiting the ATM Interface. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }
}





