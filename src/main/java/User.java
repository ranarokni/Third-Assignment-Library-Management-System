import java.util.ArrayList;

public class User{
    //User should have a list of books
    //User should have a username and a password

    private String userName;
    private String password;

    ArrayList<Book> history = new ArrayList<Book>();

    public User(String userName, String password, ArrayList<Book> history) {
        this.userName = userName;
        this.password = password;
        this.history = history;
    }

    public User(String userName, String password){
        Book book = new Book("s","s","s",0);
        this.userName = userName;
        this.password = password;
        this.history.add(book);
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isInHistory(Book book){
        if(!this.history.contains(book)){
            return true;
        }
        else {
            return false;
        }
    }

    public ArrayList<Book> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Book> history) {
        this.history = history;
    }

    public boolean rentBook(Book book, Library library){
        if(this.isInHistory(book) && library.doesBookExist(book)){
            library.decreaseBook(book);
            this.history.add(book);
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book, Library library){
        if(this.isInHistory(book)){
            library.increaseBook(book);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", history=" + history +
                '}';
    }

}
