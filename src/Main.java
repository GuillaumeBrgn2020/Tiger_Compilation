import AST.Ast;
import AST.AstCreator;
import GraphViz.GraphVizVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import parser.exprLexer;
import parser.exprParser;
import parser.exprParser.ProgramContext;

import java.io.IOException;

public class Main {

    public static void main(String[] args){

        args = new String[1];


        //args[0] = "C:\\Users\\pauln\\Documents\\Telecom_Nancy\\2A\\S7\\PCL\\nicola156u\\examples\\8queens_controles_paul.exp";


        //args[0] = "C:\\Users\\louen\\IdeaProjects\\nicola156u\\examples\\good_sujet_8queens.exp";
        //args[0] = "C:\\Users\\louen\\IdeaProjects\\nicola156u\\examples\\good_guillaume_while_sans_ifthenelse.txt";
        //args[0] = "C:\\Users\\louen\\IdeaProjects\\nicola156u\\examples\\good_guillaume_declaration_variables.exp";
        //args[0] = "C:\\Users\\lou en\\IdeaProjects\\nicola156u\\examples\\good_guillaume_if.exp";
        //args[0] = "C:\\Users\\louen\\IdeaProjects\\nicola156u\\examples\\good_louen_let.exp";
        //args[0] = "C:\\Users\\louen\\IdeaProjects\\nicola156u\\examples\\good_louen_new_type.exp";
        //args[0] = "C:\\Users\\louen\\IdeaProjects\\nicola156u\\examples\\good_louen_boolean_expr";
        //args[0] = "examples\\good_sujet_8queens_controls_guillaume.exp";
        //args[0] = "examples\\good_guillaume_declaration_variables.exp";
        args[0] = "examples\\8queens_soutenance.exp";

        if (args.length < 1){
            System.out.println("Error : Expected 1 argument filepath, found 0");
            return;
        }

        String testFile = args[0];

        try {

            //chargement du fichier et construction du parser
            CharStream input = CharStreams.fromFileName(testFile);
            exprLexer lexer = new exprLexer(input); 
            CommonTokenStream stream = new CommonTokenStream(lexer);
            exprParser parser = new exprParser(stream);

            ProgramContext program = parser.program();



            // code d'affichage de l'arbre syntaxique
            /*
            JFrame frame = new JFrame("Antlr AST");
            JPanel panel = new JPanel();
            TreeViewer viewer = new TreeViewer(Arrays.asList(
                    parser.getRuleNames()),program);
            viewer.setScale(1.5); // Scale a little
            panel.add(viewer);
            frame.add(panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
             */



            // code d'affichage de l'AST


            AstCreator creator = new AstCreator();
            Ast ast = program.accept(creator);

            //Visiteur pour la representation graphique + appel
            GraphVizVisitor graphViz = new GraphVizVisitor();
            ast.accept(graphViz);

            graphViz.dumpGraph("./out/tree.dot");

        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (RecognitionException e) {
            e.printStackTrace();
        }
        

    }
    
}