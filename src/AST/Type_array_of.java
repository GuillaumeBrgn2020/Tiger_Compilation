package AST;

public class Type_array_of implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Identifier typeID;
    public Type_array_of(Identifier typeID){
        this.typeID = typeID;
    }
}
