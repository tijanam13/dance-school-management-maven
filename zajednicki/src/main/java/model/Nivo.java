package model;

import java.io.Serializable;

/**
 * Enumeracija koja predstavlja nivo kvalifikacije instruktora.
 *
 * @author Tijana
 * @version 1.0
 * @see InstruktorKvalifikacija
 */
public enum Nivo implements Serializable {

    /** Osnovni nivo kvalifikacije. */
    osnovni,

    /** Srednji nivo kvalifikacije. */
    srednji,

    /** Napredni nivo kvalifikacije. */
    napredni;
}