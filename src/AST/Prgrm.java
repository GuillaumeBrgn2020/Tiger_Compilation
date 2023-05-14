package AST;

public class Prgrm implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public Ast ast;

    public Prgrm(Ast ast){
        this.ast = ast;
    }

}
    
