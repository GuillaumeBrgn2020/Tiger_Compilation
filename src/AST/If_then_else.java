package AST;

public class If_then_else implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }
    public Ast expr_if;
    public Ast expr_then;
    public Ast expr_else;

    public If_then_else(Ast expr_if, Ast expr_then, Ast expr_else){
        this.expr_if = expr_if;
        this.expr_then = expr_then;
        this.expr_else = expr_else;
    }
}