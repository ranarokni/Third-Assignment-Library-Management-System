import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Library {
    /*
    * The library should have a list of books.
    * The library should have a map of books ISBNs which is linked to the amount of book
    -> (for example: harry potter -> 4 means there are currently 4 harry potter books)
    * The library should have a list of users and a list of librarians.
     */

    //book related functions

    private HashMap<Book, Integer> storage = new HashMap<Book, Integer>();
    private HashMap<String, Integer> booksNames = new HashMap<String, Integer>();
//    private HashMap<Book, String> borrowedBooks = new HashMap<Book, String>();
    private ArrayList<User> members = new ArrayList<User>();
    private ArrayList<Librarian> librarians = new ArrayList<Librarian>();

    public Library(){
        Book book = new Book("s","s","s",0);
        User user = new User("r","r");
        Librarian librarian = new Librarian("r", "r");
        this.storage.put(book, 0);
        this.booksNames.put("r", 0);
        this.members.add(user);
        this.librarians.add(librarian);

    };

    public boolean doesBookExist(Book book){
        if(storage.isEmpty())
            return false;
        if(storage.containsKey(book) && storage.get(book) != 0){
            return true;
        }
        return false;
    }

    public void addBook(Book book){
        storage.put(book, 1);
        booksNames.put(book.getName(), 1);
    }

    public void removeBook(Book book){
        storage.remove(book);
    }

    public String searchBook(String bookName){
        String answer = "";
        if(booksNames.containsKey(bookName)){
            for(Map.Entry<Book, Integer> set: storage.entrySet()){
                answer += set.getKey().toString() + "-> " + set.getValue().toString();
                answer += '\n';
            }
            return answer;
        }

        else
            return "The book is not available";
    }

    public void updateBook(Book previusBook, Book newBook){
        storage.put(newBook, storage.get(previusBook));
        storage.remove(previusBook);
    }

    public void increaseBook(Book book){
        storage.put(book, storage.get(book) + 1);
      }

    public void decreaseBook(Book book){
        storage.put(book, storage.get(book) - 1);
    }

    //user related functions

    public boolean doesUserNameExist(String user){
        if(members.isEmpty())
            return false;
        for(int i = 0; i < members.size(); i++){
            if(members.get(i).getUserName().equals(user))
                return true;
        }
        return false;
    }

    public void addUser(User user){
        members.add(user);
    }

    public void removeUser(User user){
        members.remove(user);
    }

    public String searchUser(User user) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getUserName().equals(user.getUserName())) {
                return members.get(i).toString();
            }
        }
        return "The user doesn't exist";
    }

    public void updateUser(User newUser, User previusUser){
        newUser.setHistory(previusUser.getHistory());
        members.add(newUser);
        members.remove(previusUser);
    }

    public boolean isUserPasswordValid(String userName, String password){
        for(int i = 0; i < members.size(); i++){
            if(members.get(i).getUserName().equals(userName)){
                if(members.get(i).getPassword().equals(password)){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
    }

    //librarian related functions

    public void addLibrarian(Librarian librarian){
        librarians.add(librarian);
    }

    public void removeLibrarian(Librarian librarian){
        librarians.remove(librarian);
    }

    public String searchLibrarian(String userName){
        for (int i = 0; i < librarians.size(); i++){
            if(librarians.get(i).getUserName().equals(userName)){
                return userName;
            }
        }
        return "The librarian doesn't exist";
    }

    public void updateLibrarian(Librarian newlibrarian, Librarian previosLibrarian){
        librarians.add(newlibrarian);
        librarians.remove(previosLibrarian);
    }

    public boolean doesLibrarianExist(String userName){
        if(librarians.isEmpty()){
            return false;
        }
        for(int i = 0; i < librarians.size(); i++){
            if(librarians.get(i).getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    public boolean isLibrarianPasswordValid(String userName, String password){
        for(int i = 0; i < librarians.size(); i++){
            if(librarians.get(i).getUserName().equals(userName)){
                if(librarians.get(i).getPassword().equals(password)){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
    }


}
