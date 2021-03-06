package com.thoughtworks.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookLibraryTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setupStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(System.out);
    }

    @Test
    public void shouldReturnFormattedStringOfLibraryWhenFormatIsCalled() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("Brief History of Time", "Stephen Hawking", 1988));
        books.add(new Book("Crime and Punishment", "Fyodor Dostoyevsky", 1866));
        books.add(new Book("Seven Minutes", "Irving Wallace", 1969));
        ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
        HashMap<Book, User> bookUserHashMap = new HashMap<Book, User>();
        BookLibrary bookLibrary = new BookLibrary(books, checkedOutBooks, bookUserHashMap);

        bookLibrary.format();

        assertEquals(String.format("%-30s%-30s%-30s\n", "Name", "Author", "Year Published") +
                String.format("%-30s%-30s%-30s\n", "Brief History of Time", "Stephen Hawking", "1988") +
                String.format("%-30s%-30s%-30s\n", "Crime and Punishment", "Fyodor Dostoyevsky", "1866") +
                String.format("%-30s%-30s%-30s\n", "Seven Minutes", "Irving Wallace", "1969"), bookLibrary.format());
    }

    @Test
    public void shouldReturnTrueWhenBookIsInAvailableBooksWhenCheckOutIsCalled() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("Brief History of Time", "Stephen Hawking", 1988));
        books.add(new Book("Crime and Punishment", "Fyodor Dostoyevsky", 1866));
        books.add(new Book("Seven Minutes", "Irving Wallace", 1969));
        ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
        HashMap<Book, User> bookUserHashMap = new HashMap<Book, User>();
        BookLibrary bookLibrary = new BookLibrary(books, checkedOutBooks, bookUserHashMap);
        User user = new User("222-2222", "juliusseizure", "user", "", "", "");

        assertTrue(bookLibrary.checkOut(new Book("Seven Minutes"), user));
    }

    @Test
    public void shouldReturnFalseWhenBookIsNotInAvailableBooksWhenCheckOutIsCalled() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("Brief History of Time", "Stephen Hawking", 1988));
        books.add(new Book("Crime and Punishment", "Fyodor Dostoyevsky", 1866));
        books.add(new Book("Seven Minutes", "Irving Wallace", 1969));
        ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
        HashMap<Book, User> bookUserHashMap = new HashMap<Book, User>();
        BookLibrary bookLibrary = new BookLibrary(books, checkedOutBooks, bookUserHashMap);
        User user = new User("222-2222", "juliusseizure", "user", "", "", "");

        assertFalse(bookLibrary.checkOut(new Book("Not Valid Stuff"), user));
    }

    @Test
    public void shouldReturnTrueWhenBookIsInCheckoutBooksWhenCheckInIsCalled() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("Brief History of Time", "Stephen Hawking", 1988));
        books.add(new Book("Crime and Punishment", "Fyodor Dostoyevsky", 1866));
        books.add(new Book("Seven Minutes", "Irving Wallace", 1969));
        ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
        HashMap<Book, User> bookUserHashMap = new HashMap<Book, User>();
        BookLibrary bookLibrary = new BookLibrary(books, checkedOutBooks, bookUserHashMap);
        User user = new User("222-2222", "juliusseizure", "user", "", "", "");

        bookLibrary.checkOut(new Book("Seven Minutes"), user);

        assertTrue(bookLibrary.checkIn(new Book("Seven Minutes"), user));
    }

    @Test
    public void shouldReturnFalseWhenBookIsNotInCheckoutBooksWhenCheckInIsCalled() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("Brief History of Time", "Stephen Hawking", 1988));
        books.add(new Book("Crime and Punishment", "Fyodor Dostoyevsky", 1866));
        books.add(new Book("Seven Minutes", "Irving Wallace", 1969));
        ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
        HashMap<Book, User> bookUserHashMap = new HashMap<Book, User>();
        BookLibrary bookLibrary = new BookLibrary(books, checkedOutBooks, bookUserHashMap);
        User user = new User("222-2222", "juliusseizure", "user", "", "", "");

        assertFalse(bookLibrary.checkIn(new Book("Not Valid Stuff"), user));
    }

    @Test
    public void shouldReturnBookCheckoutInformationWhenBookCheckoutInformationIsCalled() {
        BookLibrary bookLibrary = new LibraryFactory().getDefaultLibrary();

        bookLibrary.checkOut(new Book("Seven Minutes"), new User("222-2222", "juliusseizure", "user", "Julius Caesar", "julius@caesar.com", "888888888"));

        assertEquals("Books Checkout Information\n" + "--------------------------\n" +
                String.format("%-30s%-30s\n", "Book", "User") +
                String.format("%-30s%-30s\n", "Seven Minutes", String.format("%-20s%-20s%-30s%-20s\n", "222-2222", "Julius Caesar", "julius@caesar.com", "888888888")),
                bookLibrary.bookCheckOutInformation());
    }
}
