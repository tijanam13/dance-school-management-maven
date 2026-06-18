package transfer;

import java.io.Serializable;
import komunikacija.Operacije;

/**
 * Predstavlja zahtev koji klijent salje serveru.
 * Sadrzi sistemsku operaciju koju treba izvrsiti i parametar potreban
 * za njeno izvrsavanje. Implementiran kao Java record jer predstavlja
 * nepromenljiv (immutable) prenosni objekat (DTO) izmedju klijenta i servera -
 * jednom kreiran zahtev se ne menja, samo se cita na strani servera.
 *
 * @param operacija sistemska operacija koju server treba da izvrsi
 * @param param parametar potreban za izvrsavanje operacije (moze biti null
 *        za operacije koje ne zahtevaju parametar)
 * @author Tijana
 * @version 1.0
 */
public record Zahtev(Operacije operacija, Object param) implements Serializable {
}
