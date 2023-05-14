package AST;

public class Then_statement implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Ast expr;
    public Then_statement(Ast expr){
        this.expr = expr;
    }
}
