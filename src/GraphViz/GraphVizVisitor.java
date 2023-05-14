package GraphViz;

import AST.*;
import TDS.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GraphVizVisitor implements AstVisitor<String> {


    private ArrayList<Tds> tds;
    private Tds currentTds;

    private ArrayList<TdsElement> elements;
    private TdsElement currentElement;
    private ArrayList<String> operation;

    private boolean isInOperation = false;
    private boolean isDivision = false;

    private ArrayList<String> args;

    private ArrayList<String> affectations;
    private boolean isInOp = false;
    private boolean isFctCall = false;


    private boolean isInAffect = false;

    private ArrayList<String> existingTypes;

    private Map<String, ArrayList<String>> errors;


    private int state;
    private String nodeBuffer;
    private String linkBuffer;


    public GraphVizVisitor() {
        this.state = 0;
        this.nodeBuffer = "digraph \"ast\"{\n\n\tnodesep=1;\n\tranksep=1;\n\n";
        this.linkBuffer = "\n";

        tds = new ArrayList<>();
        currentTds = null;

        elements = new ArrayList<>();
        currentElement = null;

        existingTypes = new ArrayList<>();
        existingTypes.add("Integer");
        existingTypes.add("String");

        errors = new HashMap<>();
    }

    public void dumpGraph(String filepath) throws IOException {

        FileOutputStream output = new FileOutputStream(filepath);

        String buffer = this.nodeBuffer + this.linkBuffer;
        byte[] strToBytes = buffer.getBytes();

        output.write(strToBytes);

        output.close();

    }

    private String nextState() {
        int returnedState = this.state;
        this.state++;
        return "N" + returnedState;
    }


    private void addTransition(String from, String dest) {
        this.linkBuffer += String.format("\t%s -> %s; \n", from, dest);

    }

    private void addNode(String node, String label) {
        this.nodeBuffer += String.format("\t%s [label=\"%s\", shape=\"box\"];\n", node, label);

    }


    public void setCurrent(Tds tds) {
        this.tds.add(tds);
        this.currentTds = tds;
    }

    public void getBack() {
        this.currentTds = this.currentTds.parent;
    }


    public String visit(Identifier identifier) {
        String nodeIdentifier = this.nextState();

        if (this.currentElement != null) {
            switch (this.currentElement.getClass().getName()) {
                case "TDS.TdsVariable" -> {

                    if (this.existingTypes.contains(identifier.value))
                        ((TdsVariable) this.currentElement).setType(identifier.value);
                    else {
                        if (identifier.value.matches("-?\\d+")){
                            ((TdsVariable) this.currentElement).setType("Integer");
                        }
                        else
                            ((TdsVariable) this.currentElement).setType("String");
                    }

                    this.currentTds.tdsElements.add(
                            this.currentElement
                    );
                    this.currentElement = null;

                }
            }
        }

        if (this.isInOperation && !this.isDivision){
            this.operation.add(identifier.value);
        }


        if(this.isDivision) {
            try {
                int d = Integer.parseInt(identifier.value);
                this.operation.add(identifier.value);
            } catch (NumberFormatException e) {
            }
        }

        if (this.isInAffect){
            this.affectations.add(identifier.value);
        }

        this.addNode(nodeIdentifier, identifier.value);

        return nodeIdentifier;
    }

    public String visit(Declaration_list declaration_list) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "Declarations");

        ArrayList<String> decl_listState = new ArrayList<String>();
        for (Ast decl : declaration_list.decl_list)
            decl_listState.add(decl.accept(this));
        for (String state : decl_listState)
            this.addTransition(nodeIdentifier, state);

        return nodeIdentifier;
    }

    public String visit(Instruction_list instruction_list) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "Instructions");

        ArrayList<String> instr_listState = new ArrayList<String>();
        for (Ast instr : instruction_list.instr_list)
            instr_listState.add(instr.accept(this));
        for (String state : instr_listState)
            this.addTransition(nodeIdentifier, state);

        return nodeIdentifier;
    }


    public String visit(Prgrm prgrm) {
        String nodeIdentifier = this.nextState();

        Tds newTds = new Tds("root");
        this.setCurrent(newTds);

        TdsArgument tdsArgument = new TdsArgument("toPrint");
        ArrayList<TdsArgument> arrayListProcedure = new ArrayList<TdsArgument>();
        arrayListProcedure.add(tdsArgument);

        this.currentTds.tdsElements.add(new TdsProcedure("print", arrayListProcedure));


        String expr = prgrm.ast.accept(this);

        this.addNode(nodeIdentifier, "ROOT");
        this.addTransition(nodeIdentifier, expr);

        this.getBack();
        this.currentTds.print();

        for (Map.Entry<String, ArrayList<String>> entry : this.errors.entrySet())
            for (String s : entry.getValue()) {
                System.out.println(" # " + entry.getKey() + " ~ " + s);
            }
        return nodeIdentifier;
    }

    public String visit(Operation operation) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, operation.operator.value);

        this.operation = new ArrayList<String>();
        this.isInOperation = true;
        if (operation.operator.value.equals("/")){
            this.isDivision = true;
        }

        String left_exprState = operation.left_expr.accept(this);
        this.addTransition(nodeIdentifier, left_exprState);

        String right_exprState = operation.right_expr.accept(this);
        this.addTransition(nodeIdentifier, right_exprState);

        for (int i=0; i < this.operation.size() - 1 ; i++) {

        if (this.operation.size() == 2) {
            String typeGauche = this.currentTds.getTypeVar(this.operation.get(i));
            String typeDroit = this.currentTds.getTypeVar(this.operation.get(i+1));

            if (typeGauche == null) {
                if (this.operation.get(i).matches("-?\\d+")) {
                    typeGauche = "Integer";
                } else {
                    typeGauche = "String";
                }
            }

            if (typeDroit == null) {
                if (this.operation.get(i + 1).matches("-?\\d+")) {
                    typeDroit = "Integer";
                } else {
                    typeDroit = "String";
                }
            }

            if (typeGauche != typeDroit) {
                this.errors.putIfAbsent("Operation",
                        new ArrayList<String>());
                this.errors.get("Operation").add(
                        ("Type gauche et type droit differents : " + typeGauche + " et " + typeDroit + " dans "+ this.operation.get(0) + operation.operator.value + this.operation.get(1)));
            }
        }
            if (operation.operator.value.equals("/")){
                try {
                    int divisor = Integer.parseInt(this.operation.get(1));
                    if (divisor == 0){
                        this.errors.putIfAbsent("Operation",
                                new ArrayList<String>());
                        this.errors.get("Operation").add(
                                ("Division par 0 pour : " + this.operation.get(0)) + "/0" );
                    }
                }
                catch (NumberFormatException e){}
            }
            this.isInOperation = false;
        }


        return nodeIdentifier;
    }

    public String visit(Lvalue_id lvalue_id) {

        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, lvalue_id.identifier.value);

        if (this.isInOperation && this.currentTds.containVarName(lvalue_id.identifier.value)) {
            this.operation.add(lvalue_id.identifier.value);
        }

        if (!(this.currentTds.containVarName(lvalue_id.identifier.value) || (this.currentTds.containArgName(lvalue_id.identifier.value)))){
            this.errors.putIfAbsent("lvalue_id Var",
                    new ArrayList<String>());
            this.errors.get("lvalue_id Var").add(
                    ("Variable non déclarée avant utilisation : " + lvalue_id.identifier.value));
        }

        if(this.isFctCall){
            this.args.add(lvalue_id.identifier.value);
        }


        if (this.isInAffect){
            this.affectations.add(lvalue_id.identifier.value);
        }


        if (!lvalue_id.lValue_bis.getClass().getName().equals("AST.Empty")) {
            String lvalue_bisState = lvalue_id.lValue_bis.accept(this);
            this.addTransition(nodeIdentifier, lvalue_bisState);
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Lvalue_bis_attribute lvalue_bis_attribute) {
        String nodeIdentifier = this.nextState();
        String identifierState = lvalue_bis_attribute.identifier.accept(this);

        this.addNode(nodeIdentifier, ".");
        this.addTransition(nodeIdentifier, identifierState);

        if (!lvalue_bis_attribute.lValue_bis.getClass().getName().equals("AST.Empty")) {
            String lvalue_bisState = lvalue_bis_attribute.lValue_bis.accept(this);
            this.addTransition(nodeIdentifier, lvalue_bisState);
        }

        return nodeIdentifier;
    }

    public String visit(Lvalue_bis_array_value lvalue_bis_array_value) {
        String nodeIdentifier = this.nextState();
        String exprState = lvalue_bis_array_value.expr.accept(this);

        this.addNode(nodeIdentifier, "[ ]");
        this.addTransition(nodeIdentifier, exprState);

        if (!lvalue_bis_array_value.lValue_bis.getClass().getName().equals("AST.Empty")) {
            String lvalue_bisState = lvalue_bis_array_value.lValue_bis.accept(this);
            this.addTransition(nodeIdentifier, lvalue_bisState);
        }

        return nodeIdentifier;
    }

    public String visit(If_then if_then) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "Condition");

        String expr_ifState = if_then.expr_if.accept(this);
        this.addTransition(nodeIdentifier, expr_ifState);

        String expr_thenState = if_then.expr_then.accept(this);
        this.addTransition(nodeIdentifier, expr_thenState);

        return nodeIdentifier;
    }

    public String visit(If_then_else if_then_else) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "Condition");

        String expr_ifState = if_then_else.expr_if.accept(this);
        this.addTransition(nodeIdentifier, expr_ifState);

        String expr_thenState = if_then_else.expr_then.accept(this);
        this.addTransition(nodeIdentifier, expr_thenState);

        String expr_elseState = if_then_else.expr_else.accept(this);
        this.addTransition(nodeIdentifier, expr_elseState);

        return nodeIdentifier;
    }

    public String visit(If_statement if_statement) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "If");
        String exprState = if_statement.expr.accept(this);
        this.addTransition(nodeIdentifier, exprState);

        return nodeIdentifier;
    }

    public String visit(Then_statement then_statement) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "Then");
        String exprState = then_statement.expr.accept(this);
        this.addTransition(nodeIdentifier, exprState);

        return nodeIdentifier;
    }

    public String visit(Else_statement else_statement) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "Else");
        String exprState = else_statement.expr.accept(this);
        this.addTransition(nodeIdentifier, exprState);

        return nodeIdentifier;
    }

    public String visit(For _for) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "for");

        this.currentElement = null;

        String initializationState = _for.initialization.accept(this);
        this.addTransition(nodeIdentifier, initializationState);

        this.currentTds.tdsElements.add(currentElement);

        TdsVariable tmp = (TdsVariable) currentElement;

        String terminationState = _for.termination.accept(this);
        this.addTransition(nodeIdentifier, terminationState);

        String instrState = _for.instr_list.accept(this);
        this.addTransition(nodeIdentifier, instrState);

        this.currentTds.tdsElements.remove(tmp);

        return nodeIdentifier;
    }

    public String visit(Initialization initialization) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "Initialization");

        String identifierState = initialization.identifier.accept(this);
        this.addTransition(nodeIdentifier, identifierState);

        String instrState = initialization.instr.accept(this);
        this.addTransition(nodeIdentifier, instrState);

        this.currentElement = new TdsVariable(initialization.identifier.value);

        return nodeIdentifier;
    }

    public String visit(Termination termination) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "Termination");

        String instrState = termination.instr.accept(this);
        this.addTransition(nodeIdentifier, instrState);

        return nodeIdentifier;
    }

    public String visit(While while_) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "while");

        // TODO : middleNode
        //String stopStatementNodeIdentifier = this.addMiddleNode(nodeIdentifier, "Stop Statement");
        //String expr_whileState = while_.expr_while.accept(this);
        //this.addTransition(stopStatementNodeIdentifier, expr_whileState);

        String expr_doState = while_.expr_do.accept(this);
        this.addTransition(nodeIdentifier, expr_doState);

        return nodeIdentifier;
    }

    public String visit(Let_decl let_decl) {
        String nodeIdentifier = this.nextState();

        Tds newTds = new Tds(this.currentTds, "Let");
        this.setCurrent(newTds);

        this.addNode(nodeIdentifier, "Let");

        String declarationsState = let_decl.declarations.accept(this);
        this.addTransition(nodeIdentifier, declarationsState);

        return nodeIdentifier;
    }

    public String visit(Let_expr let_expr) {
        String nodeIdentifier = this.nextState();

        Tds newTds = new Tds(this.currentTds, "Let");
        this.setCurrent(newTds);

        this.addNode(nodeIdentifier, "Let");

        String declarationsState = let_expr.declarations.accept(this);
        this.addTransition(nodeIdentifier, declarationsState);

        String instr_listState = let_expr.instr_list.accept(this);
        this.addTransition(nodeIdentifier, instr_listState);

        return nodeIdentifier;
    }

    public String visit(Decl_var_simple decl_var_simple) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "new Var");

        String identifierState = decl_var_simple.identifier.accept(this);
        this.addTransition(nodeIdentifier, identifierState);

        if (this.currentTds.containVarName(decl_var_simple.identifier.value)) {
            this.errors.putIfAbsent("Decl Var Simple",
                    new ArrayList<String>());
            this.errors.get("Decl Var Simple").add(
                    ("Double déclaration de variable pour : " + decl_var_simple.identifier.value));
        }

        if (this.currentTds.containFunctionName(decl_var_simple.identifier.value) || this.currentTds.containProcedureName(decl_var_simple.identifier.value)){
            this.errors.putIfAbsent("Decl Var Simple Fonction",
                    new ArrayList<String>());
            this.errors.get("Decl Var Simple Fonction").add(
                    ("Le nom choisi est deja utilisé pour une fonction/procédure: " + decl_var_simple.identifier.value));
        }

        this.currentElement = new TdsVariable(
                decl_var_simple.identifier.value
        );

        String expr_affectState = decl_var_simple.expr.accept(this);
        this.addTransition(nodeIdentifier, expr_affectState);

        /*
        this.currentTds.tdsElements.add(
                this.currentElement
        );

        this.currentElement = null;

         */

        return nodeIdentifier;
    }

    public String visit(Decl_var_base_type decl_var_base_type) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "new Var");

        String identifierState = decl_var_base_type.identifier.accept(this);
        this.addTransition(nodeIdentifier, identifierState);


        if (this.currentTds.containVarName(decl_var_base_type.identifier.value)) {
            this.errors.putIfAbsent("Decl Var Type",
                    new ArrayList<String>());
            this.errors.get("Decl Var Type").add(
                    ("Double déclaration de : " + decl_var_base_type.identifier.value));
        }

        String typeIDState = decl_var_base_type.typeID.accept(this);
        this.addTransition(nodeIdentifier, typeIDState);


        if (!(this.currentTds.containTypeName(decl_var_base_type.typeID.value))){
            this.errors.putIfAbsent("Decl Var Type Absent",
                    new ArrayList<String>());
            this.errors.get("Decl Var Type Absent").add(
                    ("Type non déclaré avant utilisation : " + decl_var_base_type.typeID.value));
        }

        if (this.currentTds.containFunctionName(decl_var_base_type.identifier.value)){
            this.errors.putIfAbsent("Decl Var Type Fonction",
                    new ArrayList<String>());
            this.errors.get("Decl Var Type Fonction").add(
                    ("Le nom choisi est deja utilisé pour une fonction/procédure: " + decl_var_base_type.identifier.value));
        }

        this.currentTds.tdsElements.add(
                new TdsVariable(
                        decl_var_base_type.identifier.value,
                        decl_var_base_type.typeID.value
                )
        );

        String expr_affectState = decl_var_base_type.expr.accept(this);
        this.addTransition(nodeIdentifier, expr_affectState);


        return nodeIdentifier;
    }

    public String visit(Decl_procedure decl_procedure) {
        String nodeIdentifier = this.nextState();

        Tds newTds = new Tds(this.currentTds, decl_procedure.identifier.value);
        this.setCurrent(newTds);

        ArrayList<TdsArgument> tdsArguments = new ArrayList<>();

        this.addNode(nodeIdentifier, "new Procedure");

        String identifierState = decl_procedure.identifier.accept(this);
        this.addTransition(nodeIdentifier, identifierState);

        if (this.currentTds.containProcedureName(decl_procedure.identifier.value)) {
            this.errors.putIfAbsent("Decl Procedure",
                    new ArrayList<String>());
            this.errors.get("Decl Procedure").add(
                    ("Double déclaration de procédure pour : " + decl_procedure.identifier.value));
        }

        if (this.currentTds.containVarName(decl_procedure.identifier.value)){
            this.errors.putIfAbsent("Decl Var Type Procedure",
                    new ArrayList<String>());
            this.errors.get("Decl Var Type Procedure").add(
                    ("Le nom choisi est deja utilisé pour une variable " + decl_procedure.identifier.value));
        }


        if (decl_procedure.field_list != null) {
            String field_listState = decl_procedure.field_list.accept(this);
            this.addTransition(nodeIdentifier, field_listState);

            for (TdsElement element : this.elements){
                tdsArguments.add((TdsArgument) element);
            }
                
            for (int i=0 ; i<this.elements.size()-1 ; i++){
                for (int j=i+1 ; j<this.elements.size(); j++){
                    if (((TdsArgument)this.elements.get(i)).getType().equals(((TdsArgument)this.elements.get(j)).getType())){
                        this.errors.putIfAbsent("Decl Procedure ",
                            new ArrayList<String>());
                        this.errors.get("Decl Procedure").add(
                            ("Deux arguments de meme nom : " + ((TdsArgument)this.elements.get(i)).getName()));
                    }
                }
            }

        }

        this.currentTds.parent.tdsElements.add(new TdsProcedure(
                decl_procedure.identifier.value,
                tdsArguments
        ));

        String instr_listState = decl_procedure.instr_list.accept(this);
        this.addTransition(nodeIdentifier, instr_listState);


        this.getBack();

        return nodeIdentifier;
    }

    public String visit(Decl_function decl_function) {
        String nodeIdentifier = this.nextState();

        Tds newTds = new Tds(this.currentTds, decl_function.identifier.value);
        this.setCurrent(newTds);

        ArrayList<TdsArgument> tdsArguments = new ArrayList<>();

        this.addNode(nodeIdentifier, "new Function");

        String identifierState = decl_function.identifier.accept(this);
        this.addTransition(nodeIdentifier, identifierState);

        if (this.currentTds.containFunctionName(decl_function.identifier.value)) {
            this.errors.putIfAbsent("Decl Function",
                    new ArrayList<String>());
            this.errors.get("Decl Function").add(
                    ("Double déclaration de fonction pour : " + decl_function.identifier.value));
        }

        if (this.currentTds.containVarName(decl_function.identifier.value) && this.currentTds.containProcedureName(decl_function.identifier.value)){
            this.errors.putIfAbsent("Decl Var Type Fonction",
                    new ArrayList<String>());
            this.errors.get("Decl Var Type Fonction").add(
                    ("Le nom choisi est deja utilisé pour une variable " + decl_function.identifier.value));
        }

        if (decl_function.field_list != null) {
            String field_listState = decl_function.field_list.accept(this);
            this.addTransition(nodeIdentifier, field_listState);

            for (TdsElement element : this.elements)
                tdsArguments.add((TdsArgument) element);
        }

        for (int i=0 ; i<this.elements.size()-1 ; i++){
            for (int j=i+1 ; j<this.elements.size(); j++){
                if (((TdsArgument)this.elements.get(i)).getType().equals(((TdsArgument)this.elements.get(j)).getType())){
                    this.errors.putIfAbsent("Decl Fonction",
                        new ArrayList<String>());
                    this.errors.get("Decl Fonction").add(
                        ("Deux arguments de meme nom : " + ((TdsArgument)this.elements.get(i)).getName()));
                }
            }
        }

        String typeIDState = decl_function.typeID.accept(this);
        this.addTransition(nodeIdentifier, typeIDState);

        this.currentTds.parent.tdsElements.add(new TdsFonction(
                decl_function.identifier.value,
                decl_function.typeID.value,
                tdsArguments
        ));




        String instr_listState = decl_function.instr_list.accept(this);
        this.addTransition(nodeIdentifier, instr_listState);

        this.getBack();

        return nodeIdentifier;
    }

    public String visit(Decl_type decl_type) {
        String nodeIdentifier = this.nextState();

        ArrayList<TdsArgument> arguments = new ArrayList<>();

        String identifierState = decl_type.identifier.accept(this);

        this.addNode(nodeIdentifier, "new Type");
        this.addTransition(nodeIdentifier, identifierState);

        String typeState = decl_type.type.accept(this);
        this.addTransition(nodeIdentifier, typeState);

        if (this.currentTds.containTypeName(decl_type.identifier.value)) {
            this.errors.putIfAbsent("Decl Type",
                    new ArrayList<String>());
            this.errors.get("Decl Type").add(
                    ("Double déclaration de type pour : " + decl_type.identifier.value));
        }

        if (decl_type.type.getClass().getName().equals("AST.Type_array_of")) {
            this.currentTds.tdsElements.add(
                    new TdsType(
                            decl_type.identifier.value,
                            "array of "
                    )
            );

        } else if (decl_type.type.getClass().getName().equals("AST.Field_list")) {
            for (TdsElement element : this.elements)
                arguments.add((TdsArgument) element);

            this.currentTds.tdsElements.add(
                    new TdsType(
                            decl_type.identifier.value,
                            arguments
                    )
            );
        } else {
            this.currentTds.tdsElements.add(
                    new TdsType(
                            decl_type.identifier.value,
                            "debug "
                    )
            );
        }

        if (!this.existingTypes.contains(decl_type.identifier.value))
            this.existingTypes.add(decl_type.identifier.value);

        /* if (this.currentTds.containTypeName(decl_type.identifier.value)){
            this.errors.put("Decl Type", "Double déclaration de type de : " + decl_type.identifier.value);
        } */


        return nodeIdentifier;
    }

    public String visit(Field_list field_list) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "Field list");

        this.elements.clear();

        ArrayList<String> field_listState = new ArrayList<String>();
        for (Ast field : field_list.field_list)
            field_listState.add(field.accept(this));

        for (String state : field_listState)
            this.addTransition(nodeIdentifier, state);

        return nodeIdentifier;
    }

    public String visit(Field_def field_def) {
        String nodeIdentifier = this.nextState();

        this.elements.add(
                new TdsArgument(
                        field_def.identifier.value,
                        field_def.typeID.value
                )
        );

        this.addNode(nodeIdentifier, "Field");

        String identifierState = field_def.identifier.accept(this);
        this.addTransition(nodeIdentifier, identifierState);

        String typeIDState = field_def.typeID.accept(this);
        this.addTransition(nodeIdentifier, typeIDState);

        this.currentTds.tdsElements.add(
                new TdsArgument(
                        field_def.identifier.value,
                        field_def.typeID.value
                )
        );

        return nodeIdentifier;
    }

    public String visit(Field_use field_use) {
        isFctCall = true;
        String nodeIdentifier = this.nextState();

        this.elements.add(
                new TdsArgument(
                        field_use.identifier.value
                )
        );

        this.addNode(nodeIdentifier, "Field");

        String identifierState = field_use.identifier.accept(this);
        this.addTransition(nodeIdentifier, identifierState);

        String instrState = field_use.instr.accept(this);
        this.addTransition(nodeIdentifier, instrState);

        this.currentTds.tdsElements.add(
                new TdsArgument(
                        field_use.identifier.value
                )
        );

        isFctCall = false;
        return nodeIdentifier;
    }


    public String visit(Array_new_array_of array_new_array_of) {
        String nodeIdentifier = this.nextState();

        String identifierState = array_new_array_of.identifier.accept(this);
        String exprState = array_new_array_of.expr.accept(this);
        String array_typeState = array_new_array_of.array_type.accept(this);

        this.addNode(nodeIdentifier, "new Array");
        this.addTransition(nodeIdentifier, identifierState);
        this.addTransition(nodeIdentifier, exprState);
        this.addTransition(nodeIdentifier, array_typeState);

        return nodeIdentifier;
    }

    public String visit(Record_new_record record_new_record) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "new Record");

        String identifierState = record_new_record.identifier.accept(this);
        this.addTransition(nodeIdentifier, identifierState);

        if (record_new_record.field_list != null) {
            String field_listState = record_new_record.field_list.accept(this);
            this.addTransition(nodeIdentifier, field_listState);
        }

        return nodeIdentifier;
    }

    public String visit(Affectation_operation affectation_operation) {

        this.affectations = new ArrayList<String>();
        this.isInAffect = true;

        this.operation = new ArrayList<String>();
        this.isInOperation = true;

        String nodeIdentifier = this.nextState();

        String lvalueState = affectation_operation.lvalue.accept(this);
        String exprState = affectation_operation.expr.accept(this);

 /*       for (int i=0; i < operation.size() - 1 ; i++) {
            String typeGauche = null;
            String typeDroit = null;
            if (this.operation.size() == 2) {
                typeDroit = this.currentTds.getTypeVar(this.operation.get(0));
                typeGauche = this.currentTds.getTypeVar(this.operation.get(1));

                if (typeDroit == null) {
                    if (operation.get(i + 1).matches("-?\\d+")) {
                        typeDroit = "Integer";
                    } else {
                        typeDroit = "String";
                    }
                }
            }
            if (typeGauche != typeDroit) {
                this.errors.putIfAbsent("Operation",
                        new ArrayList<String>());
                this.errors.get("Operation").add(
                        ("Type gauche et type droit differents : " + typeGauche + " et " + typeDroit));
            }
        }*/

        this.addNode(nodeIdentifier, "Affectation");
        this.addTransition(nodeIdentifier, lvalueState);
        this.addTransition(nodeIdentifier, exprState);

        for (int i=0; i < affectations.size() - 1 ; i++) {
            if (affectations.size() == 2){
                String type_gauche = this.currentTds.getTypeVar(affectations.get(i));
                String type_droit = this.currentTds.getTypeVar(affectations.get(i+1));

                if (type_droit == null){
                    if (affectations.get(i+1).matches("-?\\d+")){
                        type_droit = "Integer";
                    }
                    else {
                        type_droit = "String";
                    }
                }


                if(type_droit != type_gauche){
                        this.errors.putIfAbsent("Affect type",
                                new ArrayList<String>());
                        this.errors.get("Affect type").add(
                                ("Affect type : " + type_gauche + " :  " + type_droit + " pour " + affectations.get(i) + " et "  + affectations.get(i+1)));
                    }
                }
        }

        this.isInAffect = false;
        return nodeIdentifier;
    }

    public String visit(Function_call function_call) {

        this.args = new ArrayList<>();

        isFctCall = true;

        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "Call");

        String identifierState = function_call.identifier.accept(this);
        this.addTransition(nodeIdentifier, identifierState);



        ArrayList<String> expression = new ArrayList<String>();
        for (Ast ast : function_call.expression)
            expression.add(ast.accept(this));

        if (function_call.expression.size() != this.currentTds.getNbParamFonction(function_call.identifier.value)
                && (this.currentTds.containFunctionName(function_call.identifier.value)
                || this.currentTds.containProcedureName(function_call.identifier.value)) ) {

            this.errors.putIfAbsent("Function_call",
                    new ArrayList<String>());
            this.errors.get("Function_call").add(
                    ("Le nombre de param n'est pas le bon " +
                            function_call.identifier.value + " Obtenu : " +
                            function_call.expression.size() + " Attendu : " +
                            this.currentTds.getNbParamFonction(function_call.identifier.value )));
        }


        if (function_call.expression.size() != 0 && this.args.size() != 0){
            for (String arg : args){
                if (!this.currentTds.containVarName(arg) && (!this.currentTds.containArgName(arg))){
                    this.errors.putIfAbsent("Function_call Param",
                            new ArrayList<String>());
                    this.errors.get("Function_call Param").add(
                            ("Le param n'existe pas : " +
                                    arg));
                }
            }
        }

        if (!(this.currentTds.containProcedureName(function_call.identifier.value)) && (!(this.currentTds.containFunctionName(function_call.identifier.value)))){
            this.errors.putIfAbsent("Decl Fonction/Procedure Absent",
                    new ArrayList<String>());
            this.errors.get("Decl Fonction/Procedure Absent").add(
                    ("Fonction non déclarée avant utilisation : " + function_call.identifier.value));
        }


        for (String state : expression)
            this.addTransition(nodeIdentifier, state);


        return nodeIdentifier;
    }



    public String visit(Type_array_of type_array_of){
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "array of");

        String typeIDState = type_array_of.typeID.accept(this);
        this.addTransition(nodeIdentifier, typeIDState);

        return nodeIdentifier;
    }
}
