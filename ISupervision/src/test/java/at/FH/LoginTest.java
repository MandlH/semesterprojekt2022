package at.FH;

import at.FH.General.Check;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LoginTest {
    private Check checker;
    private char[] ch = {'h', 'a', 'r', 'a', 'l', 'd', ' ', 'm' , 'a', 'n', 'd', 'l'};

    @BeforeEach
    public void setUp(){
        checker = new Check();
    }

    @Test
    public void loginTrueTest(){
        Assertions.assertTrue(checker.checkPassword(ch, "harald mandl"));
    }

    @Test
    public void loginFalseTest(){
        Assertions.assertFalse(checker.checkPassword(ch, "testing"));
    }

}
