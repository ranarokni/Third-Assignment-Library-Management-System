import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.io.Console;
import java.security.MessageDigest;

import java.io.IOException;
public class Main {
    /*
    * make a functional library app using oop
    * run the main program in Main.java and code the oop part in other classes
    * don't forget to add at least 1 librarian to the library to make it functionable.
    * *  *** don't limit yourself to our template ***
     */

    public static void main(String[] args) {
        Library library = new Library();

        int operation = runMenu();

        while (true) {
            switch (operation) {
                case 1:
                    userLogin(library);
                    operation = runMenu();
                    break;
                case 2:
                    librarianLogin(library);
                    operation = runMenu();
                    break;
                case 3:
                    userRegistration(library);
                    operation = runMenu();
                    break;
                case 4:
                    librarianRegistration(library);
                    operation = runMenu();
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }

    }

    public static int runMenu(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to AP's Library! What do you want to do? Choose from the list bellow");
        System.out.println("1. Login as a member");
        System.out.println("2. Login as a librarian");
        System.out.println("3. New user, register");
        System.out.println("4. New librarian, register");
        System.out.println("5. Exit");


        //input validation check
        int inputNumber = 0;
        boolean invalidInmput = true;
        while (invalidInmput) {
            while (!scanner.hasNextInt()) {
                System.err.println("Please enter valid number");
                scanner.next();
            }
            inputNumber = scanner.nextInt();
            if(inputNumber > 5 || inputNumber < 1){
                System.err.println("You can only choose 4 option");
            }
            else {
                invalidInmput = false;
                break;
            }
        }

        return inputNumber;
    }

    public static String doHashing(String password){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());

            byte[] result = messageDigest.digest();
            StringBuilder sb = new StringBuilder();

            for(byte b : result){
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return "";
    }


    public static void userLogin(Library library){
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("-------MEMBER LOGIN-------");
        System.out.println("Press q to exit\n");
        System.out.print("Enter your username: ");

        String userName = scanner.next();
        if(userName.equals("q")){
            return;
        }
        while(!library.doesUserNameExist(userName)){
            System.err.println("Username does not found, try again");
            userName = scanner.next();
            if(userName.equals("q")){
                return;
            }
        }

        System.out.print("Enter your password: ");
        String pass = scanner.next();
//        char[] pass = console.readPassword();
        String password = doHashing(pass);
        if(password.equals("q")){
            return;
        }
        int i = 0;
        while (i < 3 && library.isUserPasswordValid(userName, password)){
            System.err.println("Invalid password, try again");
            i++;
//            pass = console.readPassword();
            pass = scanner.next();
            password = doHashing(pass);
            if(password.equals("q")){
                return;
            }
        }

        if(i == 3){
            System.err.println("You entered wrong password for 3 times, the program will be closed");
            System.exit(0);
        }

        System.out.println("You entered successfully!");

        User user = new User(userName, password);

        userMenu(library, user);
        return;

    }

    public static void userMenu(Library library, User user){
        Scanner scanner = new Scanner(System.in);

        while (!scanner.next().equals("q")) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("------MEMBER MENU------");
            System.out.println("Press q to exit\n");
            System.out.println("What do you want to do? Choose from the list below");
            System.out.println("1. Rent book");
            System.out.println("2. Return book");
            System.out.println("3. See history");

            int inputNumber = 0;
            boolean invalidInmput = true;
            while (invalidInmput) {
                while (!scanner.hasNextInt()) {
                    System.err.println("Please enter valid number");
                    scanner.next();
                }
                inputNumber = scanner.nextInt();
                if (inputNumber > 3 || inputNumber < 1) {
                    System.err.println("You can only choose 3 option");
                } else {
                    invalidInmput = false;
                    break;
                }
            }

            String bookName;
            String bookAuther;
            String bookPublisher;
            int bookYearOfPublish;

            System.out.println("Please enter the book's name, auther, publisher and the year of publish in order: ");
            bookName = scanner.next();
            bookAuther = scanner.next();
            bookPublisher = scanner.next();
            bookYearOfPublish = scanner.nextInt();
            Book book = new Book(bookName, bookPublisher, bookAuther, bookYearOfPublish);

            switch (inputNumber) {
                case 1:
                    if (user.rentBook(book, library)) {
                        System.out.println("You rented the book successfully!");
                    }
                    else{
                        System.err.println("You have already rented this book!");
                    }
                    break;
                case 2:
                    if(user.returnBook(book, library)){
                        System.out.println("Return operation succeed, hope to see you again");
                    }
                    else{
                        System.err.println("You have hadn't rent this book before!");
                    }
                    break;
                case 3:
                    System.out.println(user.history);
                    break;
            }
        }
        return;
    }

    public static void librarianLogin(Library library){
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("-------LIBRARIAN LOGIN-------");
        System.out.println("Press q to exit\n");
        System.out.print("Enter your username: ");

        String userName = scanner.next();
        if(userName.equals("q")){
            return;
        }
        while(!library.doesLibrarianExist(userName)){
            System.err.println("Username does not found, try again");
            userName = scanner.next();
            if(userName.equals("q")){
                return;
            }
        }

        System.out.print("Enter your password: ");
        String pass = scanner.next();
//        char[] pass = console.readPassword();
        String password = doHashing(pass);
        if(password.equals("q")){
            return;
        }

        int i = 0;
        while (i < 3 && library.isLibrarianPasswordValid(userName, password)){
            System.err.println("Invalid password, try again");
            i++;
//            pass = console.readPassword();
            pass = scanner.next();
            password = doHashing(pass);
            if(password.equals("q")){
                return;
            }
        }

        if(i == 3){
            System.err.println("You entered wrong password for 3 times, the program will be closed");
            System.exit(0);
        }

        System.out.println("You entered successfully!");

        Librarian librarian = new Librarian(userName, password);
        librarianMenu(librarian, library);
        return;

    }

    public static void librarianMenu(Librarian librarian, Library library){
        Scanner scanner = new Scanner(System.in);

        while (!scanner.next().equals("q")) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("------LIBRARIAN MENU------");
            System.out.println("Press q to exit\n");
            System.out.println("What do you want to do? Choose from the list bellow");
            System.out.println("1. Add book");
            System.out.println("2. Remove book");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book");

            System.out.println("5. Add user");
            System.out.println("6. Remove user");
            System.out.println("7. Search user");
            System.out.println("8. Update user");

            System.out.println("9. Add librarian");
            System.out.println("10. Remove librarian");
            System.out.println("11. Search librarian");
            System.out.println("12. Update librarian");


            int inputNumber = 0;
            boolean invalidInmput = true;
            while (invalidInmput) {
                while (!scanner.hasNextInt()) {
                    System.err.println("Please enter valid number");
                    scanner.next();
                }
                inputNumber = scanner.nextInt();
                if (inputNumber > 12 || inputNumber < 1) {
                    System.err.println("You can only choose 3 option");
                } else {
                    invalidInmput = false;
                    break;
                }
            }

            if(inputNumber <= 4 && inputNumber >= 1) {
                String bookName;
                String bookAuther;
                String bookPublisher;
                int bookYearOfPublish;

                System.out.println("Please enter the book's name, auther, publisher and the year of publish in order: ");
                bookName = scanner.next();
                bookAuther = scanner.next();
                bookPublisher = scanner.next();
                bookYearOfPublish = scanner.nextInt();
                Book book = new Book(bookName, bookPublisher, bookAuther, bookYearOfPublish);
                switch (inputNumber){
                    case 1:
                        if(librarian.addBook(book, library)){
                            System.out.println("The Book added successfully");
                        }
                        else{
                            System.err.println("The book already exists");
                        }
                        break;
                    case 2:
                        if(librarian.removeBook(book, library))
                            System.out.println("The book removed successfully!");
                        else
                            System.err.println("The book does not exist");
                        break;
                    case 3:
                        System.out.println(library.searchBook(book.getName()));
                        break;
                    case 4:
                        System.out.println("Please enter those same information for new book: ");
                        bookName = scanner.next();
                        bookAuther = scanner.next();
                        bookPublisher = scanner.next();
                        bookYearOfPublish = scanner.nextInt();
                        Book newBook = new Book(bookName,bookPublisher,bookAuther,bookYearOfPublish);
                        if(librarian.updateBook(book, newBook, library))
                            System.out.println("The book updated successfully!");
                        else
                            System.err.println("The book does not exists");
                        break;
                }
            }

            if(inputNumber <= 8 && inputNumber >= 5) {
                String name;
                String password;
                System.out.println("Please enter the user's name and password in order: ");
                name = scanner.next();
                password = scanner.next();
                User user = new User(name, password);
                switch (inputNumber){
                    case 5:
                        if(librarian.addUser(user, library)){
                            System.out.println("The user added successfully");
                        }
                        else{
                            System.err.println("The user already exists");
                        }
                        break;
                    case 6:
                        if(librarian.removeUser(user, library)) {
                            System.out.println("The user removed successfully!");
                        }
                        else {
                            System.err.println("The user does not exist");
                        }
                        break;
                    case 7:
                        System.out.println(library.searchUser(user));
                        break;
                    case 8:
                        System.out.println("Please enter those same information for new user: ");
                        name = scanner.next();
                        password = scanner.next();
                        User newUser = new User(name, password);
                        if(librarian.updateUser(newUser, user, library)) {
                            System.out.println("The user updated successfully!");
                        }
                        else {
                            System.err.println("The user does not exists");
                        }
                        break;
                }
            }

            if(inputNumber <= 12 && inputNumber >= 9) {
                String name;
                String password;
                System.out.println("Please enter the librarian's name and password in order: ");
                name = scanner.next();
                password = scanner.next();
                Librarian librarian1 = new Librarian(name, password);
                switch (inputNumber){
                    case 5:
                        if(librarian.addLibrarian(librarian1, library)){
                            System.out.println("The librarian added successfully");
                        }
                        else{
                            System.err.println("The librarian already exists");
                        }
                        break;
                    case 6:
                        if(librarian.removeLibrarian(librarian1, library)) {
                            System.out.println("The librarian removed successfully!");
                        }
                        else {
                            System.err.println("The librarian does not exist");
                        }
                        break;
                    case 7:
                        System.out.println(library.searchLibrarian(librarian1.getUserName()));
                        break;
                    case 8:
                        System.out.println("Please enter those same information for new librarian: ");
                        name = scanner.next();
                        password = scanner.next();
                        Librarian newLibrarian = new Librarian(name, password);
                        if(librarian.updateLibrarian(newLibrarian, librarian1, library)) {
                            System.out.println("The librarian updated successfully!");
                        }
                        else {
                            System.err.println("The librarian does not exists");
                        }
                        break;
                }
            }
        }
        return;
    }
    public static void userRegistration(Library library){
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("-------MEMBER REGISTRATION-------");
        System.out.print("Enter your username: ");

        String userName = scanner.next();
        System.out.print("XXXXXXXXXXX");

        while(library.doesUserNameExist(userName)){
            System.err.println("Username already token, try again");
            userName = scanner.next();
        }


        System.out.print("Enter your password: ");
        String pass = scanner.next();
//        char[] pass = console.readPassword();
        String password = doHashing(String.valueOf(pass));

        System.out.println("Repeat your password: ");
//        pass = console.readPassword();
        pass = scanner.next();
        String repeatedPassword = doHashing(String.valueOf(pass));
        while (!repeatedPassword.equals( password)){
            System.err.println("Two passwords does not match, repeat your password");
//            pass = console.readPassword();
            pass = scanner.next();
            repeatedPassword = doHashing(String.valueOf(pass));
        }

        System.out.println("You registered successfully!");

        User user = new User(userName, repeatedPassword);
        library.addUser(user);
        userMenu(library, user);
        return;

    }
    public static void librarianRegistration(Library library){
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("-------Librarian REGISTRATION-------");
        System.out.print("Enter your username: ");

        String userName = scanner.next();

        while(library.doesLibrarianExist(userName)){
            System.err.println("Username already token, try again");
            userName = scanner.next();
        }

        System.out.print("Enter your password: ");
//        char[] pass = console.readPassword("Enter your password: ");
        String pass = scanner.next();
        String password = doHashing(pass);

        System.out.println("Repeat your password: ");
//        pass = console.readPassword();
        pass = scanner.next();
        String repeatedPassword = doHashing(pass);
        while (!repeatedPassword.equals(password)){
            System.err.println("Two passwords does not match, repeat your password");
//            pass = console.readPassword();
            pass = scanner.next();
            repeatedPassword = doHashing(pass);
        }

        System.out.println("You registered successfully!");

        Librarian librarian = new Librarian(userName, repeatedPassword);
        library.addLibrarian(librarian);
        librarianMenu(librarian, library);
        return;
    }
}
