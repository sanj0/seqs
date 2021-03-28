package de.sanj0.seqs;

import org.apache.commons.cli.*;

import java.util.function.Function;

public class Main {

    private static final String DX_OPTION = "dx";
    private static final String DEPTH_OPTION = "d";
    private static final String START_OPTION = "s";
    private static final String HELP_OPTION = "h";

    private static final int DEFAULT_DEPTH = 3;
    private static final double DEFAULT_DX = SEQSUtils.DX;
    private static final double DEFAULT_START = 2;

    public static void main(String[] args) throws ParseException {
        final Options options = new Options();
        options.addOption(Option.builder(DX_OPTION).
                hasArg().
                desc("the dx value to be used for deriving\nDefault: " + DEFAULT_DX).
                argName("DOUBLE").
                type(Double.class).build());
        options.addOption(Option.builder(DEPTH_OPTION).
                longOpt("depth").
                hasArg().
                desc("the number of times to apply the newton formula - the higher this value the (potentially) more exact the solution becomes\nDefault: " + DEFAULT_DEPTH).
                argName("INTEGER").
                type(Number.class).build());
        options.addOption(Option.builder(START_OPTION).
                longOpt("start").
                hasArg().
                desc("the starting value for the newton method to solve the equation\nDefault: " + DEFAULT_START).
                argName("INTEGER").
                type(Number.class).build());
        options.addOption(HELP_OPTION, "help", false, "prints this help");

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption(HELP_OPTION)) {
            final String header = "Solves an equation\nThe equation has to be valid JavaScript\n";
            final String footer = "\nExample usage: seqs -d 5 \"pow(x,2)+4 = 7\"\n\nBuilt and maintained by sanj0 at https://www.github.com/sanj0/seqs";

            final HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("seqs <EQUATION>", header, options, footer, true);
            System.exit(0);
        }

        SEQSUtils.DX = getOrDefault(cmd, DX_OPTION, DEFAULT_DX, Double::parseDouble);
        final int depth = getOrDefault(cmd, DEPTH_OPTION, DEFAULT_DEPTH, Integer::parseInt);
        final double start = getOrDefault(cmd, START_OPTION, DEFAULT_START, Double::parseDouble);

        if (cmd.getArgList().size() == 1) {
            final JSFunction f = SEQSUtils.equationToFunctionWithRootAsSolution(cmd.getArgList().get(0));
            final double solution = SEQSUtils.newtonRoot(f, SEQSUtils.derive(f), start, depth);
            System.out.println(solution);
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
