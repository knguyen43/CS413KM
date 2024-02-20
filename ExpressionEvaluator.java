/*************************************************
File: ExpressionDriver.java
By: Khang Nguyen
Date: Feb 19 2024
Compile: javac ExpressionEvaluator.java
Usage: java ExpressionEvaluator
System: Any system with Java support
Description: This program evaluates a predefined infix and postfix algebraic expressions with different identifier values.
             The ExpressionDriver class serves as the client adaptor, initiating the test for evaluating both infix
             and postfix expressions. The InfixEvaluator class evaluates infix expressions, while the PostfixEvaluator
             class evaluates postfix expressions.
*************************************************/
import java.util.Scanner;
import java.util.Stack;

public class ExpressionEvaluator {

    public static void main(String[] args) {
        // Define the infix and postfix expressions
        final String INFIX_EXPRESSION = "(a+b)*(c+d)";
        final String POSTFIX_EXPRESSION = "ac-b^d+";

        Scanner scanner = new Scanner(System.in);
        boolean computingNeeded = true;

        while (computingNeeded) {
            // Read values for identifiers
            System.out.print("Enter value for 'a': ");
            double aValue = scanner.nextDouble();
            System.out.print("Enter value for 'b': ");
            double bValue = scanner.nextDouble();
            System.out.print("Enter value for 'c': ");
            double cValue = scanner.nextDouble();
            System.out.print("Enter value for 'd': ");
            double dValue = scanner.nextDouble();

            // Evaluate infix expression
            double infixResult = InfixEvaluator.evaluateInfix(INFIX_EXPRESSION, aValue, bValue, cValue, dValue);
            System.out.println("Value of infix string " + INFIX_EXPRESSION + " with a = " + aValue + ", b = " + bValue +
                    ", c = " + cValue + ", d = " + dValue + " is " + infixResult);

            // Evaluate postfix expression
            double postfixResult = PostfixEvaluator.evaluatePostfix(POSTFIX_EXPRESSION, aValue, bValue, cValue, dValue);
            System.out.println("Value of postfix string " + POSTFIX_EXPRESSION + " with a = " + aValue + ", b = " + bValue +
                    ", c = " + cValue + ", d = " + dValue + " is " + postfixResult);

            System.out.print("Do you want to compute again? (yes/no): ");
            String answer = scanner.next();
            computingNeeded = answer.equalsIgnoreCase("yes");
        }

        scanner.close();
    }
}

class InfixEvaluator {
    public static double evaluateInfix(String str, double a, double b, double c, double d) {
        // Initialize stacks to store operators and operands
        Stack<Character> operatorStack = new Stack<>();
        Stack<Double> valueStack = new Stack<>();

        // Remove white spaces from the expression
        str = str.replaceAll("\\s+", "");

        // Iterate through the expression
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            // If current character is a digit or an identifier, it is part of an operand
            if (Character.isDigit(ch) || OperatorUtil.isIdentifier(ch)) {
                StringBuilder operand = new StringBuilder();
                // Continue reading characters until a non-digit/non-identifier character is encountered
                while (i < str.length() && (Character.isDigit(str.charAt(i)) || OperatorUtil.isIdentifier(str.charAt(i)))) {
                    operand.append(str.charAt(i++));
                }
                // If the operand is an identifier, push its corresponding value onto the stack
                if (OperatorUtil.isIdentifier(operand.charAt(0))) {
                    valueStack.push(OperatorUtil.getIdentifierValue(operand.charAt(0), a, b, c, d));
                } else { // Otherwise, push the parsed double value onto the stack
                    valueStack.push(Double.parseDouble(operand.toString()));
                }
                i--; // Decrement i to account for the extra increment in the loop
            }
            // If current character is an opening parenthesis, push it onto operator stack
            else if (ch == '(') {
                operatorStack.push(ch);
            }
            // If current character is a closing parenthesis, calculate all operations inside the parenthesis
            else if (ch == ')') {
                // Evaluate all operators until '(' is found
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    double result = OperatorUtil.applyOperator(operatorStack.pop(), valueStack.pop(), valueStack.pop());
                    valueStack.push(result);
                }
                // Check for mismatched parentheses
                if (operatorStack.isEmpty() || operatorStack.pop() != '(') {
                    throw new IllegalArgumentException("Mismatched parentheses!");
                }
            }
            // If current character is an operator, apply operator precedence
            else if (OperatorUtil.isOperator(ch)) {
                while (!operatorStack.isEmpty() && OperatorUtil.precedence(operatorStack.peek()) >= OperatorUtil.precedence(ch)) {
                    double result = OperatorUtil.applyOperator(operatorStack.pop(), valueStack.pop(), valueStack.pop());
                    valueStack.push(result);
                }
                operatorStack.push(ch);
            }
        }

        // Evaluate remaining operators in the stacks
        while (!operatorStack.isEmpty()) {
            double result = OperatorUtil.applyOperator(operatorStack.pop(), valueStack.pop(), valueStack.pop());
            valueStack.push(result);
        }

        // Result will be the last element in the value stack
        return valueStack.pop();
    }
}

class PostfixEvaluator {
    public static double evaluatePostfix(String str, double a, double b, double c, double d) {
        // Initialize stack to store operands
        Stack<Double> operandStack = new Stack<>();

        // Remove white spaces from the expression
        str = str.replaceAll("\\s+", "");

        // Iterate through the expression
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            // If current character is a digit, it is part of an operand
            if (Character.isDigit(ch)) {
                StringBuilder operand = new StringBuilder();
                while (i < str.length() && (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.')) {
                    operand.append(str.charAt(i++));
                }
                operandStack.push(Double.parseDouble(operand.toString()));
                i--;
            } else if (OperatorUtil.isIdentifier(ch)) {
                // If current character is an identifier, push the corresponding value onto the stack
                operandStack.push(OperatorUtil.getIdentifierValue(ch, a, b, c, d));
            } else if (OperatorUtil.isOperator(ch)) {
                // If current character is an operator, apply the operator on the operands
                double operand2 = operandStack.pop();
                double operand1 = operandStack.pop();
                operandStack.push(OperatorUtil.applyOperator(ch, operand1, operand2));
            }
        }

        // Result will be the last element in the operand stack
        return operandStack.pop();
    }
}



class OperatorUtil {
    public static double applyOperator(char operator, double a, double b) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Division by zero!");
                return a / b;
            case '^':
                return Math.pow(a, b);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    public static boolean isIdentifier(char ch) {
        return ch == 'a' || ch == 'b' || ch == 'c' || ch == 'd';
    }

    public static double getIdentifierValue(char identifier, double a, double b, double c, double d) {
        switch (identifier) {
            case 'a':
                return a;
            case 'b':
                return b;
            case 'c':
                return c;
            case 'd':
                return d;
            default:
                throw new IllegalArgumentException("Invalid identifier: " + identifier);
        }
    }

    public static int precedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        } else if (operator == '^') {
            return 3;
        } else {
            return -1; // If the character is not an operator
        }
    }
}
