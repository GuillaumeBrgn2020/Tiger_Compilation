package AST;

import java.util.ArrayList;

public class Field_list implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public ArrayList<Ast> field_list;
    public Field_list(ArrayList<Ast> field_list){
        this.field_list = field_list;
    }
}
