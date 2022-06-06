package at.FH.Database;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {

    private static String URL = "localhost";
    private static int PORT = 3306;

    /**
     * check if Server is running
     * @return server startus
     */
    public static boolean hostAvailable(){
        try(Socket s = new Socket(URL, PORT)){
            return true;
        } catch (IOException ignore) {

        }
        return false;
    }

}
