/**
Klassen Legemiddel er en abstrakt klasse som tar inn navn, pris, og virkestoff som parameter i kontruktøren.
I tillegg eksisterer en static instansvariabel, total, som øker for hvert objekt som opprettes.
I tillegg har klassen medfølgende getter- og setter-metoder.
*/

abstract public class Legemiddel {
    protected static int total = 0;
    protected String navn;
    protected int pris;
    protected double virkestoff;
    protected int id;

    public Legemiddel(String nv, int p, double stoff) {
        navn = nv;
        pris = p;
        virkestoff = stoff;

        total++;
        id = total;
    }

    public int hentId() {
        return id;
    }

    public String hentNavn() {
        return navn;
    }

    public int hentPris() {
        return pris;
    }

    public double hentVirkestoff() {
        return virkestoff;
    }

    public void settNyPris(int ny) {
        pris = ny;
    }

    // toString som returnerer en brukervennlig streng om informasjonen til legemiddelet
    @Override
    public String toString() {
        return "Id: " + id
        + "\nNavn: " + navn
        + "\nPris: " + pris
        + "\nVirkestoff: " + virkestoff;
    }
}
