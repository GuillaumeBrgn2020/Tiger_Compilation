package AST;


public class Operation implements Ast{

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Ast left_expr;
    public Identifier operator;
    public Ast right_expr;

    public Operation(Ast left_expr, Identifier operator, Ast right_expr){
        this.left_expr = left_expr;
        this.operator = operator;
        this.right_expr = right_expr;
    }
}

