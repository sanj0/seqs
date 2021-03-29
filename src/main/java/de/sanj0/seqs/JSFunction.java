package de.sanj0.seqs;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.function.DoubleFunction;

public class JSFunction implements DoubleFunction<Double> {

    private final ScriptEngine JS = new ScriptEngineManager().getEngineByExtension("js");
    private String function;

    public JSFunction(final String function) {
        this.function = function;
    }

    @Override
    public Double apply(final double value) {
        try {
            JS.put("x", value);
            return Double.valueOf(JS.eval(function).toString());
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return 0.0D;
    }

    /**
     * Gets {@link #function}.
     *
     * @return the value of {@link #function}
     */
    public String getFunction() {
        return function;
    }

    /**
     * Sets {@link #function}.
     *
     * @param function the new value of {@link #function}
     */
    public void setFunction(final String function) {
        this.function = function;
    }
}
