/**
Klassen arver fra Resept, og har en metode farge() som returnerer reseptens farge, hvit.
Prisen som betales er full pris.
*/

public class HvitResept extends Resept {

    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pas, int reit) {
        super(legemiddel, utskrivendeLege, pas, reit);
    }

    public String farge() {
        return "hvit";
    }

    public int prisAaBetale() {
        return legemiddel.hentPris();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String hentType() {
        return "hvit";
    }
}
