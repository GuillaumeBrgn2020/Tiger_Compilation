package AST;

public class Lvalue_id implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Identifier identifier;
    public Ast lValue_bis;

    public Lvalue_id(Identifier identifier, Ast lValue_bis){
        this.identifier = identifier;
        this.lValue_bis = lValue_bis;
    }

}
