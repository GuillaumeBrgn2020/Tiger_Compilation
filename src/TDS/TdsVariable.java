package TDS;

public class TdsVariable extends TdsElement {

    private String name;
    private String type;

    public TdsVariable(String name) {
        this.name = name;
        this.type = "";
    }

    public TdsVariable(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String print() {
        String toPrint = " > Variable : ";
        toPrint += this.name + "  |  ";
        toPrint += this.type;

        return toPrint;
    }
    
}
