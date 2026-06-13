/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Tijana
 */
public class Posiljalac {

    private Socket s;

    public Posiljalac(Socket s) {
        this.s = s;
    }

    public void posalji(Object obj) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(obj);
            oos.flush();
        } catch (IOException ex) {
            System.err.println("Greška prilikom slanja poruke: " + ex.getMessage());
        }

    }
}
