package AST;

public class Decl_var_simple implements Ast{

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }
    public Identifier identifier;
    public Ast expr;

    public Decl_var_simple(Identifier identifier, Ast expr){
        this.identifier = identifier;
        this.expr = expr;
    }
}
