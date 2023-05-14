package AST;

import java.util.ArrayList;

public class Instruction_list implements Ast{
    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public ArrayList<Ast> instr_list;
    public Instruction_list(ArrayList<Ast> instr_list){
        this.instr_list = instr_list;
    }
}
