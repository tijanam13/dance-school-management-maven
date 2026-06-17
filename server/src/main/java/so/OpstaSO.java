package so;

import repozitorijum.Repozitorijum;
import repozitorijum.db.DBRepozitorijum;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

/**
 * Apstraktna klasa koja predstavlja osnovu svih sistemskih operacija u aplikaciji.
 * Definise sablonski obrazac (Template Method Pattern) za izvrsavanje sistemskih operacija
 * uz upravljanje transakcijama nad bazom podataka.
 * Svaka konkretna sistemska operacija nasledjuje ovu klasu i implementira
 * metode {@link #preduslovi(Object)} i {@link #izvrsi(Object, Object)}.
 *
 * @author Tijana
 * @version 1.0
 */
public abstract class OpstaSO {

    /**
     * Repozitorijum koji se koristi za komunikaciju sa bazom podataka.
     */
    protected final Repozitorijum broker;

    /**
     * Podrazumevani konstruktor koji inicijalizuje repozitorijum.
     */
    public OpstaSO() {
        this.broker = new DBRepozitorijumGenericki();
    }

    /**
     * Izvrsava sistemsku operaciju po sablonskom obrascu.
     * Redosled izvrsavanja: provera preduslova, pokretanje transakcije,
     * izvrsavanje operacije i potvrda transakcije.
     * Ako dodje do greske u bilo kojoj fazi, transakcija se ponistava
     * i izuzetak se propagira ka pozivaocu.
     *
     * @param parametar objekat koji se prosledjuje sistemskoj operaciji
     * @param uslov uslov koji se koristi pri izvrsavanju operacije
     * @throws Exception ako dodje do greske u bilo kojoj fazi izvrsavanja
     */
    public final void izvrsiOperaciju(Object parametar, Object uslov) throws Exception {
        try {
            preduslovi(parametar);
            pokreniTransakciju();
            izvrsi(parametar, uslov);
            potvrdiTransakciju();
        } catch (Exception e) {
            ponistiTransakciju();
            throw e;
        }
    }

    /**
     * Proverava preduslove pre izvrsavanja sistemske operacije.
     * Svaka konkretna sistemska operacija implementira ovu metodu
     * i u njoj vrsi validaciju ulaznih parametara.
     *
     * @param parametar objekat koji se validira
     * @throws Exception ako preduslovi nisu ispunjeni
     */
    protected abstract void preduslovi(Object parametar) throws Exception;

    /**
     * Izvrsava konkretnu sistemsku operaciju.
     * Svaka konkretna sistemska operacija implementira ovu metodu
     * i u njoj vrsi odgovarajuce izmene nad bazom podataka.
     *
     * @param parametar objekat nad kojim se operacija izvrsava
     * @param uslov uslov koji se koristi pri izvrsavanju operacije
     * @throws Exception ako dodje do greske pri izvrsavanju operacije
     */
    protected abstract void izvrsi(Object parametar, Object uslov) throws Exception;

    /**
     * Pokrece transakciju nad bazom podataka uspostavljanjem konekcije.
     *
     * @throws Exception ako dodje do greske pri uspostavljanju konekcije
     */
    private void pokreniTransakciju() throws Exception {
        ((DBRepozitorijum) broker).povezi();
    }

    /**
     * Potvrduje transakciju nad bazom podataka (commit).
     *
     * @throws Exception ako dodje do greske pri potvrdjivanju transakcije
     */
    private void potvrdiTransakciju() throws Exception {
        ((DBRepozitorijum) broker).commit();
    }

    /**
     * Ponistava transakciju nad bazom podataka (rollback).
     * Poziva se u slucaju greske tokom izvrsavanja operacije.
     *
     * @throws Exception ako dodje do greske pri ponistavanju transakcije
     */
    private void ponistiTransakciju() throws Exception {
        ((DBRepozitorijum) broker).rollback();
    }

    /**
     * Prekida konekciju sa bazom podataka.
     *
     * @throws Exception ako dodje do greske pri prekidanju konekcije
     */
    private void prekiniKonekciju() throws Exception {
        ((DBRepozitorijum) broker).prekiniVezu();
    }
}