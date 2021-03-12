/**
Narkotisk er av typen Legemiddel og tar imot styrke som parameter. Har getter-metode og egen toString().
*/

public class Narkotisk extends Legemiddel {
    protected int styrke;

    public Narkotisk(String navn, int pris, double virkestoff, int styrk) {
        super(navn, pris, virkestoff);
        styrke = styrk;
    }

    public int hentNarkotiskStyrke() {
        return styrke;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String hentType() {
        return "narkotisk";
    }
}
