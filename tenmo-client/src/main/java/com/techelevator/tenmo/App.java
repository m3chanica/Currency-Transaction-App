package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.*;

import java.math.BigDecimal;

import java.util.Scanner;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";
    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private final AccountService accountService = new AccountService(API_BASE_URL);
    private AuthenticatedUser currentUser;
    private final UserService userService = new UserService(API_BASE_URL);
    private final TransferService transferService = new TransferService(API_BASE_URL);

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {
        BigDecimal account = accountService.getBalance(currentUser);
        System.out.println("```");
        System.out.println("Your current balance is: " + account);
        System.out.println("```");
    }



	private void viewPendingRequests() {
        System.out.println("```");
        System.out.println("-------------------------------------------");
        System.out.println("Pending Transfers");
        System.out.println("ID          To                       Amount");
        System.out.println("-------------------------------------------");
        System.out.println("XX"+"    "+"From: "+"XXXXXXXX"+"   "+"$XXXXX.XX");
        System.out.println("-------------------------------------------");
        System.out.println();
        System.out.println("Please enter transfer ID to approve/reject (0 to cancel): ");
        System.out.println("```");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        if(userInput == "0"){
            mainMenu();
        }else{
            mainMenu();
        }
	}

	private void sendBucks() {
        User[] users = userService.listUsers();
        if (users != null) {
            consoleService.printUsers(users);
        } else {
            consoleService.printErrorMessage();
        }
        System.out.println();
        System.out.println("Enter ID of user you are sending to (0 to cancel):");
        System.out.println("Enter amount:");
        System.out.println("```");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        if(userInput == "0"){
            mainMenu();
        }else{
            mainMenu();
        }
	}

	private void requestBucks() {
        User[] users = userService.listUsers();
        if (users != null) {
            consoleService.printUsers(users);
        } else {
            consoleService.printErrorMessage();
        }
        System.out.println();
        System.out.println("Enter ID of user you are requesting from (0 to cancel):");
        System.out.println("Enter amount:");
        System.out.println("```");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        if(userInput == "0"){
            mainMenu();
        }else{
            mainMenu();
        }
	}

//TODO THE INFO BEING PROVIDED IS ABSOLUTE GARBAGE AND NEEDS PROPER INFO YOU DUMB CRAB EATING IDIOT
    private void viewTransferHistory() {


        Transfer[] transfers = transferService.listTransfer();
        if (transfers != null) {
            consoleService.printTransfers(transfers, accountService);
        } else {
            consoleService.printErrorMessage();
        }
//        System.out.println("```");
//        System.out.println("-------------------------------------------");
//        System.out.println("Transfers");
//        System.out.println("ID          From/To                  Amount");
//        System.out.println("-------------------------------------------");
//        System.out.println("id"+"    "+"transfercode: "+"nameofother"+"   "+"$amount");
//        System.out.println("-------------------------------------------");
//        System.out.println();
//        System.out.println("Please enter transfer ID to view details (0 to cancel):");
//        System.out.println("```");
//
//        Scanner scanner = new Scanner(System.in);
//        String userInput = scanner.nextLine();
//        if(userInput == "0"){
//            mainMenu();
//        }else{
//            mainMenu();
//        }

    }

    //TODO HOLY SHIT THIS IS TOTALLY EFFED PLEASE MAKE IT SO WHEN THE USER SPECS AN ID IT RETURNS THE CORRECT SHIT
    // PROMPT USER FOR THE DAMN ID
    private void transferDetails(int transferId) {
        Transfer transfers = transferService.getTransferDetails(transferId);
            if (transferId > 0) {
                transfers = transferService.getTransferDetails(transferId);
                } else {
                    consoleService.printErrorMessage();
        }
    }



//        System.out.println("```");
//        System.out.println("-------------------------------------------");
//        System.out.println("Transfer Details");
//        System.out.println("-------------------------------------------");
//        System.out.println("ID:     "+"XX");
//        System.out.println("From:   "+"XXXXXX");
//        System.out.println("To:     "+"XXXXXX");
//        System.out.println("Status: "+"XXXXX");
//        System.out.println("Amount: "+"$"+"XXXX.XX");
//        System.out.println("```");
//        System.out.println("Enter any key to continue");
//
//        Scanner scanner = new Scanner(System.in);
//        String userInput = scanner.nextLine();
//        if(userInput == "0"){
//            mainMenu();
//        }else{
//            mainMenu();
//        }
//    }


}



