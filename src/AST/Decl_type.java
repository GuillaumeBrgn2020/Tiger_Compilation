package AST;

public class Decl_type implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Identifier identifier;
    public Ast type;
    public Decl_type(Identifier identifier, Ast type){
        this.identifier = identifier;
        this.type = type;
    }
}
