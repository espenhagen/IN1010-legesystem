/**
Klassen Lenkeliste inneholder en privat (protected) klasse Node, som tar vare på selve dataen.
Lenkeliste har to instansvariabler, forste, som peker på det første Node-objektet i lista, og stoerrelse,
som angir nåværende størrelse i lista.
De ulike metodene, tar hensyn til en rekke edge-cases. Disse beskrives i de ulike metodene.
Brukeren av klassen, kan legge til, fjerne og sette verdier lista, både med og uten posisjon.
*/

public class Lenkeliste <T> implements Liste <T> {

    // Private klasse, Node som inneholder en referanse til neste Node, og egen data
    protected class Node {
        T data;
        Node neste = null;

        public Node(T dat) {
             data = dat;
        }
    }

    protected Node forste = null;

    // Teller over antall eksisterende noder lista, frem til jeg finner en tom node
    @Override
    public int stoerrelse() {
        Node hjelp = forste;
        int antall = 0;

        while (hjelp != null) {
            antall++;
            hjelp = hjelp.neste;
        }
        return antall;
    }

    // iterer meg gjennom lista, til punktet før riktig posisjon, og legger da til den nye Noden
    @Override
    public void leggTil(int pos, T x) {
        Node ny = new Node(x);
        Node hjelp = forste;
        int indeks = 0;

        // Hvis ugyldig posisjon, kast exception
        if (pos < 0 || pos > this.stoerrelse()) {
            throw new UgyldigListeIndeks(pos);
        }

        // hvis posisjonen er lik størrelsen, legg til på 'vanlig' måte
        if (pos == this.stoerrelse()) {
            this.leggTil(x);
            return;
        }

        // hvis posisjonen er null, angi en ny første-peker
        if (pos == 0) {
            ny.neste = forste;
            forste = ny;
            return;
        }

        while (indeks != pos - 1) {
            hjelp = hjelp.neste;
            indeks++;
        }

        ny.neste = hjelp.neste;
        hjelp.neste = ny;
    }

    @Override
    public void leggTil(T x) {
        // Hvis lista er tom, angi første-pekeren
        if (this.stoerrelse() == 0) {
            forste = new Node(x);
            return;
        }

        // Iterer til slutten, deretter legg til en ny node
        Node hjelp = forste;
        while (hjelp.neste != null) {
            hjelp = hjelp.neste;
        }

        hjelp.neste = new Node(x);
    }

    // Delegerer oppgaven til klassens fjern() og leggTil()-metoder
    @Override
    public void sett(int pos, T x) {

        fjern(pos);
        leggTil(pos, x);
    }

    // Iterer til riktig posisjon, deretter returner dataen fra noden
    @Override
    public T hent(int pos) {
        Node hjelp = forste;
        int indeks = 0;

        if (pos < 0 || pos >= this.stoerrelse()) {
            throw new UgyldigListeIndeks(pos);
        }

        if (this.stoerrelse() == 0) {
            throw new UgyldigListeIndeks(-1);
        }

        while (indeks != pos) {
            hjelp = hjelp.neste;
            indeks++;
        }

        return hjelp.data;
    }

    // Teller frem til posisjonen før, bruker en midlertidig variabel som hjelp, fjerner objektet, og returnerer hjelpe-variabelens data
    @Override
    public T fjern(int pos) {
        Node hjelp = forste;
        int indeks = 0;

        if (pos < 0 || pos >= this.stoerrelse()) {
            throw new UgyldigListeIndeks(pos);
        }

        if (pos == 0) {
            return this.fjern();
        }

        while (indeks < pos - 1) {
            hjelp = hjelp.neste;
            indeks++;
        }
        Node returneres = hjelp.neste;

        // Hvis objektet som skal fjernes, er det siste i lista, fjern det uten å ta hensyn til hva som er foran
        if (hjelp.neste.neste == null) {
            hjelp.neste = null;
            return returneres.data;
        }

        hjelp.neste = hjelp.neste.neste;

        return returneres.data;
    }

    // Midlertidig variabel, 'returneres', som brukes for å holde rede på objektet som skal fjernes
    @Override
    public T fjern() {
        if (this.stoerrelse() < 1) {
            throw new UgyldigListeIndeks(-1);
        }

        Node returneres = forste;
        forste = forste.neste;
        return returneres.data;
    }

    // Bygger opp et String-objekt som returnerer dataen i lista
    @Override
    public String toString() {
        String returneres = "";
        Node hjelp = forste;

        while (hjelp != null) {
            returneres += hjelp.data;
            hjelp = hjelp.neste;
        }

        return returneres;
    }
}
