package com.example.budgetwolt2.consoleCourseWork;

import java.io.*;

public class Utils {





    public static void writeWoltToFile(Wolt wolt) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(name:"wolt.txt")));
            out.writeObject(wolt);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static Wolt readWolfFromFile(){
        ObjectInputStream in = null;
        Wolt wolt = null;
        try {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(name: "wolt.txt")));
            wolt = (Wolt) in.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return wolt;
    }

}
