package com.thoughtworks.biblioteca;

import java.util.ArrayList;

/* Library factory manufactures a default Library and returns it */
public class LibraryFactory {

    public Library getDefaultLibrary() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("Brief History of Time", "Stephen Hawking", 1988));
        books.add(new Book("Crime and Punishment", "Fyodor Dostoyevsky", 1866));
        books.add(new Book("Seven Minutes", "Irving Wallace", 1969));
        return new Library(books);
    }
}
