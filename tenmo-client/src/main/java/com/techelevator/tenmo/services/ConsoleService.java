package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.AccountService;


import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleService {

    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }

    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println();
    }

    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a decimal number.");
            }
        }
    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }




    public void printUsers(User[] users) {
        System.out.println("```");
        System.out.println("-------------------------------------------");
        System.out.println("Users");
        System.out.println("ID              Name");
        System.out.println("-------------------------------------------");
        for (User user : users) {
        System.out.println(user.getUserId() + ":           " + user.getUsername());}
        System.out.println("-------------------------------------------");

	}
//TODO THIS HORSESHIT IS WRONG AND NEEDS FIXED CUZ ITS SOOO DUMB AND JUST WRONG LOOK AT THE README
    public void printTransfers(Transfer[] transfers, AccountService username) {
        System.out.println("```");
        System.out.println("-------------------------------------------");
        System.out.println("Transfers");
        System.out.println("ID          From/To                  Amount");
        System.out.println("-------------------------------------------");
        for (Transfer transfer : transfers) {
            String fromUsername = username.getUsernameByAccountId(transfer.getAccountFrom());
            System.out.println(transfer.getTransferId()+"        "+transfer.getTransferTypeId()+": "+ fromUsername + "               "+"$"+transfer.getAmount());
        }
        System.out.println("-------------------------------------------");
    }

//TODO THISSSSS HORSESHIT IS FORMATTED A LOT BETTER BUT IT IS JUST NOT IMPLEMENTED YOU DUMB APE
    public void printTransferDetails(Transfer transfer) {
        System.out.println("```");
        System.out.println("-------------------------------------------");
        System.out.println("Transfer Details");
        System.out.println("-------------------------------------------");
        if (transfer == null) {
            System.out.println("No transfer to print");
        } else {
            System.out.println("Id: " + transfer.getTransferId());
            System.out.println("Status: " + transfer.getTransferStatusId());
            System.out.println("Account From: " + transfer.getAccountFrom());
            System.out.println("Account to: " + transfer.getAccountTo());
            System.out.println("Amount: " + transfer.getAmount());
        }
    }
}