package de.sanj0.seqs;

import org.apache.commons.cli.*;

public class CommandLineHelper {

    public static CommandLine handleArgs(final String[] args) throws ParseException {
        final Options options = new Options();
        options.addOption(Option.builder(Main.DX_OPTION).
                hasArg().
                desc("the dx value to be used for deriving\nDefault: " + Main.DEFAULT_DX).
                argName("DOUBLE").
                type(Double.class).build());
        options.addOption(Option.builder(Main.DEPTH_OPTION).
                longOpt("depth").
                hasArg().
                desc("the number of times to apply the newton formula - the higher this value the (potentially) more exact the solution becomes\nDefault: " + Main.DEFAULT_DEPTH).
                argName("INTEGER").
                type(Number.class).build());
        options.addOption(Option.builder(Main.START_OPTION).
                longOpt("start").
                hasArg().
                desc("the starting value for the newton method to solve the equation\nDefault: " + Main.DEFAULT_START).
                argName("INTEGER").
                type(Number.class).build());
        options.addOption(Main.VERBOSE_OPTION, "verbose", false, "prints out some more information");
        options.addOption(Main.HELP_OPTION, "help", false, "prints this help");

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption(Main.HELP_OPTION)) {
            final String header = "Solves an equation\nThe equation has to be valid JavaScript\n";
            final String footer = "\nExample usage: seqs -d 5 \"pow(x,2)+4 = 7\"\n\nBuilt and maintained by sanj0 at https://www.github.com/sanj0/seqs";

            final HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("seqs <EQUATION>", header, options, footer, true);
            System.exit(0);
        }

        return cmd;
    }
}
