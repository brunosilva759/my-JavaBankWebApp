package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.Controller;
import io.codeforall.bootcamp.javabank.controller.LoginController;
import io.codeforall.bootcamp.javabank.services.AuthService;
import io.codeforall.bootcamp.javabank.view.LoginView;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    private LoginController loginController;
    private AuthService authService;
    private Controller controller;
    private LoginView view;

    @Before
    public void setup(){
        loginController = new LoginController();
        authService = mock(AuthService.class);
        controller = mock(Controller.class);
        view = mock(LoginView.class);

        loginController.setAuthService(authService);
        loginController.setNextController(controller);
        loginController.setView(view);
    }

    @Test
    public void testSuccessfulLogin(){
       when(authService.authenticate(1)).thenReturn(true);

       loginController.onLogin(1);

       verify(controller).init();
       assertFalse(loginController.isAuthFailed());
    }

    @Test
    public void failedLogin(){
        when(authService.authenticate(2)).thenReturn(false);

        loginController.onLogin(2);

    }
}
