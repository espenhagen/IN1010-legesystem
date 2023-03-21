/**
Klassen SortertLenkeliste tillater ikke bruk av sett(int pos, T x) og leggTil(int pos, T x).
Klassen skriver over leggTil(T x), slik at den itererer gjennom lista, frem til den nye noden
har en mindre verdi enn det neste objektet i lista. Deretter settes den nye noden inn på riktig plass.
*/

public class SortertLenkeliste <T extends Comparable <T>> extends Lenkeliste <T> {

    // Metoden har en rekke edge-cases som må tas hensyn til
    @Override
    public void leggTil(T x) {
        Node ny = new Node(x);
        Node hjelp = forste;
        Node forrige = null;

        // Hvis lista er tom
        if (this.stoerrelse() == 0) {
            super.leggTil(x);
            return;
        }

        // Hvis det nye node-objektet er mindre enn det første, da får vi en ny første
        if (ny.data.compareTo(forste.data) < 0) {
            ny.neste = forste;
            forste = ny;
            return;
        }

        // Itererer gjennom, frem til vi finner et objekt som er større enn den nye node, eller  til hjelp.neste er lik null
        while (ny.data.compareTo(hjelp.data) >= 0 && hjelp.neste != null) {
            forrige = hjelp;
            hjelp = hjelp.neste;
        }

        // Hvis det foreløpige hjelp-objektet er større enn det nye, sett det inn
        if (ny.data.compareTo(hjelp.data) < 0) {
            ny.neste = hjelp;
            forrige.neste = ny;
            return;
        }

        // Hvis vi er ved slutten av lista, sett inn det nye objektet
        if (hjelp.neste == null) {
            hjelp.neste = ny;
            return;
        }
    }

    // Fjerner alltid det bakerste (største) objektet, med mindre det kun er et element i lista
    @Override
    public T fjern() {
        Node hjelp = forste;

        if (this.stoerrelse() < 1) {
            throw new UgyldigListeIndeks(-1);
        }

        if (this.stoerrelse() == 1) {
            Node returneres = forste;
            forste = null;
            return returneres.data;
        }

        return super.fjern(this.stoerrelse() - 1);
    }

    @Override
    public void sett(int pos, T x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggTil(int pos, T x) {
        throw new UnsupportedOperationException();
    }
}
