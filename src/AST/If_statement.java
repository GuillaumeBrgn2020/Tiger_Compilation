package AST;

public class If_statement implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Ast expr;
    public If_statement(Ast expr){
        this.expr = expr;
    }
}
