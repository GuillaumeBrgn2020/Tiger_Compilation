package AST;

import java.util.ArrayList;

public class Function_call implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Identifier identifier;
    public ArrayList<Ast> expression;
    public Function_call(Identifier identifier, ArrayList<Ast> expression){
        this.identifier = identifier;
        this.expression = expression;
    }
}
