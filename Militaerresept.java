/**
Klassen Militaerresept arver fra HvitResept og gir alltid ut gratis respeter, altså pris = 0.
*/

public class Militaerresept extends HvitResept {

    public Militaerresept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    @Override
    public int prisAaBetale() {
        return 0;
    }

    @Override
    public String toString() {
        return "\nType: Militaerresept" + super.toString();
    }
}
