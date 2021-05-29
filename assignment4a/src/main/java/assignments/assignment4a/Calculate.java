package assignments.assignment4a;


import java.util.*;

public class Calculate {
    private static final List<String> OPERATORS = Arrays
            .asList("+", "-", "*", "/", "^", "(", ")");
    private static ArrayList<String> tokenList;
    private static Stack<String> stack;
    private static String result;
    private static ArrayList<String> postfixExpression;
    private static ArrayList<String> errorMessages;

    public static void start(String input) {
        tokenList = new ArrayList<>();
        errorMessages = new ArrayList<>();
        stack = new Stack<>();
        postfixExpression = new ArrayList<>();
        System.out.println("input: " + input);
        StringTokenizer tokens = new StringTokenizer(input, "+-*/^() ", true);
        while (tokens.hasMoreTokens())
            tokenList.add(tokens.nextToken());
        tokenList.removeIf(s -> s.equals(" ")); // remove all spaces

        postfixExpression = convertToPostfix(tokenList);

        if (validateTokens()) {
            result = evaluatePostfixExpression(postfixExpression);
            if (!stack.isEmpty()) {
                errorMessages.add("Missing operator");
                System.out.println("MISSING OPERATOR HERE");
            }
        } else {
            evaluatePostfixExpression(postfixExpression);
            result = "N/A";
        }
    }

    private static boolean validateTokens() {
        // check if tokenList has invalid inputs
        for (String token : tokenList) {
            if (!OPERATORS.contains(token) && !isNumeric(token)) {
                errorMessages.add("Parse Error");
                System.out.println("PARSE ERROR");
                break;
            }
        }

        return errorMessages.isEmpty();
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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
                try {
                    int operand1 = Integer.parseInt(stack.pop());
                    int operand2 = Integer.parseInt(stack.pop());
                    switch (element) {
                        case "+" -> stack.push((operand2 + operand1) + "");
                        case "-" -> stack.push((operand2 - operand1) + "");
                        case "*" -> stack.push((operand2 * operand1) + "");
                        case "/" -> stack.push((operand2 / operand1) + "");
                        case "^" -> stack.push((int) Math.pow(operand2, operand1) + "");
                    }
                } catch (EmptyStackException | NumberFormatException e) {
                    errorMessages.add("Missing operand");
                    System.out.println("MISSING OPERAND");
                    return "N/A";
                } catch (ArithmeticException e) {
                    errorMessages.add("Division by Zero");
                    System.out.println("DIVISION BY ZERO");
                    return "N/A";
                }
            }

        }
        return stack.pop();
    }

    public static ArrayList<String> convertToPostfix(ArrayList<String> arr) {
        ArrayList<String> temp = new ArrayList<>();

        for (String element : arr) {
            System.out.println("stack: " + stack);
            System.out.println(temp);
            System.out.println();
            if (!OPERATORS.contains(element)) {
                // if element is an operand, immediately output
                temp.add(element);
            } else if (element.equals("(")) {
                stack.push(element);
            } else if (element.equals(")")) {
                // if element is a closed parenthesis, pop stack symbols until an open
                // parenthesis appears
                try {
                    while (!stack.peek().equals("(")) {
                        temp.add(stack.pop());
                    }
                    stack.pop();
                } catch (EmptyStackException e) {
                    errorMessages.add("Missing open parenthesis");
                    System.out.println("MISSING OPEN PARENTHESIS");
                }

            } else {
                // if element is an operator, pop all stack symbols until a symbol of lower
                // precedence or a right-associative symbol of equal precedence appears. Then
                // push the operator
                while (true) {
                    if (stack.isEmpty())
                        break;
                    else if (precedence(element) < precedence(stack.peek()))
                        break;
                    else if ((precedence(element) == precedence(stack.peek()))
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

    private static int precedence(String c) {
        return switch (c) {
            case "^" -> 2;
            case "*", "/" -> 3;
            default -> 4;
        };
    }

    private static boolean isRightAssociative(String c) {
        return switch (c) {
            case "*", "-", "/" -> true;
            default -> false;
        };
    }

    public static ArrayList<String> getErrorMessages() {
        return errorMessages;
    }

    public static String getResult() {
        return result;
    }

    public static String getPostfixExpression() {
        StringBuilder s = new StringBuilder();
        for (String element : postfixExpression) {
            s.append(element).append(" ");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        start("1 - 2 - 4 ^ 5 * 3 * 6 / 7 ^ 2 ^ 2");
        System.out.println(result);

    }
}