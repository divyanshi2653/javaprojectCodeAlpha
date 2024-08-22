package com.company;

import java.util.*;

public class ELibrarySystem {
    private static List<User> users = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();
    private static User loggedInUser = null;

    public static void main(String[] args) {
        // Add some default categories and books
        Category fiction = new Category("Fiction");
        Category science = new Category("Science");
        Category history = new Category("History");

        books.add(new Book("The Great Gatsby", fiction));
        books.add(new Book("Brief History of Time", science));
        books.add(new Book("Sapiens", history));

        // Add default users
        users.add(new User("admin", "password123", true));
        users.add(new User("john", "john123", false));

        Scanner scanner = new Scanner(System.in);

        // Main loop
        while (true) {
            if (loggedInUser == null) {
                System.out.println("Welcome to E-Library System!");
                System.out.println("1. Login");
                System.out.println("2. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    login(scanner);
                } else if (choice == 2) {
                    System.out.println("Thank you for using the E-Library System!");
                    break;
                }
            } else {
                displayMenu(scanner);
            }
        }
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Login successful! Welcome " + username);
                return;
            }
        }
        System.out.println("Invalid credentials, try again.");
    }

    private static void displayMenu(Scanner scanner) {
        System.out.println("1. Borrow Book");
        System.out.println("2. Return Book");
        System.out.println("3. View Available Books");
        System.out.println("4. Logout");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                borrowBook(scanner);
                break;
            case 2:
                returnBook(scanner);
                break;
            case 3:
                viewAvailableBooks();
                break;
            case 4:
                loggedInUser = null;
                break;
        }
    }

    private static void borrowBook(Scanner scanner) {
        System.out.println("Enter the book name to borrow: ");
        String bookName = scanner.nextLine();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(bookName) && !book.isBorrowed()) {
                book.setBorrowed(true);
                loggedInUser.getBorrowedBooks().add(book);
                System.out.println("You have successfully borrowed: " + bookName);
                return;
            }
        }
        System.out.println("Book not available or already borrowed.");
    }

    private static void returnBook(Scanner scanner) {
        System.out.println("Enter the book name to return: ");
        String bookName = scanner.nextLine();

        for (Book book : loggedInUser.getBorrowedBooks()) {
            if (book.getTitle().equalsIgnoreCase(bookName)) {
                book.setBorrowed(false);
                loggedInUser.getBorrowedBooks().remove(book);
                System.out.println("You have successfully returned: " + bookName);
                return;
            }
        }
        System.out.println("You haven't borrowed this book.");
    }

    private static void viewAvailableBooks() {
        System.out.println("Available Books: ");
        for (Book book : books) {
            if (!book.isBorrowed()) {
                System.out.println(book.getTitle() + " (Category: " + book.getCategory().getName() + ")");
            }
        }
    }
}
