package com.thoughtworks.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LoginTest {

    @Test
    public void shouldCallReadOnInputTwiceToTakeLoginCredentialsWhenLoginIsCalled(){
        InputReader inputReader = mock(InputReader.class);
        ConsoleDisplayFactory consoleDisplayFactory = new ConsoleDisplayFactory();
        Authenticator authenticator = mock(Authenticator.class);
        Login login = new Login(authenticator, inputReader, consoleDisplayFactory);

        when(inputReader.read()).thenReturn("222-2222", "juliusseizure");
        login.login();

        verify(inputReader, times(2)).read();
    }

    @Test
    public void shouldCallAuthenticateOnAuthenticatorWhenLoginIsCalled(){
        InputReader inputReader = mock(InputReader.class);
        ConsoleDisplayFactory consoleDisplayFactory = new ConsoleDisplayFactory();
        Authenticator authenticator = mock(Authenticator.class);
        Login login = new Login(authenticator, inputReader, consoleDisplayFactory);

        when(inputReader.read()).thenReturn("222-2222", "juliusseizure");
        login.login();

        verify(authenticator).authenticate(anyString(), anyString());
    }

    @Test
    public void shouldReturnSessionWithUserWhenLoginIsCalled(){
        InputReader inputReader = mock(InputReader.class);
        ConsoleDisplayFactory consoleDisplayFactory = new ConsoleDisplayFactory();
        Authenticator authenticator = mock(Authenticator.class);
        Login login = new Login(authenticator, inputReader, consoleDisplayFactory);

        when(inputReader.read()).thenReturn("222-2222", "juliusseizure");

        assertEquals(Session.class, login.login().getClass());
    }
}
