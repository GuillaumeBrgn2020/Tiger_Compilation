package AST;

import java.util.ArrayList;

public class Declaration_list implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public ArrayList<Ast> decl_list;
    public Declaration_list(ArrayList<Ast> decl_list){
        this.decl_list = decl_list;
    }
}
