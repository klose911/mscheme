package org.klose.scheme.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.klose.scheme.constant.SConstant;
import org.klose.scheme.exception.IllegalExpressionException;
import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.model.SFrame;
import org.klose.scheme.model.SProcedure;
import org.klose.scheme.type.SBoolean;
import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;
import org.klose.scheme.type.SString;
import org.klose.scheme.utils.EvalUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Eval takes as arguments an expression and an environment and return evaluation value.
 * <p>
 * eg. eval("(+ 1 2)" env) will return 3
 */
public class EvalService {
    private EvalService() {
        throw new UnsupportedOperationException("illegal constructor for EvalService");
    }

    public static SObject eval(SExpression exp, SFrame env)
            throws IllegalExpressionException, WrongArgumentNumberException {
        if (exp == null)
            throw new IllegalArgumentException("expression can not be null");
        if (env == null)
            throw new IllegalArgumentException("environment can not be null");

        if (exp.isSelfEval()) {
            String str = exp.getValue();
            //such as numbers, strings, eval returns the expression itself.
            if (EvalUtils.isNumber(str))
                return new SNumber(NumberUtils.createNumber(str));
            else if (EvalUtils.isString(str))
                return new SString(StringUtils.substring(str, 1,
                        str.length() - 1));
            else
                //look up variables in the environment to find their values
                return env.lookup(str);
        }

        SExpression operator = exp.getChildren().get(0);
        if (operator == null)
            throw new IllegalExpressionException("operator can not be null");

        //special form
        switch (operator.getValue()) {
            case "quote":  // ("quote" a) => a
                return QuoteService.evalQuote(exp);
            case "if": // (if (> 2 1) "foo" "bar")
                return ConditionService.evalIf(exp, env);
            case "define": // (define a 1)
                return DefineService.evalDefine(exp, env);
            case "set!": //  (set! a 1)
                return AssignService.evalAssign(exp, env);
            case "lambda": // (lambda (x y) (+ x y))
                return LambdaService.evalLambda(exp, env);
            case "begin": // (begin (+ 1 2) (* 3 4)) => 12
                return SequenceService.evalSequence(exp, env);
            default:
                break;
        }

        //Otherwise the operator should be a procedure, then recursively evaluate the operator part and the operands of the combination.
        //The resulting procedure and arguments are passed to apply, which handles the actual procedure application.
        SProcedure procedure = (SProcedure) EvalService.eval(operator, env);
        if (procedure != null)
            return ApplyService.apply(procedure, listOfOperands(exp, env));
        else
            throw new IllegalExpressionException("Unknown expression type -- EVAL" + exp);
    }

    private static SObject[] listOfOperands(SExpression exp, SFrame env)
            throws IllegalExpressionException, WrongArgumentNumberException {
        assert (exp != null);
        assert (env != null);

        //evaluates each operand and returns a list of the corresponding values
        if (exp.getChildren() == null || exp.getChildren().isEmpty())
            throw new IllegalExpressionException("expression is not procedure");

        int paramSize = exp.getChildren().size() - 1;
        SObject[] operands = new SObject[paramSize];
        for (int i = 0; i < paramSize; i++)
            operands[i] = EvalService.eval(exp.getChildren().get(i + 1), env);

        return operands;
    }

    static class AssignService {
        private AssignService() {
            throw new UnsupportedOperationException("illegal constructor for AssignService");
        }

        static SString evalAssign(SExpression exp, SFrame env)
                throws IllegalExpressionException, WrongArgumentNumberException {
            if (exp == null)
                throw new IllegalArgumentException("expression of evalAssign can not be null");
            if (env == null)
                throw new IllegalArgumentException("environment of evalAssign can not be null");

            if (exp.getChildren().size() != 3)
                throw new WrongArgumentNumberException(3, exp.getChildren().size(), "evalAssign");

            String var = exp.getChildren().get(1).getValue();
            SObject value = EvalService.eval(exp.getChildren().get(2), env);
            //modify the binding of a variable under the environment
            env.assign(var, value);
            return new SString("ok");
        }
    }

    static class ConditionService {
        private ConditionService() {
            throw new UnsupportedOperationException("illegal constructor for ConditionService");
        }

        static SObject evalIf(SExpression exp, SFrame env)
                throws IllegalExpressionException, WrongArgumentNumberException {
            if (exp == null)
                throw new IllegalArgumentException("expression of evalIf can not be null");
            if (env == null)
                throw new IllegalArgumentException("environment of evalIf can not be null");

            if (exp.getChildren().size() < 3)
                throw new WrongArgumentNumberException(3, exp.getChildren().size(), "evalIf");

            // evaluate the predicate expression
            SExpression predicate = exp.getChildren().get(1);
            SBoolean check = (SBoolean) EvalService.eval(predicate, env);
            if (check.getValue()) {
                //evaluate the consequent if the predicate is true
                SExpression consequent = exp.getChildren().get(2);
                return EvalService.eval(consequent, env);
            } else {
                //if alternative available, evaluate the alternative
                if (exp.getChildren().size() == 4) {
                    SExpression alternative = exp.getChildren().get(3);
                    return EvalService.eval(alternative, env);
                } else
                    //if alternative misses, directly return false
                    return new SBoolean(false);
            }
        }
    }

    static class QuoteService {
        static SObject evalQuote(SExpression exp) throws WrongArgumentNumberException {
            if (exp == null)
                throw new IllegalArgumentException("expression of evalQuote can not be null");

            if (exp.getChildren().size() != 2)
                throw new WrongArgumentNumberException(2, exp.getChildren().size(), "quote");

            //returns the expression that was quoted
            return exp.getChildren().get(1);
        }

        private QuoteService() {
            throw new UnsupportedOperationException("illegal constructor for QuoteService");
        }
    }

    static class SequenceService {
        static SObject evalSequence(SExpression exp, SFrame env)
                throws IllegalExpressionException, WrongArgumentNumberException {
            if (exp == null)
                throw new IllegalArgumentException("expression of evalSequence can not be null");
            if (env == null)
                throw new IllegalArgumentException("environment of evalSequence can not be null");

            if (exp.getChildren().size() < 2)
                throw new WrongArgumentNumberException(2, exp.getChildren().size(), "evalSequence");

            SObject result = SConstant.NIL;
            List<SExpression> children = exp.getChildren();
            for (int i = 1; i < children.size(); i++)
                result = EvalService.eval(children.get(i), env);

            return result;
        }

        private SequenceService() {
            throw new UnsupportedOperationException("illegal constructor for SequenceService");
        }
    }

    static class LambdaService {
        static SProcedure evalLambda(SExpression exp, SFrame env) throws WrongArgumentNumberException {
            if (exp == null)
                throw new IllegalArgumentException("expression of evalLambda can not be null");
            if (env == null)
                throw new IllegalArgumentException("environment of evalLambda can not be null");

            if (exp.getChildren().size() != 3)
                throw new WrongArgumentNumberException(3, exp.getChildren().size(), "evalLambda");

            // transformed into an applicable procedure by packaging together the parameters
            // and body specified by the lambda expression with the environment of the evaluation
            SExpression body = exp.getChildren().get(2);
            SExpression parametersExp = exp.getChildren().get(1);
            List<String> parameters = new ArrayList<>();
            for (SExpression p : parametersExp.getChildren())
                parameters.add(p.getValue());


            return new SProcedure(body, parameters, env);
        }

        private LambdaService() {
            throw new UnsupportedOperationException("illegal constructor for LambdaService");
        }
    }

    static class DefineService {
        static SString evalDefine(SExpression exp, SFrame env)
                throws IllegalExpressionException, WrongArgumentNumberException {
            if (exp == null)
                throw new IllegalArgumentException("expression of evalDefine can not be null");
            if (env == null)
                throw new IllegalArgumentException("environment of evalDefine can not be null");

            if (exp.getChildren().size() != 3)
                throw new WrongArgumentNumberException(3, exp.getChildren().size(), "evalDefine");

            env.define(getDefinitionVariable(exp), getDefinitionValue(exp, env));
            return new SString("ok");
        }


        private static String getDefinitionVariable(SExpression exp)
                throws IllegalExpressionException {
            assert (exp != null);
            assert (exp.getChildren() != null);
            assert (exp.getChildren().size() == 3);

            SExpression variableExpression = exp.getChildren().get(1);
            if (variableExpression.isSelfEval())
                return variableExpression.getValue();
            else {
                List<SExpression> variableList = variableExpression.getChildren();
                if (variableList.isEmpty())
                    throw new IllegalExpressionException("variable list of define can not be empty");

                return variableList.get(0).getValue();
            }
        }

        private static SObject getDefinitionValue(SExpression exp, SFrame env)
                throws IllegalExpressionException, WrongArgumentNumberException {
            assert (exp != null);
            assert (exp.getChildren() != null);
            assert (exp.getChildren().size() == 3);
            assert (env != null);

            SExpression variableExpression = exp.getChildren().get(1);
            SExpression valueExpression = exp.getChildren().get(2);

            if (variableExpression.isSelfEval())
                return EvalService.eval(valueExpression, env);
            else {
                List<SExpression> variableList = variableExpression.getChildren();
                if (variableList.isEmpty())
                    throw new IllegalExpressionException("variable list of define can not be empty");

                return new SProcedure(valueExpression,
                        getProcedureParameters(variableList), env);
            }
        }

        private static List<String> getProcedureParameters(List<SExpression> variableList) {
            assert (variableList != null);
            assert (!variableList.isEmpty());

            List<String> params = new ArrayList<>();
            for (int i = 1; i < variableList.size(); i++)
                params.add(variableList.get(i).getValue());

            return params;
        }

        private DefineService() {
            throw new UnsupportedOperationException("illegal constructor for DefineService");
        }
    }

}
