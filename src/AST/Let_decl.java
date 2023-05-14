package AST;

import java.util.ArrayList;

public class Let_decl implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Ast declarations;
    public Let_decl(Ast declarations){
        this.declarations = declarations;
    }
}
