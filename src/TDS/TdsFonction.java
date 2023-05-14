package TDS;


import java.util.ArrayList;


public class TdsFonction extends TdsElement {

    private String name;
    private String typeRetour;
    private ArrayList<TdsArgument> parametres;
    private int nbParametres;

    public TdsFonction(String name, String typeRetour, ArrayList<TdsArgument> parametres) {
        this.name = name;
        this.typeRetour = typeRetour;
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

    public String getTypeRetour() {
        return typeRetour;
    }

    public String print() {
        String toPrint = " > Function : ";

        toPrint += this.name + "  |  ";
        toPrint += this.typeRetour + "  |  ";

        toPrint += this.nbParametres + " : ";
        if(this.nbParametres > 0) {
            toPrint += ((TdsArgument) this.parametres.get(0)).getType();
            for (int i=1; i<this.nbParametres; i++)
                toPrint += ", " + ((TdsArgument) this.parametres.get(i)).getType();
        }

        return toPrint;
    }
    
}
