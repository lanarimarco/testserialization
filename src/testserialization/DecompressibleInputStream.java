/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testserialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/**
 *
 * @author lana
 */
public class DecompressibleInputStream extends ObjectInputStream {

    public DecompressibleInputStream(InputStream in) throws IOException {
        super(in);
    }


    protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
        ObjectStreamClass resultClassDescriptor = super.readClassDescriptor(); // initially streams descriptor
        Class localClass = Class.forName(resultClassDescriptor.getName()); // the class in the local JVM that this descriptor represents.
        if (localClass == null) {
            System.out.println("No local class for " + resultClassDescriptor.getName());
            return resultClassDescriptor;
        }
        ObjectStreamClass localClassDescriptor = ObjectStreamClass.lookup(localClass);
        if (localClassDescriptor != null) { // only if class implements serializable
            final long localSUID = localClassDescriptor.getSerialVersionUID();
            final long streamSUID = resultClassDescriptor.getSerialVersionUID();
            if (streamSUID != localSUID) { // check for serialVersionUID mismatch.
                final StringBuffer s = new StringBuffer("Overriding serialized class version mismatch: ");
                s.append("local serialVersionUID = ").append(localSUID);
                s.append(" stream serialVersionUID = ").append(streamSUID);
                Exception e = new InvalidClassException(s.toString());
                System.out.println("Potentially Fatal Deserialization Operation. " + e);
                resultClassDescriptor = localClassDescriptor; // Use local class descriptor for deserialization
            }
        }
        return resultClassDescriptor;
    }
}