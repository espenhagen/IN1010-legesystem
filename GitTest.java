/**
 * Dette er en praktisk gjennomgang av git og versjonskontroll.
 * Jeg antar at dere har sett på eksempelet med "minforstecommit.txt"
 *
 * Dette er mest ment som ett mer utfyllende eksempel med bruk
 * av java-kode (som er det dere vil bruke på obligen)
 *
 * Gjør de nummererte stegene under
 *
 *  1. Vi starter lett med å lage en enkel utskriftsetning
 *     flytt denne filen inn i mappen der du klonet git-repoet til din maskin
 *     add, commit og push denne versjonen til origin
 *
 * 2. Endre utskriften over i **nettvarianten** på github.uio.no, f.eks endre ordet "forste" til "andre"
 *    commit endringen til master på web
 *
 * 3. endre ordet "forste" til tredje lokalt på din maskin
 *    add, commit og prøv å push til origin
 *    behold den lokale endringen når du rydder opp i konflikten
 *    kompiler og se at programmet kjører som det skal på din maskin
 *    last så endringen opp og se at den er oppdatert
 *
 * Av og til kan det være flere konflikter i en fil og/eller flere konflikter over flere filer.
 * Håndter hver konflikt, kompiler og sjekk at programmet fungerer som det skal før du laster opp.
 *
 * Merk at git håndterer java-filen likt som tekstfilen
 */

public class GitTest{
    public static void main(String[] args) {
        System.out.println("Dette er min forste java-commit");
        System.out.println("Endring");
		System.out.println("Elias sin flmepld");
        System.out.println("Ahmed gjor endringer ogsaa!");
    }
}
