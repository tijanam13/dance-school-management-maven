/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import repozitorijum.Repozitorijum;
import repozitorijum.db.DBRepozitorijum;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

/**
 *
 * @author Tijana
 */
public abstract class OpstaSO {

    protected final Repozitorijum broker;

    public OpstaSO() {
        this.broker = new DBRepozitorijumGenericki();
    }

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

    protected abstract void preduslovi(Object parametar) throws Exception;

    protected abstract void izvrsi(Object parametar, Object uslov) throws Exception;

    private void pokreniTransakciju() throws Exception {
        ((DBRepozitorijum) broker).povezi();
    }

    private void potvrdiTransakciju() throws Exception {
        ((DBRepozitorijum) broker).commit();
    }

    private void ponistiTransakciju() throws Exception {
        ((DBRepozitorijum) broker).rollback();
    }

    private void prekiniKonekciju() throws Exception {
        ((DBRepozitorijum) broker).prekiniVezu();
    }

}
