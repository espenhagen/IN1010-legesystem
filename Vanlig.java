/**
Et objekt av Vanlig er i prinsippet likt som Legemiddel, uten noen ny implementasjon bortsett
fra toString-metoden.
*/

public class Vanlig extends Legemiddel {

    public Vanlig(String navn, int pris, double virkestoff) {
        super(navn, pris, virkestoff);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String hentType() {
        return "vanlig";
    }
}
