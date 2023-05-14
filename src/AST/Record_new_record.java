package AST;

public class Record_new_record implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Identifier identifier;
    public Ast field_list;
    public Record_new_record(Identifier identifier, Ast field_list){
        this.identifier = identifier;
        this.field_list = field_list;
    }
}
