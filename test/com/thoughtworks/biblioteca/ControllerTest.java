package com.thoughtworks.biblioteca;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ControllerTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldCallFormatOnWelcomeMessageWhenStartIsCalled() {
        exit.expectSystemExit();

        WelcomeMessage welcomeMessage = mock(WelcomeMessage.class);
        MainMenu mainMenu = mock(MainMenu.class);
        ConsoleDisplayFactory consoleDisplayFactory = mock(ConsoleDisplayFactory.class);
        ConsoleDisplay consoleDisplay = mock(ConsoleDisplay.class);
        InputReader inputReader = mock(InputReader.class);
        Parser parser = mock(Parser.class);
        Controller controller = new Controller(welcomeMessage, mainMenu, consoleDisplayFactory, inputReader, parser);

        when(consoleDisplayFactory.getNewConsoleDisplay(anyString())).thenReturn(consoleDisplay);
        when(inputReader.read()).thenReturn("*", "2");
        when(parser.parse(anyString())).thenReturn(new InvalidMenuOption("Select a valid option!", consoleDisplayFactory), new QuitMenuOption());
        controller.start();

        verify(welcomeMessage).format();
    }

    @Test
    public void shouldCallReadOnInputReaderWhenStartIsCalled(){
        exit.expectSystemExit();

        WelcomeMessage welcomeMessage = mock(WelcomeMessage.class);
        MainMenu mainMenu = mock(MainMenu.class);
        ConsoleDisplayFactory consoleDisplayFactory = mock(ConsoleDisplayFactory.class);
        ConsoleDisplay consoleDisplay = mock(ConsoleDisplay.class);
        InputReader inputReader = mock(InputReader.class);
        Parser parser = mock(Parser.class);
        Controller controller = new Controller(welcomeMessage, mainMenu, consoleDisplayFactory, inputReader, parser);

        when(consoleDisplayFactory.getNewConsoleDisplay(anyString())).thenReturn(consoleDisplay);
        when(inputReader.read()).thenReturn("*", "2");
        when(parser.parse(anyString())).thenReturn(new InvalidMenuOption("Select a valid option!", consoleDisplayFactory), new QuitMenuOption());
        controller.start();

        verify(inputReader).read();
    }

    @Test
    public void shouldCallParseOnParserWhenStartIsCalled(){
        exit.expectSystemExit();

        WelcomeMessage welcomeMessage = mock(WelcomeMessage.class);
        MainMenu mainMenu = mock(MainMenu.class);
        ConsoleDisplayFactory consoleDisplayFactory = mock(ConsoleDisplayFactory.class);
        ConsoleDisplay consoleDisplay = mock(ConsoleDisplay.class);
        InputReader inputReader = mock(InputReader.class);
        Parser parser = mock(Parser.class);
        Controller controller = new Controller(welcomeMessage, mainMenu, consoleDisplayFactory, inputReader, parser);

        when(consoleDisplayFactory.getNewConsoleDisplay(anyString())).thenReturn(consoleDisplay);
        when(inputReader.read()).thenReturn("*", "2");
        when(parser.parse(anyString())).thenReturn(new InvalidMenuOption("Select a valid option!", consoleDisplayFactory), new QuitMenuOption());
        controller.start();

        verify(parser).parse(anyString());
    }


    @Test
    public void shouldCallDoOperatonOnMenuOptionWhenStartIsCalled(){
        exit.expectSystemExit();

        WelcomeMessage welcomeMessage = mock(WelcomeMessage.class);
        MainMenu mainMenu = mock(MainMenu.class);
        ConsoleDisplayFactory consoleDisplayFactory = mock(ConsoleDisplayFactory.class);
        ConsoleDisplay consoleDisplay = mock(ConsoleDisplay.class);
        InputReader inputReader = mock(InputReader.class);
        Parser parser = mock(Parser.class);
        Controller controller = new Controller(welcomeMessage, mainMenu, consoleDisplayFactory, inputReader, parser);
        QuitMenuOption quitMenuOption = new QuitMenuOption();

        when(consoleDisplayFactory.getNewConsoleDisplay(anyString())).thenReturn(consoleDisplay);
        when(inputReader.read()).thenReturn("2");
        when(parser.parse(anyString())).thenReturn(quitMenuOption);
        controller.start();

        verify(quitMenuOption).doOperation();
    }
}
