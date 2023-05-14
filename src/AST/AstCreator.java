package AST;

import parser.*;

import java.util.ArrayList;

public class AstCreator extends exprBaseVisitor<Ast>{

    /* Modele

    public Ast visitXXXX(exprParser.XXXXContext ctx) {

    }
    */

    public Ast visitPrgrm(exprParser.PrgrmContext ctx){
        Ast expr = ctx.getChild(0).accept(this);
        return new Prgrm(expr);
    }

    public Ast visitExpr_boolean_expr(exprParser.Expr_boolean_exprContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    public Ast visitBoolean_expr_loop(exprParser.Boolean_expr_loopContext ctx){return ctx.getChild(0).accept(this);}

    public Ast visitBoolean_expr_operation(exprParser.Boolean_expr_operationContext ctx){
        Ast left_expr = ctx.getChild(0).accept(this);
        Identifier operator = new Identifier(ctx.getChild(1).getText());
        Ast right_expr = ctx.getChild(2).accept(this);

        return new Operation(left_expr, operator, right_expr);
    }

    public Ast visitBinary_expr_loop(exprParser.Binary_expr_loopContext ctx){return ctx.getChild(0).accept(this);}

    public Ast visitBinary_expr_operation(exprParser.Binary_expr_operationContext ctx){

        Ast left_expr = ctx.getChild(0).accept(this);
        Identifier operator = new Identifier(ctx.getChild(1).getText());
        Ast right_expr = ctx.getChild(2).accept(this);

        return new Operation(left_expr, operator, right_expr);
    }

    public Ast visitAdd_expr_loop(exprParser.Add_expr_loopContext ctx){return ctx.getChild(0).accept(this);}

    public Ast visitAdd_expr_operation(exprParser.Add_expr_operationContext ctx){

        Ast left_expr = ctx.getChild(0).accept(this);
        Identifier operator = new Identifier(ctx.getChild(1).getText());
        Ast right_expr = ctx.getChild(2).accept(this);

        return new Operation(left_expr, operator, right_expr);
    }

    public Ast visitMultiply_expr_loop(exprParser.Multiply_expr_loopContext ctx){return ctx.getChild(0).accept(this);}

    public Ast visitMultiply_expr_operation(exprParser.Multiply_expr_operationContext ctx){
        Ast left_expr = ctx.getChild(0).accept(this);
        Identifier operator = new Identifier(ctx.getChild(1).getText());
        Ast right_expr = ctx.getChild(2).accept(this);

        return new Operation(left_expr, operator, right_expr);
    }

    public Ast visitExpression_lvalue(exprParser.Expression_lvalueContext ctx){
        return ctx.getChild(0).accept(this);
    }

    public Ast visitExpression_if_then_else(exprParser.Expression_if_then_elseContext ctx){
        return ctx.getChild(0).accept(this);
    }

    public Ast visitExpression_if_then(exprParser.Expression_if_thenContext ctx){
        return ctx.getChild(0).accept(this);
    }

    public Ast visitExpression_for(exprParser.Expression_forContext ctx){
        return ctx.getChild(0).accept(this);
    }

    public Ast visitExpression_while(exprParser.Expression_whileContext ctx){
        return ctx.getChild(0).accept(this);
    }

    public Ast visitExpression_let(exprParser.Expression_letContext ctx){
        return ctx.getChild(0).accept(this);
    }

    public Ast visitExpression_decl_array(exprParser.Expression_decl_arrayContext ctx){
        return ctx.getChild(0).accept(this);
    }

    public Ast visitExpression_decl_record(exprParser.Expression_decl_recordContext ctx){
        return ctx.getChild(0).accept(this);
    }

    public Ast visitExpression_affectation(exprParser.Expression_affectationContext ctx){
        return ctx.getChild(0).accept(this);
    }

    public Ast visitExpression_bloc(exprParser.Expression_blocContext ctx){
        return ctx.getChild(1).accept(this);
    }

    public Ast visitExpression_negate(exprParser.Expression_negateContext ctx){
        return ctx.getChild(0).accept(this);
    }

    public Ast visitExpression_function_call(exprParser.Expression_function_callContext ctx){
        return ctx.getChild(0).accept(this);
    }

    public Ast visitExpression_string_constant(exprParser.Expression_string_constantContext ctx){
        String value = ctx.getText().split("\"")[1];

        return new Identifier(value);
    }

    public Ast visitExpression_integer_constant(exprParser.Expression_integer_constantContext ctx){
        return new Identifier(ctx.getChild(0).getText());
    }

    public Ast visitLvalue_id(exprParser.Lvalue_idContext ctx) {

        Identifier identifier = new Identifier(ctx.getChild(0).getText());
        Ast lValue_bis = ctx.getChild(1).accept(this);

        return new Lvalue_id(identifier, lValue_bis);
    }

    public Ast visitLvalue_bis_attribute(exprParser.Lvalue_bis_attributeContext ctx) {

        Identifier identifier = new Identifier(ctx.getChild(1).getText());
        Ast lValue_bis = ctx.getChild(2).accept(this);

        return new Lvalue_bis_attribute(identifier, lValue_bis);
    }

    public Ast visitLvalue_bis_array_value(exprParser.Lvalue_bis_array_valueContext ctx) {

        Ast expr = ctx.getChild(1).accept(this);
        Ast lValue_bis = ctx.getChild(3).accept(this);

        return new Lvalue_bis_array_value(expr, lValue_bis);
    }

    public Ast visitLvalue_bis_empty(exprParser.Lvalue_bis_emptyContext ctx){
        return new Empty();
    }

    public Ast visitIf_then(exprParser.If_thenContext ctx) {

        Ast expr_if = ctx.getChild(1).accept(this);
        Ast expr_then = ctx.getChild(3).accept(this);

        return new If_then(expr_if, expr_then);
    }

    public Ast visitIf_then_else(exprParser.If_then_elseContext ctx) {

        Ast expr_if = ctx.getChild(1).accept(this);
        Ast expr_then = ctx.getChild(3).accept(this);
        Ast expr_else = ctx.getChild(5).accept(this);

        return new If_then_else(expr_if, expr_then, expr_else);
    }

    public Ast visitIf_statement(exprParser.If_statementContext ctx){
        Ast expr = ctx.getChild(0).accept(this);
        return new If_statement(expr);
    }

    public Ast visitThen_statement(exprParser.Then_statementContext ctx){
        Ast expr = ctx.getChild(0).accept(this);
        return new Then_statement(expr);
    }

    public Ast visitElse_statement(exprParser.Else_statementContext ctx){
        Ast expr = ctx.getChild(0).accept(this);
        return new Else_statement(expr);
    }

    public Ast visitFor(exprParser.ForContext ctx) {

        Ast initialization = ctx.getChild(1).accept(this);
        Ast termination = ctx.getChild(3).accept(this);
        Ast instr = ctx.getChild(5).accept(this);

        return new For(initialization, termination, instr);
    }

    public Ast visitInitialization(exprParser.InitializationContext ctx){
        Identifier identifier = new Identifier(ctx.getChild(0).getText());
        Ast instr = ctx.getChild(2).accept(this);

        return new Initialization(identifier, instr);
    }

    public Ast visitTermination(exprParser.TerminationContext ctx){
        Ast instr = ctx.getChild(0).accept(this);

        return new Termination(instr);
    }

    public Ast visitWhile(exprParser.WhileContext ctx) {

        Ast expr_while = ctx.getChild(1).accept(this);
        Ast expr_do = ctx.getChild(3).accept(this);

        return new While(expr_while, expr_do);
    }

    public Ast visitLet_decl(exprParser.Let_declContext ctx) {
        Ast declarations = ctx.getChild(1).accept(this);
        return new Let_decl(declarations);
    }

    public Ast visitLet_expr(exprParser.Let_exprContext ctx) {
        Ast declarations = ctx.getChild(1).accept(this);
        Ast instr_list = ctx.getChild(3).accept(this);

        return new Let_expr(declarations, instr_list);
    }

    public Ast visitDeclarations(exprParser.DeclarationsContext ctx){
        ArrayList<Ast> decl_list = new ArrayList<Ast>();

        for(int i=0; i<ctx.getChildCount(); i++) {
            decl_list.add(ctx.getChild(i).accept(this));
        }

        return new Declaration_list(decl_list);
    }

    public Ast visitInstr_list(exprParser.Instr_listContext ctx){
        ArrayList<Ast> instr_list = new ArrayList<Ast>();

        for(int i=0; i<ctx.getChildCount(); i+=2) {
            instr_list.add(ctx.getChild(i).accept(this));
        }

        return new Instruction_list(instr_list);
    }


    @Override
    public Ast visitDecl_list_var(exprParser.Decl_list_varContext ctx) {
        return ctx.getChild(0).accept(this);
    }
    @Override
    public Ast visitDecl_list_function(exprParser.Decl_list_functionContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    public Ast visitDecl_procedure(exprParser.Decl_procedureContext ctx) {
        Identifier identifier = new Identifier(ctx.getChild(1).getText());

        Ast field_list = null;
        if(ctx.getChildCount() > 5)
            field_list = ctx.getChild(3).accept(this);

        Ast instr = ctx.getChild(ctx.getChildCount()-1).accept(this);

        return new Decl_procedure(identifier, field_list, instr);
    }

    public Ast visitDecl_function(exprParser.Decl_functionContext ctx) {
        Identifier identifier = new Identifier(ctx.getChild(1).getText());

        Ast field_list = null;
        if(ctx.getChildCount() > 7)
            field_list = ctx.getChild(3).accept(this);

        Identifier typeID = new Identifier(ctx.getChild(ctx.getChildCount()-3).getText());
        Ast instr_list = ctx.getChild(ctx.getChildCount()-1).accept(this);

        return new Decl_function(identifier, field_list, typeID, instr_list);
    }

    @Override
    public Ast visitDecl_list_type(exprParser.Decl_list_typeContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    public Ast visitDecl_var_simple(exprParser.Decl_var_simpleContext ctx) {
        Identifier identifier = new Identifier(ctx.getChild(1).getText());
        Ast expr = ctx.getChild(3).accept(this);
        return new Decl_var_simple(identifier, expr);
    }

    public Ast visitDecl_var_base_type(exprParser.Decl_var_base_typeContext ctx) {

        Identifier identifier = new Identifier(ctx.getChild(1).getText());
        Identifier typeID = new Identifier(ctx.getChild(3).getText());
        Ast expr = ctx.getChild(5).accept(this);
        return new Decl_var_base_type(identifier, typeID, expr);
    }

    public Ast visitDecl_type(exprParser.Decl_typeContext ctx) {
        Identifier identifier = new Identifier(ctx.getChild(1).getText());
        Ast type = ctx.getChild(3).accept(this);

        return new Decl_type(identifier, type);
    }

    public Ast visitType_type_id(exprParser.Type_type_idContext ctx) {return new Identifier(ctx.getChild(0).getText());}

    public Ast visitType_array_of(exprParser.Type_array_ofContext ctx) {
        Identifier typeID = new Identifier(ctx.getChild(2).getText());

        return new Type_array_of(typeID);
    }

    public Ast visitType_record(exprParser.Type_recordContext ctx) {
        if(ctx.getChildCount() <= 2){
            return new Empty();
        }

        return ctx.getChild(1).accept(this);
    }




    public Ast visitField_list(exprParser.Field_listContext ctx) {
        ArrayList<Ast> field_list = new ArrayList<Ast>();
        for(int i=0; i<ctx.getChildCount(); i+=2){
            field_list.add(ctx.getChild(i).accept(this));
        }

        return new Field_list(field_list);
    }

    public Ast visitField_def(exprParser.Field_defContext ctx) {
        Identifier typeID = new Identifier(ctx.getChild(0).getText());
        Identifier identifier = new Identifier(ctx.getChild(2).getText());

        return new Field_def(typeID, identifier);
    }

    public Ast visitField_use(exprParser.Field_useContext ctx) {
        Identifier typeID = new Identifier(ctx.getChild(0).getText());
        Ast instr = new Identifier(ctx.getChild(2).getText());

        return new Field_use(typeID, instr);
    }



    public Ast visitBloc(exprParser.BlocContext ctx){return ctx.getChild(0).accept(this);}


    public Ast visitArray_new_array_of(exprParser.Array_new_array_ofContext ctx) {
        Identifier identifier = new Identifier(ctx.getChild(0).getText());
        Ast expr = ctx.getChild(2).accept(this);
        Ast array_type = ctx.getChild(5).accept(this);

        return new Array_new_array_of(identifier, expr, array_type);
    }

    public Ast visitRecord_new_record(exprParser.Record_new_recordContext ctx) {
        Identifier identifier = new Identifier(ctx.getChild(0).getText());

        Ast field_list = null;
        if(ctx.getChildCount() > 3)
            field_list = ctx.getChild(2).accept(this);

        return new Record_new_record(identifier, field_list);
    }

    public Ast visitAffectation_operation(exprParser.Affectation_operationContext ctx) {
        Ast lvalue = ctx.getChild(0).accept(this);
        Ast expr = ctx.getChild(2).accept(this);

        return new Affectation_operation(lvalue, expr);
    }

    public Ast visitNegate_expr(exprParser.Negate_exprContext ctx) {return ctx.getChild(1).accept(this);}

    public Ast visitFunction_call(exprParser.Function_callContext ctx) {
        Identifier identifier = new Identifier(ctx.getChild(0).getText());
        ArrayList<Ast> expression = new ArrayList<Ast>();
        if(ctx.getChildCount() > 3)
            for(int i=2; i<ctx.getChildCount()-1; i++)
                expression.add(ctx.getChild(i).accept(this));

        return new Function_call(identifier, expression);
    }

}
