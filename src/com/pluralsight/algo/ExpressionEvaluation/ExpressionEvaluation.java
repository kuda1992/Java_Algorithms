package com.pluralsight.algo.ExpressionEvaluation;


import java.util.Stack;

public class ExpressionEvaluation {

    private Stack<String> operators;
    private Stack<Double> values;
    private String expression;

    public ExpressionEvaluation(String expression) {
        this.expression = expression;
        operators = new Stack<>();
        values = new Stack<>();
    }

    public double evaluate() {
        for (char c : expression.toCharArray()) {
            String charAsString = ExpressionEvaluation.convertCharToString(c);
            if (isOpenBracket(charAsString)) {
                continue;
            }
            if (isPlusOperator(charAsString)) {
                operators.push(charAsString);
                continue;
            }

            if (isMinusOperator(charAsString)) {
                operators.push(charAsString);
                continue;
            }

            if (isMuliplicationOperator(charAsString)) {
                operators.push(charAsString);
                continue;
            }

            if (isDivideOperator(charAsString)) {
                operators.push(charAsString);
                continue;
            }

            if (isCloseBracket(charAsString)) {

                String operator = operators.pop();
                Double value = values.pop();

                if (isPlusOperator(operator)) {
                    value = values.pop() + value;
                }

                if (isMinusOperator(operator)) {
                    value = values.pop() - value;
                }

                if (isMuliplicationOperator(operator)) {
                    value = values.pop() * value;
                }

                if (isDivideOperator(operator)) {
                    value = values.pop() / value;
                }

                values.push(value);
            }

            if (isValidNumber(charAsString)) {
                values.push(convertStringToDouble(charAsString));
            }
        }
        return values.pop();
    }

    private boolean isOpenBracket(String s) {
        return s.equals("(");
    }

    private boolean isCloseBracket(String s) {
        return s.equals(")");
    }

    private boolean isPlusOperator(String s) {
        return s.equals("+");
    }

    private boolean isMinusOperator(String s) {
        return s.equals("-");
    }

    private boolean isDivideOperator(String s) {
        return s.equals("/");
    }

    private boolean isMuliplicationOperator(String s) {
        return s.equals("*");
    }

    private static String convertCharToString(char c) {
        return String.valueOf(c);
    }

    private boolean isValidNumber(String s) throws NumberFormatException {
        boolean isValid = false;
        try {
            Double.parseDouble(s);
            isValid = true;
        } catch (NumberFormatException error) {
            System.out.println(error.getMessage());
        }

        return isValid;
    }

    private Double convertStringToDouble(String s) {
        return Double.parseDouble(s);
    }
}
