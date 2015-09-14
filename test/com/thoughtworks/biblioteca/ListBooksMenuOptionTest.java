package com.thoughtworks.biblioteca;

import org.junit.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ListBooksMenuOptionTest {

    @Test
    public void shouldCallLibraryDisplayWhenDoOperationIsCalled(){
        Library library = mock(Library.class);
        ConsoleDisplayFactory consoleDisplayFactory = mock(ConsoleDisplayFactory.class);
        ListBooksMenuOption listBooksMenuOption = new ListBooksMenuOption(library, consoleDisplayFactory);

        listBooksMenuOption.doOperation();

        verify(library).format();
    }

    @Test
    public void shouldCallConsoleDisplayOnReturnedStringWhenDoOperationIsCalled(){
        Library library = mock(Library.class);
        ConsoleDisplayFactory consoleDisplayFactory = mock(ConsoleDisplayFactory.class);
        ListBooksMenuOption listBooksMenuOption = new ListBooksMenuOption(library, consoleDisplayFactory);

        listBooksMenuOption.doOperation();

        verify(consoleDisplayFactory).getNewConsoleDisplay(anyString());
    }
}
