package AST;

public class Decl_function implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Identifier identifier;
    public Ast field_list;
    public Identifier typeID;
    public Ast instr_list;
    public Decl_function(Identifier identifier, Ast field_list, Identifier typeID, Ast instr_list){
        this.identifier = identifier;
        this.field_list = field_list;
        this.typeID = typeID;
        this.instr_list = instr_list;
    }
}
