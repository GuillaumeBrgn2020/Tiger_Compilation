package TDS;

import java.util.ArrayList;

public class TdsType extends TdsElement{


    private String name;
    private ArrayList<TdsArgument> arguments;
    private String cloneType;

    public TdsType(String name, ArrayList<TdsArgument> arguments){
        this.name = name;
        this.arguments = arguments;
        this.cloneType = "";
    }

    public TdsType(String name, String cloneType){
        this.name = name;
        this.arguments = new ArrayList<>();
        this.cloneType = cloneType;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String print() {
        String toPrint = " > Type : ";

        toPrint += this.name + "  |  ";

        if(this.cloneType.equals("")) {
            toPrint += this.arguments.size() + " : ";

            if (this.arguments.size() > 0) {
                toPrint += " [ " + this.arguments.get(0).getType()
                        + " : " + this.arguments.get(0).getName() + " ]";
                for (int i = 1; i < this.arguments.size(); i++)
                    toPrint += ", " + " [ " + this.arguments.get(i).getType()
                            + " : " + this.arguments.get(i).getName() + " ]";
            }
        } else {
            toPrint += this.cloneType;
        }

        return toPrint;
    }
}
