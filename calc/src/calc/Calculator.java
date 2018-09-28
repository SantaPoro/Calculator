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
        // TODO List<String> postfix = infix2Postfix(tokens);
        // TODO double result = evalPostfix(postfix);
        return 0; // result;
    }

    // ------  Evaluate RPN expression -------------------


    double evalPostfix(List<String> sList) {

        return 0;
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
        Stack<String> stack = new Stack<>();
        List<String> postFix;
        for (String item : inFix){
            if(true){ //if the char is an number
                stack.push(item);
            } else if (true) { //if token is an operator
                stack = operatorFound(item, stack);
            }

        }

        return new ArrayList<>();
    }

    Stack<String> operatorFound (String item, Stack<String> stack ){
        if (getPrecedence(stack.peek()) > getPrecedence(item)){

        }

        return stack;
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
