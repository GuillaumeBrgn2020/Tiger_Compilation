package AST;

public class Field_def implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Identifier typeID;
    public Identifier identifier;
    public Field_def(Identifier identifier, Identifier typeID){
        this.typeID = typeID;
        this.identifier = identifier;
    }
}
