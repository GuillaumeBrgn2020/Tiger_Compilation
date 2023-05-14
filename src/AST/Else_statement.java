package AST;

public class Else_statement implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Ast expr;
    public Else_statement(Ast expr){
        this.expr = expr;
    }
}
