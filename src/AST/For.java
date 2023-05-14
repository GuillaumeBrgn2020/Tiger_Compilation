package AST;

public class For implements Ast{

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }
    public Ast initialization;
    public Ast termination;
    public Ast instr_list;

    public For(Ast initialization, Ast termination, Ast instr_list){
        this.initialization = initialization;
        this.termination = termination;
        this.instr_list = instr_list;
    }
}
