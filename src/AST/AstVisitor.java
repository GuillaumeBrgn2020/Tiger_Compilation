package AST;


public interface AstVisitor<T> {

    // Modele :  public T visit(XXXX affect);
    public T visit(Identifier identifier);
    public T visit(Prgrm prgrm);
    public T visit(Operation operation);
    public T visit(For _for);
    public T visit(If_then if_then);
    public T visit(If_then_else if_then_else);
    public T visit(Lvalue_bis_array_value lvalue_bis_array_value);
    public T visit(Lvalue_bis_attribute lvalue_bis_attribute);
    public T visit(Lvalue_id Lvalue_id);
    public T visit(While _while);
    public T visit(Let_decl let_decl);
    public T visit(Let_expr let_expr);
    public T visit(Decl_var_simple decl_var_simple);
    public T visit(Decl_var_base_type decl_var_base_type);
    public T visit(Decl_procedure decl_procedure);
    public T visit(Decl_function decl_function);
    public T visit(Decl_type decl_type);
    public T visit(Array_new_array_of array_new_array_of);
    public T visit(Record_new_record record_new_record);
    public T visit(Affectation_operation affectation_operation);
    public T visit(Function_call function_call);


    public T visit(If_statement if_statement);
    public T visit(Then_statement then_statement);
    public T visit(Else_statement else_statement);


    public T visit(Declaration_list declaration_list);
    public T visit(Instruction_list instruction_list);


    public T visit(Field_list field_list);
    public T visit(Field_def field_def);
    public T visit(Field_use field_use);


    public T visit(Type_array_of type_array_of);


    public T visit(Initialization initialization);
    public T visit(Termination termination);
}