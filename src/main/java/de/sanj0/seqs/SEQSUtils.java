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
