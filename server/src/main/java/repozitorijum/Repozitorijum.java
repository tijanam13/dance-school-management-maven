/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repozitorijum;

import java.util.List;

/**
 *
 * @author Tijana
 * @param <T>
 */
public interface Repozitorijum<T> {

    List<T> vratiSve(T parametar, String uslov) throws Exception;

    List<T> vratiSve(T parametar) throws Exception;

    void dodaj(T parametar) throws Exception;

    void izmeni(T parametar) throws Exception;

    void obrisi(T parametar) throws Exception;

    T vratiObjekat(T parametar, String uslov) throws Exception;

    T vratiObjekat(T parametar) throws Exception;
}
