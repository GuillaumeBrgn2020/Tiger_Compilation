package AST;

public class Let_expr implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Ast declarations;
    public Ast instr_list;
    public Let_expr(Ast declarations, Ast instr_list){
        this.declarations = declarations;
        this.instr_list = instr_list;
    }
}
