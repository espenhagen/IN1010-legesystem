/**
Klassen Stabel arver fra Lenkeliste.
Klassen inneholder to nye metoder, leggpaa(T x) som legger et nytt Node-objekt bakerst i lista.
Kall på taAv() fjerner og returnerer dataen fra det bakerste objektet i lista.
Datastrukturen blir dermed Last In First Out (LIFO) ettersom det siste objektet inn, er det som fjernes først.
*/

public class Stabel <T> extends Lenkeliste <T> {

    // Bruker superklassens leggTil-metode
    public void leggPaa(T x) {
        super.leggTil(x);
    }

    // For å fjerne det fremste objektet, kan vi kalle på superklassens fjern() med størrelsen - 1 som parameter
    public T taAv() {
        return super.fjern(this.stoerrelse() - 1);
    }
}
