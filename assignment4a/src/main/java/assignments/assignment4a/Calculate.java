package assignments.assignment4a;


import java.lang.reflect.Array;
import java.util.*;

public class Calculate {
    private static final List<String> OPERATORS = Arrays.asList("+", "-", "*", "/", "^", "(", ")");
    private static ArrayList<String> tokenList = new ArrayList<>();
    private static Stack<String> stack = new Stack<>();
    private static String result;
    private static ArrayList<String> postfixExpression;

    public static void start(String input) {
        tokenList.clear();
        System.out.println("input: " + input);
        StringTokenizer tokens = new StringTokenizer(input, "+-*/^() ", true);
        System.out.println("tokens: " + tokens);
        while (tokens.hasMoreTokens())
            tokenList.add(tokens.nextToken());
        tokenList.removeIf(s -> s.equals(" ")); // remove all spaces
        postfixExpression = convertToPostfix(tokenList);
        result = evaluatePostfixExpression(postfixExpression);

    }

    public static String getResult(){
        return result;
    }

    public static ArrayList<String> getPostfixExpression() {
        return postfixExpression;
    }

    private static String evaluatePostfixExpression(ArrayList<String> tokenList) {
        System.out.println(tokenList);

        for (String element : tokenList) {
            System.out.println(stack);
            if (!OPERATORS.contains(element)) {
                // if element is an operand, push to stack
                stack.push(element);
            } else if (OPERATORS.contains(element)) {
                // if an operator is encountered, pop 2 items from the stack, then push
                // the result back to the stack
                int operand1 = Integer.parseInt(stack.pop());
                int operand2 = Integer.parseInt(stack.pop());
                switch (element){
                    case "+" -> stack.push((operand2 + operand1) + "");
                    case "-" -> stack.push((operand2 - operand1) + "");
                    case "*" -> stack.push((operand2 * operand1) + "");
                    case "/" -> stack.push((operand2 / operand1) + "");
                    case "^" -> stack.push((int)Math.pow(operand2, operand1) + "");
                }
            }

        }
        return stack.pop();
    }

    public static ArrayList<String> convertToPostfix(ArrayList<String> arr) {
        ArrayList<String> temp = new ArrayList<>();

        for (String element : arr) {
            if (!OPERATORS.contains(element)) {
                // if element is an operand, immediately output
                temp.add(element);
            } else if (element.equals("(")) {
                stack.push(element);
            } else if (element.equals(")")) {
                // if element is a closed parenthesis, pop stack symbols until an open
                // parenthesis appears
                while (!stack.peek().equals("(")) {
                    temp.add(stack.pop());
                }
                stack.pop();

            } else {
                // if element is an operator, pop all stack symbols until a symbol of lower
                // precedence or a right-associative symbol of equal precedence appears. Then
                // push the operator
                while (true) {
                    if (stack.isEmpty())
                        break;
                    else if (precedenceStack(element) < precedenceStack(stack.peek()))
                        break;
                    else if ((precedenceStack(element) == precedenceStack(stack.peek()))
                            && !isRightAssociative(stack.peek())) {
                        break;
                    } else
                        temp.add(stack.pop());
                }
                stack.push(element);

            }

        }

        while (!stack.isEmpty())
            temp.add(stack.pop());

        return temp;
    }


    private static int precedence(char c) {
        return switch (c) {
            case '(' -> 1;
            case '^' -> 2;
            case '*', '/' -> 3;
            default -> 4;
        };
    }

    private static int precedenceStack(String c) {
        return switch (c) {
            case "^" -> 2;
            case "*", "/" -> 3;
            default -> 4;
        };
    }

    private static boolean isRightAssociative(String c) {
        return switch (c) {
            case "-", "/" -> true;
            default -> false;
        };
    }


    public static void main(String[] args) {
        start("1 - 2 - 4 ^ 5 * 3 * 6 / 7 ^ 2 ^ 2");
    }
}
