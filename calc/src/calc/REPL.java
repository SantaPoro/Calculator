package calc;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 *  Calculator program
 *
 *  Read Eval Print Loop for Calculator (a command line)
 *
 *  To use the calculator run this.
 *
 */
class REPL {

    public static void main(String[] args) {
        new REPL().program();
    }

    final Scanner scan = new Scanner(in);
    final Calculator calculator = new Calculator();

    void program() {

        while (true) {
            out.print("> ");
            String input = scan.nextLine();
            try {
                double result = calculator.eval(input);
                out.println(result);
            }catch( Exception e){
                out.println(e.getMessage());
            }
        }
    }


}
