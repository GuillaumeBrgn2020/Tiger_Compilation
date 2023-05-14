package AST;

public class If_then implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }
    public Ast expr_if;
    public Ast expr_then;

    public If_then(Ast expr_if, Ast expr_then){
        this.expr_if = expr_if;
        this.expr_then = expr_then;
    }

}
