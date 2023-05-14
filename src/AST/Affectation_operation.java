package AST;

public class Affectation_operation implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Ast lvalue;
    public Ast expr;
    public Affectation_operation(Ast lvalue, Ast expr){
        this.lvalue = lvalue;
        this.expr = expr;
    }
}
