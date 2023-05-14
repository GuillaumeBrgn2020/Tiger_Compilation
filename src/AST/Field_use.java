package AST;

public class Field_use implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Identifier identifier;
    public Ast instr;
    public Field_use(Identifier identifier, Ast instr){
        this.identifier = identifier;
        this.instr = instr;
    }
}
