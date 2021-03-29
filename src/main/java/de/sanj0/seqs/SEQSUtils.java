package de.sanj0.seqs;

import java.util.function.DoubleFunction;

public class SEQSUtils {

    public static double DX = 0.000_001;

    public static DoubleFunction<Double> derive(DoubleFunction<Double> f) {
        return x -> (f.apply(x + DX) - f.apply(x)) / DX;
    }

    public static double newtonRoot(final DoubleFunction<Double> f, final DoubleFunction<Double> fDerived, final double start, final double iterations) {
        double x = start;
        for (int i = 0; i < iterations; i++) {
            x = x - f.apply(x) / fDerived.apply(x);
        }

        return x;
    }

    /**
     * Provides shortcuts to often used
     * Math functions like {@code sqrt} and {@code pow}
     * <p>
     * Full list of supported function shortcuts: <br>
     * <ul>
     *     <li>pow - Math.pow</li>
     *     <li>sqrt - Math.sqrt</li>
     *     <li>cbrt - Math.cbrt</li>
     *     <li>log - Math.log</li>
     *     <li>abs - Math.abs</li>
     *     <li>ceil - Math.ceil</li>
     *     <li>floor - Math.floor</li>
     *     <li>sin - Math.sin</li>
     *     <li>cos - Math.cos</li>
     *     <li>tan - Math.tan</li>
     * </ul>
     *
     * @return valid js from given simple seqs expression syntax
     */
    public static String seqsSyntaxToJS(final String eq) {
        return eq.replace("pow", "Math.pow").
                replace("sqrt", "Math.sqrt").
                replace("cbrt", "Math.cbrt").
                replace("log", "Math.log").
                replace("abs", "Math.abs").
                replace("ceil", "Math.ceil").
                replace("floor", "Math.floor").
                replace("sin", "Math.sin").
                replace("cos", "Math.cos").
                replace("tan", "Math.tan");
    }

    /**
     * Converts the given equation string
     * into a function such as the root of said
     * function in f(x) = 0 is the solution of the
     * equation.
     *
     * @param equation an equation
     * @return a function derived from the equation such as the root of said
     * function in f(x) = 0 is the solution of the equation
     */
    public static JSFunction equationToFunctionWithRootAsSolution(final String equation) {
        final String[] hands = equation.split("=");
        final String f = "(" + hands[0] + ")-(" + hands[1] + ")";
        return new JSFunction(f);
    }
}
