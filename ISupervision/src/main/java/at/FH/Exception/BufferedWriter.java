package at.FH.Exception;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class BufferedWriter {

    private static final String filename = "log.out";

    public void write(String s){
        try (java.io.BufferedWriter bufferedWriter = new java.io.BufferedWriter(new FileWriter(filename, false))) {
            Date d = new Date();
            bufferedWriter.write(d + ": " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
