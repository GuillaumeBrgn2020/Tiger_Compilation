package TDS;

import java.util.ArrayList;

public class Tds {

    public static int region = 0;

    public ArrayList<TdsElement> tdsElements;

    public int imbricationNumber;
    public int regionNumber;

    public Tds parent;
    public ArrayList<Tds> sons;

    public String name;



    public Tds(String name){
        this.tdsElements = new ArrayList<>();

        this.imbricationNumber = 0;
        this.regionNumber = region;
        region++;

        this.parent = null;
        this.sons = new ArrayList<>();

        this.name = name;
    }

    public Tds(Tds parent, String name){
        this.tdsElements = new ArrayList<>();

        this.imbricationNumber = parent.imbricationNumber +1;
        this.regionNumber = region;
        region++;

        this.parent = parent;
        this.parent.sons.add(this);
        this.sons = new ArrayList<>();

        this.name = name;
    }

    public boolean containVarName(String name){
        for (TdsElement e : this.tdsElements){
            if (e.getClass().getName().equals("TDS.TdsVariable")){
                if (e.getName().equals(name)){
                    return true;
                }
            }
        }
        if (parent != null){
            return this.parent.containVarName(name);
        }
        return false;
    }

    public boolean containArgName(String name){
        for (TdsElement e : this.tdsElements){
            if (e.getClass().getName().equals("TDS.TdsArgument")){
                if (e.getName().equals(name)){
                    return true;
                }
            }
        }
        if (parent != null){
            return this.parent.containArgName(name);
        }
        return false;
    }

     public boolean containTypeName(String name){
        for (TdsElement e : this.tdsElements){
            if (e.getClass().getName().equals("TDS.TdsType")){
                if (e.getName().equals(name)){
                    return true;
                }
            }
        }

         if (parent != null){
             return this.parent.containTypeName(name);
         }
        return false;
    }

    public boolean containFunctionName(String name){
        for (TdsElement e : this.tdsElements){
            if (e.getClass().getName().equals("TDS.TdsFonction")){
                if (e.getName().equals(name)){
                    return true;
                }
            }
        }

        if (parent != null){
            return this.parent.containFunctionName(name);
        }
        return false;
    }

    public boolean containProcedureName(String name){
        for (TdsElement e : this.tdsElements){
            if (e.getClass().getName().equals("TDS.TdsProcedure")){
                if (e.getName().equals(name)){
                    return true;
                }
            }
        }

        if (parent != null){
            return this.parent.containProcedureName(name);
        }

        return false;
    }

    public int getNbParamFonction(String name){
        for (TdsElement e : this.tdsElements){
            if (e.getClass().getName().equals("TDS.TdsProcedure")){
                if (e.getName().equals(name)){
                    int param = ((TdsProcedure) e).getNbParametres();
                    return param;
                }
            }
            if (e.getClass().getName().equals("TDS.TdsFonction")){
                if (e.getName().equals(name)){
                    int param = ((TdsFonction) e).getNbParametres();
                    return param;
                }
            }
        }
      if (parent != null){
            return this.parent.getNbParamFonction(name);
      }
        return -1;
    }



    public String getTypeVar(String name){
        for (TdsElement e : this.tdsElements){
            if (e.getClass().getName().equals("TDS.TdsVariable")){
                if (e.getName().equals(name)){
                    return ((TdsVariable) e).getType();
                }
            }
        }
        if (parent != null){
            return this.parent.getTypeVar(name);
        }
        return null;
    }

    public String getName(){
        return ("TDS_" + this.name + "_" + this.regionNumber + "_" + this.imbricationNumber);
    }



    public void print(){
        String toPrint = "======================================\n";

        toPrint += this.getName() + "\n";

        if(this.parent != null)
            toPrint += "Parent : " + this.parent.getName() + "\n";

        toPrint += "\n-------------------------\n";


        for(TdsElement tdsElement : this.tdsElements)
            toPrint += tdsElement.print() + "\n";

        System.out.println(toPrint);

        for(Tds tds : this.sons)
            tds.print();
    }
}
