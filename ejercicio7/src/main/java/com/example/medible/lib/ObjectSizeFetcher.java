package com.example.medible.lib;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.instrument.Instrumentation;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjectSizeFetcher {

    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object o) {
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.close();
            return baos.size();
        } catch (IOException ex) {
            Logger.getLogger(ObjectSizeFetcher.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) oos.close();
            } catch (IOException ex) {
                Logger.getLogger(ObjectSizeFetcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
}
