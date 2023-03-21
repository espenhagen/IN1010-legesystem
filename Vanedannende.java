/**
Klassen Vanedannende tar imot styrke som parameter.
Inkluderer getter-metode for styrken og egen toString().
*/

public class Vanedannende extends Legemiddel {
    protected int styrke;

    public Vanedannende(String navn, int pris, double virkestoff, int styrk) {
        super(navn, pris, virkestoff);
        styrke = styrk;
    }

    public int hentVanedannendeStyrke() {
        return styrke;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String hentType() {
        return "vanedannende";
    }
}
