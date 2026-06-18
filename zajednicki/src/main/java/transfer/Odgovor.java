package transfer;

import java.io.Serializable;

/**
 * Predstavlja odgovor koji server salje klijentu nakon izvrsavanja
 * sistemske operacije. Implementiran kao Java record jer predstavlja
 * nepromenljiv (immutable) prenosni objekat (DTO) - jednom kreiran odgovor
 * se ne menja, samo se salje klijentu i cita na njegovoj strani.
 *
 * @param odgovor rezultat izvrsene sistemske operacije (objekat, lista,
 *        boolean i slicno, zavisno od izvrsene operacije)
 * @author Tijana
 * @version 1.0
 */
public record Odgovor(Object odgovor) implements Serializable {
}
