/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testserialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author lana
 */
public class TestSerialization {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
//        User user = new User("pippo");
//        user.inc();
        File workDir = new File(System.getProperty("user.dir") + "/work");
        File file = new File(workDir, "user.out");
//        
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
//        out.writeObject(user);
//        out.close();
        
        ObjectInputStream in = new DecompressibleInputStream(new FileInputStream(file));
        
        System.out.println(in.readObject());
    }
    
}
