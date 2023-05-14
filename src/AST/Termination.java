package AST;

public class Termination implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Ast instr;
    public Termination(Ast instr){
        this.instr = instr;
    }
}
