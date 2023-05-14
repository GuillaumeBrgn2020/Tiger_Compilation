package AST;

public interface Ast {
    public <T> T accept(AstVisitor<T> visitor);
}
