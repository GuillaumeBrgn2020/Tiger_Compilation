package AST;

public class Decl_procedure implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Identifier identifier;
    public Ast field_list;
    public Ast instr_list;
    public Decl_procedure(Identifier identifier, Ast field_list, Ast instr_list){
        this.identifier = identifier;
        this.field_list = field_list;
        this.instr_list = instr_list;
    }
}
