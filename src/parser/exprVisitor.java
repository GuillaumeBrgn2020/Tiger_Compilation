// Generated from expr.g4 by ANTLR 4.9.2

    package parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link exprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface exprVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code Prgrm}
	 * labeled alternative in {@link exprParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrgrm(exprParser.PrgrmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Expr_boolean_expr}
	 * labeled alternative in {@link exprParser#instr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_boolean_expr(exprParser.Expr_boolean_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Boolean_expr_loop}
	 * labeled alternative in {@link exprParser#boolean_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_expr_loop(exprParser.Boolean_expr_loopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Boolean_expr_operation}
	 * labeled alternative in {@link exprParser#boolean_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_expr_operation(exprParser.Boolean_expr_operationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Binary_expr_loop}
	 * labeled alternative in {@link exprParser#binary_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_expr_loop(exprParser.Binary_expr_loopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Binary_expr_operation}
	 * labeled alternative in {@link exprParser#binary_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_expr_operation(exprParser.Binary_expr_operationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Add_expr_loop}
	 * labeled alternative in {@link exprParser#add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd_expr_loop(exprParser.Add_expr_loopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Add_expr_operation}
	 * labeled alternative in {@link exprParser#add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd_expr_operation(exprParser.Add_expr_operationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Multiply_expr_loop}
	 * labeled alternative in {@link exprParser#multiply}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiply_expr_loop(exprParser.Multiply_expr_loopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Multiply_expr_operation}
	 * labeled alternative in {@link exprParser#multiply}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiply_expr_operation(exprParser.Multiply_expr_operationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Expression_lvalue}
	 * labeled alternative in {@link exprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_lvalue(exprParser.Expression_lvalueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Expression_if_then_else}
	 * labeled alternative in {@link exprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_if_then_else(exprParser.Expression_if_then_elseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Expression_if_then}
	 * labeled alternative in {@link exprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_if_then(exprParser.Expression_if_thenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Expression_for}
	 * labeled alternative in {@link exprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_for(exprParser.Expression_forContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Expression_while}
	 * labeled alternative in {@link exprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_while(exprParser.Expression_whileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Expression_let}
	 * labeled alternative in {@link exprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_let(exprParser.Expression_letContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Expression_decl_array}
	 * labeled alternative in {@link exprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_decl_array(exprParser.Expression_decl_arrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Expression_decl_record}
	 * labeled alternative in {@link exprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_decl_record(exprParser.Expression_decl_recordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Expression_affectation}
	 * labeled alternative in {@link exprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_affectation(exprParser.Expression_affectationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Expression_bloc}
	 * labeled alternative in {@link exprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_bloc(exprParser.Expression_blocContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Expression_negate}
	 * labeled alternative in {@link exprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_negate(exprParser.Expression_negateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Expression_function_call}
	 * labeled alternative in {@link exprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_function_call(exprParser.Expression_function_callContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Expression_string_constant}
	 * labeled alternative in {@link exprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_string_constant(exprParser.Expression_string_constantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Expression_integer_constant}
	 * labeled alternative in {@link exprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_integer_constant(exprParser.Expression_integer_constantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Lvalue_id}
	 * labeled alternative in {@link exprParser#lValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLvalue_id(exprParser.Lvalue_idContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Lvalue_bis_attribute}
	 * labeled alternative in {@link exprParser#lValue_bis}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLvalue_bis_attribute(exprParser.Lvalue_bis_attributeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Lvalue_bis_array_value}
	 * labeled alternative in {@link exprParser#lValue_bis}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLvalue_bis_array_value(exprParser.Lvalue_bis_array_valueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Lvalue_bis_empty}
	 * labeled alternative in {@link exprParser#lValue_bis}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLvalue_bis_empty(exprParser.Lvalue_bis_emptyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code If_then}
	 * labeled alternative in {@link exprParser#ifThenExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_then(exprParser.If_thenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code If_then_else}
	 * labeled alternative in {@link exprParser#ifThenElseExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_then_else(exprParser.If_then_elseContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#if_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_statement(exprParser.If_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#then_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThen_statement(exprParser.Then_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#else_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_statement(exprParser.Else_statementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code For}
	 * labeled alternative in {@link exprParser#forExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor(exprParser.ForContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#initialization}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitialization(exprParser.InitializationContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#termination}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermination(exprParser.TerminationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code While}
	 * labeled alternative in {@link exprParser#whileExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile(exprParser.WhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Let_decl}
	 * labeled alternative in {@link exprParser#letExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet_decl(exprParser.Let_declContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Let_expr}
	 * labeled alternative in {@link exprParser#letExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet_expr(exprParser.Let_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#declarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarations(exprParser.DeclarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#instr_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstr_list(exprParser.Instr_listContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Decl_list_var}
	 * labeled alternative in {@link exprParser#decl_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl_list_var(exprParser.Decl_list_varContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Decl_list_function}
	 * labeled alternative in {@link exprParser#decl_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl_list_function(exprParser.Decl_list_functionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Decl_list_type}
	 * labeled alternative in {@link exprParser#decl_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl_list_type(exprParser.Decl_list_typeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Decl_var_simple}
	 * labeled alternative in {@link exprParser#variableDecla}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl_var_simple(exprParser.Decl_var_simpleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Decl_var_base_type}
	 * labeled alternative in {@link exprParser#variableDecla}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl_var_base_type(exprParser.Decl_var_base_typeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Decl_procedure}
	 * labeled alternative in {@link exprParser#functionDecla}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl_procedure(exprParser.Decl_procedureContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Decl_function}
	 * labeled alternative in {@link exprParser#functionDecla}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl_function(exprParser.Decl_functionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Decl_type}
	 * labeled alternative in {@link exprParser#typeDecla}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl_type(exprParser.Decl_typeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Type_type_id}
	 * labeled alternative in {@link exprParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_type_id(exprParser.Type_type_idContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Type_array_of}
	 * labeled alternative in {@link exprParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_array_of(exprParser.Type_array_ofContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Type_record}
	 * labeled alternative in {@link exprParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_record(exprParser.Type_recordContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#field_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_list(exprParser.Field_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#field_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_def(exprParser.Field_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#field_use}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_use(exprParser.Field_useContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Array_new_array_of}
	 * labeled alternative in {@link exprParser#arrayDecla}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_new_array_of(exprParser.Array_new_array_ofContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Record_new_record}
	 * labeled alternative in {@link exprParser#recordDecla}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecord_new_record(exprParser.Record_new_recordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Affectation_operation}
	 * labeled alternative in {@link exprParser#affectation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAffectation_operation(exprParser.Affectation_operationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Negate_expr}
	 * labeled alternative in {@link exprParser#negation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegate_expr(exprParser.Negate_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Function_call}
	 * labeled alternative in {@link exprParser#callExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_call(exprParser.Function_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#bloc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloc(exprParser.BlocContext ctx);
}