/**
Klassen arver fra Resept, og har en metode farge() som returnerer reseptens farge, hvit.
Prisen som betales er full pris.
*/

public class HvitResept extends Resept {

    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    public String farge() {
        return "hvit";
    }

    public int prisAaBetale() {
        return legemiddel.hentPris();
    }

    @Override
    public String toString() {
        return "\nType: Hvit resept"
        + "\nFarge: " + farge() + super.toString()
        + "\nPris aa betale: " + prisAaBetale();
    }
}
