/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Tijana
 */
public abstract class OpstiDomenskiObjekat implements Serializable {

    public abstract String vratiImeTabele();

    public abstract List<OpstiDomenskiObjekat> vratiListu(ResultSet rs);

    public abstract String vratiKoloneUbaci();

    public abstract String vratiVrednostiUbaci();

    public abstract String vratiKljucZaWhere();

    public abstract String vratiVrednostiIzmeni();

    public abstract OpstiDomenskiObjekat vratiObjekat(ResultSet rs);

}
