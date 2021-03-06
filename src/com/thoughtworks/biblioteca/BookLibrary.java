package com.thoughtworks.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;

/* BookLibrary has a list of books and can do checkIn, checkout operations and can format itself*/
public class BookLibrary {

    ArrayList<Book> availableBooks;
    ArrayList<Book> checkedOutBooks;
    HashMap<Book, User> bookUserMap;

    public BookLibrary(ArrayList<Book> availableBooks, ArrayList<Book> checkedOutBooks, HashMap<Book, User> bookUserMap) {
        this.availableBooks = availableBooks;
        this.checkedOutBooks = checkedOutBooks;
        this.bookUserMap = bookUserMap;
    }

    public String format() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%-30s%-30s%-30s\n", "Name", "Author", "Year Published"));
        for (Book book : availableBooks) {
            stringBuilder.append(book.toString() + "\n");
        }
        return stringBuilder.toString();
    }

    public String bookCheckOutInformation(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Books Checkout Information\n");
        stringBuilder.append("--------------------------\n");
        stringBuilder.append(String.format("%-30s%-30s\n", "Book", "User"));
        for(Book book: checkedOutBooks)
            stringBuilder.append(String.format("%-30s%-30s\n", book.name(), bookUserMap.get(book).format()));
        return stringBuilder.toString();
    }

    public boolean checkOut(Book book, User currentUser) {
        if (availableBooks.contains(book)) {
            int bookIndex = availableBooks.indexOf(book);
            checkedOutBooks.add(availableBooks.get(bookIndex));
            bookUserMap.put(availableBooks.get(bookIndex), currentUser);
            availableBooks.remove(book);
            return true;
        }
        return false;
    }

    public boolean checkIn(Book book, User currentUser) {
        if (checkedOutBooks.contains(book) && bookUserMap.get(book).equals(currentUser)) {
            int bookIndex = checkedOutBooks.indexOf(book);
            availableBooks.add(checkedOutBooks.get(bookIndex));
            checkedOutBooks.remove(book);
            return true;
        }
        return false;
    }
}
