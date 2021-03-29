package de.sanj0.seqs;

import org.apache.commons.cli.*;

import java.util.function.Function;

public class Main {

    public static final String DX_OPTION = "dx";
    public static final String DEPTH_OPTION = "d";
    public static final String START_OPTION = "s";
    public static final String HELP_OPTION = "h";
    public static final String VERBOSE_OPTION = "v";

    public static final int DEFAULT_DEPTH = 3;
    public static final double DEFAULT_DX = SEQSUtils.DX;
    public static final double DEFAULT_START = 2;

    public static void main(String[] args) throws ParseException {
        final CommandLine cmd = CommandLineHelper.handleArgs(args);

        SEQSUtils.DX = getOrDefault(cmd, DX_OPTION, DEFAULT_DX, Double::parseDouble);
        final int depth = getOrDefault(cmd, DEPTH_OPTION, DEFAULT_DEPTH, Integer::parseInt);
        final double start = getOrDefault(cmd, START_OPTION, DEFAULT_START, Double::parseDouble);

        if (cmd.getArgList().size() == 1) {
            final JSFunction f = SEQSUtils.equationToFunctionWithRootAsSolution(SEQSUtils.seqsSyntaxToJS(cmd.getArgList().get(0)));

            final boolean verbose = cmd.hasOption(VERBOSE_OPTION);
            if (verbose) {
                System.out.println("function whose root is the solution: " + f.getFunction());
            }
            final double solution = SEQSUtils.newtonRoot(f, SEQSUtils.derive(f), start, depth);
            System.out.println((verbose ? "x = " : "") + solution);
        } else {
            System.err.println("equation missing and/or unknown args! try seqs -h for help");
        }
    }

    private static <T> T getOrDefault(final CommandLine cmd, final String option, final T defaultValue, Function<String, T> parser) {
        if (cmd.hasOption(option)) {
            return parser.apply(cmd.getOptionValue(option));
        } else {
            return defaultValue;
        }
    }
}
