/**
Klassen Resept er abstrakt og tar imot et Legemiddel-objekt, et Lege-objekt, pasientId, og antall reit
i konstruktøren. Inkluderer getters for instansvariablene. I tillegg eksisterer en statisk integer, total,
som økes med 1 for hver instans av klassen, slik at hvert objekt får en unik ID.
Inneholder også to abstrakte metoder, farge() og prisAaBetale().
*/

abstract public class Resept {
    protected static int total = 0;
    protected int id;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected int pasientId, reit;
	protected Pasient pasient;

    public Resept(Legemiddel middel, Lege lege, Pasient pas, int rei) {
        legemiddel = middel;
        utskrivendeLege = lege;
        pasient = pas;
        reit = rei;

        total++;
        id = total;
    }

    public int hentId() {
        return id;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLege() {
        return utskrivendeLege;
    }

    public int hentPasient() {
        return pasient;
    }

    public int hentReit() {
        return reit;
    }

    // Metoden prøver å bruke en reit, om det er noen tilgjengelig, og returnerer true. Ellers returneres false.
    public boolean bruk() {
        if (reit <= 0) {
            return false;
        }
        reit--;
        return true;
    }

    abstract public String farge();

    abstract public int prisAaBetale();

    // Informasjon om resepten
    @Override
    public String toString() {
        return "\nId: " + id
        + "\nLegemiddel: " + legemiddel.hentNavn()
        + "\nLege: " + utskrivendeLege.hentNavn()
        + "\nPasient: " + pasient;
        + "\nAntall reit igjen: " + reit;
    }
}
