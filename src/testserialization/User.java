/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testserialization;

import java.io.Serializable;

/**
 *
 * @author lana
 */
public class User implements Serializable{
    
    static final long serialVersionUID = 1L;
    
    private int counter;
    private String id;

    public User(String id) {
        this.id = id;
    }

    public User() {
    }
    
    

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    
    public void inc(){
        counter++;
    }

    @Override
    public String toString() {
        return "User{" + "counter=" + counter + ", id=" + id + '}';
    }
    
    
    
    
    
    
}
