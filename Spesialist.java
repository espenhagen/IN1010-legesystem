/**
Klassen arver fra Lege, og implementerer Godkjenningsfritak.
konstruktøren tar imot både navn, som sender til superklassen, og kontrollId, som lagres
som en instansvariabel i Spesialist.
*/

public class Spesialist extends Lege implements Godkjenningsfritak {
    String kontrollId;

    public Spesialist(String navn, String kId) {
        super(navn);
        kontrollId = kId;
    }

    @Override
    public String hentKontrollID() {
        return kontrollId;
    }

    @Override
    public String toString() {
        return super.toString() + "\nKontroll ID: " + kontrollId + "\n";
    }
}
