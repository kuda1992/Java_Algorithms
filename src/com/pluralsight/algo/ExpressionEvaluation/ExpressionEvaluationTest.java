package com.pluralsight.algo.ExpressionEvaluation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressionEvaluationTest {

    @Test
    void shouldEvaluateTheGivenExpression() {

        final String expression = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";

        final ExpressionEvaluation expressionEvaluation = new ExpressionEvaluation(expression);
        assertEquals(expressionEvaluation.evaluate(), 101.0);
    }
}