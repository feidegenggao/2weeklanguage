import stone.ast.ASTree;
import stone.*;

public class ParserRunner {
    public static void main(String[] args) throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        BasicParser bp = new BasicParser();
        Token t = l.peek(0);
        while (t != Token.EOF) {
            ASTree ast = bp.parse(l);
            print("Token:" + t.getText() + ", at :" + t.getLineNumber());
            print("ast:" + ast.getClass().getSimpleName());
            print("=> " + ast.toString());
            t = l.peek(0);
        }
    }

    private static void print(String s) {
        System.out.println(s);
    }
}
