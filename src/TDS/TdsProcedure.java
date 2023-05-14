package TDS;

import java.util.ArrayList;

public class TdsProcedure extends TdsElement{
    private String name;
    private ArrayList<TdsArgument> parametres;
    private int nbParametres;

    public TdsProcedure(String name, ArrayList<TdsArgument> parametres) {
        this.name = name;
        this.parametres = parametres;
        this.nbParametres = parametres.size();
    }

    @Override
    public String getName() {
        return name;
    }

    public int getNbParametres() {
        return nbParametres;
    }

    public ArrayList<TdsArgument> getParametres() {
        return parametres;
    }

    public String print() {
        String toPrint = " > Procedure : ";

        toPrint += this.name + "  |  ";

        toPrint += this.nbParametres + " : ";

        if(this.nbParametres > 0) {
            toPrint += ((TdsArgument) this.parametres.get(0)).getType();
            for (int i=1; i<this.nbParametres; i++)
                toPrint += ", " + ((TdsArgument) this.parametres.get(i)).getType();
        }

        return toPrint;
    }
}
