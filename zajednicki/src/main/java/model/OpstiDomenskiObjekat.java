package model;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;

/**
 * Apstraktna klasa koja predstavlja osnovu svih domenskih objekata u sistemu.
 * Sve domenske klase nasledjuju ovu klasu i implementiraju njene apstraktne metode
 * koje se koriste za komunikaciju sa bazom podataka.
 *
 * @author Tijana
 * @version 1.0
 */
public abstract class OpstiDomenskiObjekat implements Serializable {

    /**
     * Vraca naziv tabele u bazi podataka koja odgovara konkretnoj domenskoj klasi.
     *
     * @return naziv tabele u bazi podataka
     */
    public abstract String vratiImeTabele();

    /**
     * Vraca listu domenskih objekata kreiranih na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return lista domenskih objekata
     */
    public abstract List<OpstiDomenskiObjekat> vratiListu(ResultSet rs);

    /**
     * Vraca nazive kolona koje se koriste prilikom ubacivanja objekta u bazu podataka.
     *
     * @return string sa nazivima kolona odvojenim zarezom
     */
    public abstract String vratiKoloneUbaci();

    /**
     * Vraca vrednosti atributa objekta koje se upisuju u bazu podataka.
     *
     * @return string sa vrednostima atributa odvojenim zarezom
     */
    public abstract String vratiVrednostiUbaci();

    /**
     * Vraca uslov WHERE koji se koristi za identifikaciju objekta u bazi podataka.
     *
     * @return string sa WHERE uslovom
     */
    public abstract String vratiKljucZaWhere();

    /**
     * Vraca string sa vrednostima atributa objekta za azuriranje u bazi podataka.
     *
     * @return string sa parovima kolona i vrednosti za UPDATE upit
     */
    public abstract String vratiVrednostiIzmeni();

    /**
     * Vraca jedan domenski objekat kreiran na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return domenski objekat
     */
    public abstract OpstiDomenskiObjekat vratiObjekat(ResultSet rs);
}