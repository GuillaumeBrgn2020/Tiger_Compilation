grammar expr;

@header{
    package parser;
}

program :
        instr EOF                                                                      #Prgrm
    ;

instr :
        boolean_expr                                                                  #Expr_boolean_expr
    ;

// Priorite aux expressions booleennes
boolean_expr :
        binary_expr                                                                   #Boolean_expr_loop
    |   binary_expr ('&' | '|') instr                                                  #Boolean_expr_operation
    ;

// Gestion de la priorite de la multiplication 
binary_expr :
        add                                                                           #Binary_expr_loop
    |   add ('=' | '<>' | '>' | '<' | '<=' | '>=') instr                               #Binary_expr_operation
    ;

add :
        multiply                                                                      #Add_expr_loop
    |   multiply ('+' | '-') instr                                                     #Add_expr_operation
    ;

multiply :
        expression                                                                    #Multiply_expr_loop
    |   expression ('*' | '/') instr                                                   #Multiply_expr_operation
    ;


// Toutes les expressions possibles
expression :
        lValue                                                                        #Expression_lvalue
    |   ifThenElseExpr                                                                #Expression_if_then_else
    |   ifThenExpr                                                                    #Expression_if_then
    |   forExpr                                                                       #Expression_for
    |   whileExpr                                                                     #Expression_while
    |   letExpr                                                                       #Expression_let
    |   arrayDecla                                                                    #Expression_decl_array
    |   recordDecla                                                                   #Expression_decl_record
    |   affectation                                                                   #Expression_affectation
    |   '(' bloc ')'                                                                  #Expression_bloc
    |   negation                                                                      #Expression_negate
    |   callExpr                                                                      #Expression_function_call
    |   STRING_CONSTANT                                                               #Expression_string_constant
    |   INTEGER_CONSTANT                                                              #Expression_integer_constant
    ;

lValue :
        IDENTIFIER lValue_bis                                                         #Lvalue_id
    ;

lValue_bis :
        '.' IDENTIFIER lValue_bis                                                     #Lvalue_bis_attribute
    |   '[' instr ']' lValue_bis                                                      #Lvalue_bis_array_value
    |   /* Empty */                                                                   #Lvalue_bis_empty
    ;


ifThenExpr :
        'if' if_statement 'then' then_statement                                       #If_then
    ;

ifThenElseExpr :
        'if' if_statement 'then' then_statement 'else' else_statement                 #If_then_else
    ;

if_statement :
        instr
    ;

then_statement :
        instr
    ;

else_statement :
        instr
    ;

forExpr :
        'for' initialization 'to' termination 'do' instr                               #For
    ;

initialization :
        IDENTIFIER ':=' instr
    ;

termination :
        instr
    ;

whileExpr :
        'while' instr 'do' instr                                                        #While
    ;

letExpr :
        'let' declarations 'in' 'end'                                                   #Let_decl
    |   'let' declarations 'in' instr_list 'end'                                        #Let_expr
    ;

declarations :
        decl_list+
    ;

instr_list :
        instr
    |   instr (';' instr)+
    ;

decl_list :
        variableDecla                                                                   #Decl_list_var
    |   functionDecla                                                                   #Decl_list_function
    |   typeDecla                                                                       #Decl_list_type
    ;

variableDecla :
        'var' IDENTIFIER ':=' instr                                                     #Decl_var_simple
    |   'var' IDENTIFIER ':' IDENTIFIER ':=' instr                                      #Decl_var_base_type
    ;

functionDecla :
        'function' IDENTIFIER '(' field_list? ')' '=' instr_list                        #Decl_procedure
    |   'function' IDENTIFIER '(' field_list? ')' ':' IDENTIFIER '=' instr_list         #Decl_function
    ;

typeDecla :
        'type' IDENTIFIER '=' type                                                      #Decl_type
    ;

type :
        IDENTIFIER                                                                      #Type_type_id
    |   'array' 'of' IDENTIFIER                                                         #Type_array_of
    |   '{' field_list? '}'                                                             #Type_record
    ;

field_list :
        field_def (',' field_def)*
    |   field_use (',' field_use)*
    ;

field_def :
        IDENTIFIER ':' IDENTIFIER
    ;

field_use :
        IDENTIFIER '=' instr
    ;

arrayDecla :
        IDENTIFIER '[' instr ']' 'of' instr                                             #Array_new_array_of
    ;


recordDecla :
        IDENTIFIER '{' field_list? '}'                                                 #Record_new_record
    ;

affectation :
        lValue ':=' instr                                                               #Affectation_operation
    ;

negation :
        '-' instr                                                                       #Negate_expr
    ;

callExpr :
        IDENTIFIER '(' instr* ')'                                                       #Function_call
    ;

bloc :
        instr_list
    ;

STRING_CONSTANT :
        '"' ( ~[\r\n"] | (('!'..'~')|' ') )* '"'
    ;

INTEGER_CONSTANT :
        ('0'..'9')+
    ;

IDENTIFIER :
        (('a'..'z')|('A'..'Z'))
        (('a'..'z')|'_'|('A'..'Z')|('0'..'9'))*
    ;

COMMENT :
        '/*' .*? '*/' -> skip
    ;

LINE_COMMENT :
        '//' ~[\r\n]* -> skip
    ;

WS :   ('\n'|' '|'\t'|'\r')+ -> skip
    ;
