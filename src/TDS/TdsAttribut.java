package TDS;

public class TdsAttribut extends TdsElement {

    private String name;
    private String type;

    public TdsAttribut(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public String print() {
        String toPrint = " > Attribute : ";

        toPrint += this.name + "  |  ";
        toPrint += this.type;

        return toPrint;
    }
    
}
