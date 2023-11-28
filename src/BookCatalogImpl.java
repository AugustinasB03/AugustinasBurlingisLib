import lt.techin.library.Book;
import lt.techin.library.BookCatalog;
import lt.techin.library.BookNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class BookCatalogImpl implements BookCatalog {

    private ArrayList<Book> catalog;


    public BookCatalogImpl() {
        this.catalog = new ArrayList<>();
    }

    @Override
    public void addBook(Book book) {
        if (book != null && book.getIsbn() != null && book.getTitle() != null) {
            if (!(this.catalog.contains(book)))
                this.catalog.add(book);
        }
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        for (Book book : catalog) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }

        return null;
    }

    @Override
    public List<Book> searchBooksByAuthor(String authorName) {
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book book : catalog) {
            if (book.getAuthors().equals(authorName)) {
                booksByAuthor.add(book);
            }
        }

        return booksByAuthor;
    }

    @Override
    public int getTotalNumberOfBooks() {
        return this.catalog.size();
    }

    @Override
    public boolean isBookInCatalog(String isbn) {
        for (Book book : catalog) {
            if (book.getIsbn().equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isBookAvailable(String isbn) {
        for (Book book : catalog) {
            if (book.getIsbn().equals(isbn)) {
                if (book.isAvailable())
                    return true;
            }
        }
        return false;
    }

    @Override
    public Book findNewestBookByPublisher(String publisher) {
        for (Book book: catalog) {
            if (!(book.getPublisher().equals(publisher))) {
                Exception BookNotFoundException = new Exception();
                try {
                    throw BookNotFoundException;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }


        Book newestBook = new Book();
        int year = catalog.get(0).getPublicationYear();
        for (Book book : catalog) {
            if (book.getPublisher().equals(publisher)) {
                if (book.getPublicationYear() > year) {
                    year = book.getPublicationYear();
                    newestBook = book;
                }
            }
        }
        return newestBook;
    }

    @Override
    public List<Book> getSortedBooks() {


        return null;
    }

    @Override
    public Map<String, List<Book>> groupBooksByPublisher() {

        return null;
    }

    @Override
    public List<Book> filterBooks(Predicate<Book> predicate) {


        return null;
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        BigDecimal sum = new BigDecimal(0);
        for (Book book: catalog) {
            BigDecimal BigDecimal = convertToBigDecimal(book.getPrice());
            sum = BigDecimal;
        }

        return null;
    }

    public static BigDecimal convertToBigDecimal(Number number) {
        if (number instanceof Integer
                || number instanceof Long
                || number instanceof Short
                || number instanceof Byte) {
            return BigDecimal.valueOf(number.longValue());
        }
        return BigDecimal.valueOf(number.doubleValue());
    }
}
