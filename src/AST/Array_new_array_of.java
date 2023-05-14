package AST;

public class Array_new_array_of implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Identifier identifier;
    public Ast expr;
    public Ast array_type;
    public Array_new_array_of(Identifier identifier, Ast expr, Ast array_type){
        this.identifier = identifier;
        this.expr = expr;
        this.array_type = array_type;
    }
}
