package AST;

public class Identifier implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public String value;
    public Identifier(String value_){
        this.value = value_;
    }
}
