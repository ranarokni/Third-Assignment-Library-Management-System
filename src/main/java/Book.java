import java.util.Date;
import java.util.Objects;

public class Book {
    //Book should contain name,author,year of publish and ISBN

    private String name;
    private String publisher;
    private String auther;
    private int yearOfPublish;

    public Book(String name, String publisher, String auther, int yearOfPublish) {
        this.name = name;
        this.publisher = publisher;
        this.auther = auther;
        this.yearOfPublish = yearOfPublish;
    }
//    private String ISBN;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public int getYearOfPublish() {
        return yearOfPublish;
    }

    public void setYearOfPublish(int yearOfPublish) {
        this.yearOfPublish = yearOfPublish;
    }

//    public String getISBN() {
//        return ISBN;
//    }
//
//    public void setISBN(String ISBN) {
//        this.ISBN = ISBN;
//    }

    @Override
    public String toString(){
        return "Name: " + name + " Publisher: " + publisher +
                " Auther: " + auther + "Yearof publish: " + yearOfPublish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getYearOfPublish() == book.getYearOfPublish() && Objects.equals(getName(), book.getName()) && Objects.equals(getPublisher(), book.getPublisher()) && Objects.equals(getAuther(), book.getAuther()); //&& Objects.equals(getISBN(), book.getISBN());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPublisher(), getAuther(), getYearOfPublish()); //getISBN());
    }


}
