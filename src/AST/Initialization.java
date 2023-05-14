package AST;

public class Initialization implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Identifier identifier;
    public Ast instr;
    public Initialization(Identifier identifier, Ast instr){
        this.identifier = identifier;
        this.instr = instr;
    }
}
