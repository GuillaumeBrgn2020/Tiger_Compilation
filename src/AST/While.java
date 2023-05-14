package AST;

public class While implements Ast{

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }
    public Ast expr_while;
    public Ast expr_do;

    public While(Ast expr_while, Ast expr_do){
        this.expr_while = expr_while;
        this.expr_do = expr_do;
    }
}
