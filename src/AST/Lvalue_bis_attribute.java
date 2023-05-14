package AST;

public class Lvalue_bis_attribute implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Identifier identifier;
    public Ast lValue_bis;

    public Lvalue_bis_attribute(Identifier identifier, Ast lValue_bis){
        this.identifier = identifier;
        this.lValue_bis = lValue_bis;
    }
}
