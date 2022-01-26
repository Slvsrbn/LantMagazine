package biblioteca;

public class BookStock {
    private int NoOfBooks;
    private Book book;

    public BookStock(int noOfBooks, Book book) {
        NoOfBooks = noOfBooks;
        this.book = book;
    }

    public int getNoOfBooks() {
        return NoOfBooks;
    }

    public void setNoOfBooks(int noOfBooks) {
        NoOfBooks = noOfBooks;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
