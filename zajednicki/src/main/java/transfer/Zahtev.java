/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;
import komunikacija.Operacije;

/**
 *
 * @author Tijana
 */
public class Zahtev implements Serializable {

    private Operacije operacija;
    private Object param;

    public Zahtev() {
    }

    public Zahtev(Operacije operacija, Object param) {
        this.operacija = operacija;
        this.param = param;
    }

    public Operacije getOperacija() {
        return operacija;
    }

    public void setOperacija(Operacije operacija) {
        this.operacija = operacija;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

}
