package AST;

public class Lvalue_bis_array_value implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Ast expr;
    public Ast lValue_bis;

    public Lvalue_bis_array_value(Ast expr, Ast lValue_bis){
        this.expr = expr;
        this.lValue_bis = lValue_bis;
    }
}
