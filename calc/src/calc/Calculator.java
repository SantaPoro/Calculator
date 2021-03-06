package calc;

import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Double.NaN;
import static java.lang.Math.pow;

import java.util.Arrays;
import java.util.ArrayList;


/*
 *   A calculator for rather simple arithmetic expressions
 *
 *   This is not the program, it's a class declaration (with methods) in it's
 *   own file (which must be named Calculator.java)
 *
 *   NOTE:
 *   - No negative numbers implemented
 */
class Calculator {

    // Here are the only allowed instance variables!
    // Error messages (more on static later)
    final static String MISSING_OPERAND = "Missing or bad operand";
    final static String DIV_BY_ZERO = "Division with 0";
    final static String MISSING_OPERATOR = "Missing operator or parenthesis";
    final static String OP_NOT_FOUND = "Operator not found";

    // Definition of operators
    final static String OPERATORS = "+-*/^";

    // Method used in REPL
    double eval(String expr) {
        if (expr.length() == 0) {
            return NaN;
        }
        List<String> tokens = tokenize(expr);
        List<String> postfix = infix2Postfix(tokens);
        // TODO double result = evalPostfix(postfix);
        return 0; // result;
    }

    // ------  Evaluate RPN expression -------------------


    double evalPostfix(List<String> sList) {
        Stack<Double> output = new Stack<Double>();

        for (String item : sList) {
            if (item.matches("[0-9]*")) {
                output.push(Double.parseDouble(item));
            } else if (item.matches("[+*/^\\-]")) {
                if(output.size() < 2){
                    throw new IllegalArgumentException(MISSING_OPERAND);
                }
                output.push(applyOperator(item,output.pop(),output.pop()));
            }
        }
        if (output.size() != 1){
            throw new IllegalArgumentException(MISSING_OPERATOR);
        }
        return output.pop();
    }
    // TODO Eval methods

    double applyOperator(String op, double d1, double d2) {
        switch (op) {
            case "+":
                return d1 + d2;
            case "-":
                return d2 - d1;
            case "*":
                return d1 * d2;
            case "/":
                if (d1 == 0) {
                    throw new IllegalArgumentException(DIV_BY_ZERO);
                }
                return d2 / d1;
            case "^":
                return pow(d2, d1);
        }
        throw new RuntimeException(OP_NOT_FOUND);
    }

    // ------- Infix 2 Postfix ------------------------

    // TODO Methods
    List<String> infix2Postfix(List<String> inFix) {
        Stack<String> operatorStack = new Stack<>();
        LinkedList<String> output = new LinkedList<>();
        for (String item : inFix) {
            if (item.matches("[0-9]*")) { //if the char is an number
                output.addLast(item);
            } else if (item.matches("[+*/^\\-]")) { //if token is an operator
                operatorStack = operatorFound(item, operatorStack, output);
            } else if (item.equals("(")) { //if token is (
                operatorStack.push(item);
            } else if (item.equals(")")) { // if token is )
                operatorStack = rightBracketFound(output, operatorStack);
            }
        }
        while (operatorStack.size() != 0) {
            output.addLast(operatorStack.pop());
        }

        return output;
    }

    Stack<String> operatorFound(String item, Stack<String> stack, LinkedList<String> output) {
        while (!stack.empty() &&
                !stack.peek().equals("(") &&
                (
                        getPrecedence(stack.peek()) > getPrecedence(item) ||
                                (
                                        getPrecedence(stack.peek()) == getPrecedence(item) &&
                                                getAssociativity(stack.peek()) == Assoc.LEFT
                                )
                )
        ) {
            output.addLast(stack.pop());
        }
        stack.push(item);

        return stack;
    }

    Stack<String> rightBracketFound(LinkedList<String> output, Stack<String> operator) {
        while (!operator.empty() && !operator.peek().equals("(")) {
            output.addLast(operator.pop());
        }
        if(operator.empty()){
            throw new IllegalArgumentException(MISSING_OPERATOR);
        }
        operator.pop();

        return operator;
    }


    int getPrecedence(String op) {
        if ("+-".contains(op)) {
            return 2;
        } else if ("*/".contains(op)) {
            return 3;
        } else if ("^".contains(op)) {
            return 4;
        } else {
            throw new RuntimeException(OP_NOT_FOUND);
        }
    }

    enum Assoc {
        LEFT,
        RIGHT
    }

    Assoc getAssociativity(String op) {
        if ("+-*/".contains(op)) {
            return Assoc.LEFT;
        } else if ("^".contains(op)) {
            return Assoc.RIGHT;
        } else {
            throw new RuntimeException(OP_NOT_FOUND);
        }
    }


    // ---------- Tokenize -----------------------


    List<String> tokenize(String s) {
        s = s.replaceAll("([+*/^()\\-])", " $1 ").trim();

        return Arrays.asList(s.split("\\s+"));
    }
    // TODO Methods to tokenize

}
