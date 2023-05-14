package AST;

public class Decl_var_base_type implements Ast{


    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }
    public Identifier identifier;
    public Identifier typeID;
    public Ast expr;

    public Decl_var_base_type(Identifier identifier, Identifier typeID, Ast expr){
        this.identifier = identifier;
        this.typeID = typeID;
        this.expr = expr;
    }
}
