public class Librarian{
    /*
        * The librarian should have a username and a password
        * The librarian should be able to search users, librarians and books
        * The librarian should be able to add\remove\\update user add\\remove\\update_
        _ librarian and add\remove\\update book
         */

    private String userName;
    private String password;

    public Librarian(String userName, String password) {
        this.userName = userName;
        this.password = password;
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

    public boolean addBook(Book book, Library library){
        if(!library.doesBookExist(book)) {
            library.addBook(book);
            return true;
        }
        return false;
    }

    public boolean removeBook(Book book, Library library){
        if(library.doesBookExist(book)){
            library.removeBook(book);
            return true;
        }
        return false;
    }

    public boolean updateBook(Book previusBook, Book newBook, Library library){
        if(library.doesBookExist(previusBook)){
            library.updateBook(previusBook, newBook);
            return true;
        }
        return false;
    }

    public boolean increaseBook(Book book, Library library){
        if(library.doesBookExist(book)){
            library.increaseBook(book);
            return true;
        }
        return false;
    }

    public boolean decreaseBook(Book book, Library library){
        if(library.doesBookExist(book)){
            library.decreaseBook(book);
            return true;
        }
        return false;
    }



    public boolean addUser(User user, Library library){
        if(!library.doesUserNameExist(user.getUserName())){
            library.addUser(user);
            return true;
        }
        return false;
    }

    public boolean removeUser(User user, Library library){
        if(!library.doesUserNameExist(user.getUserName())){
            library.removeUser(user);
            return true;
        }
        return false;
    }

    public boolean updateUser(User newUser, User previousUser, Library library){
        if(!library.doesUserNameExist(previousUser.getUserName())){
            library.updateUser(newUser,previousUser);
            return true;
        }
        return false;
    }

    public boolean addLibrarian(Librarian librarian, Library library){
        if(!library.doesLibrarianExist(librarian.getUserName())){
            library.addLibrarian(librarian);
            return true;
        }
        return false;
    }

    public boolean removeLibrarian(Librarian librarian, Library library){
        if(library.doesLibrarianExist(librarian.getUserName())){
            library.removeLibrarian(librarian);
            return true;
        }
        return false;
    }

    public boolean updateLibrarian(Librarian newlibrarian, Librarian previusLibrarian, Library library){
        if(library.doesLibrarianExist(previusLibrarian.getUserName())){
            library.updateLibrarian(newlibrarian, previusLibrarian);
            return true;
        }
        return false;
    }

}
