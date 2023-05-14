package TDS;

public class TdsArgument extends TdsElement {

    private String type;
    private String nom;

    public TdsArgument(String nom) {
        this.nom = nom;
        this.type = "";
    }

    public TdsArgument(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return nom;
    }

    public String print() {
        String toPrint = " > Argument : ";
        toPrint += this.nom + "  |  ";
        toPrint += this.type;

        return toPrint;
    }
    
}
