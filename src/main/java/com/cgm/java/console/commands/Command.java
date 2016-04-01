package com.cgm.java.console.commands;

import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

/**
 * Base class for commands
 */
public abstract class Command {

    /**
     * Constant for requesting help with a command.
     */
    public static final String HELP_OPTION = "help";

    /**
     * Constant for requesting verbose output from the command.
     */
    public static final String VERBOSE_OPTION = "verbose";

    /**
     * Gets all options
     *
     * @return an {@link org.apache.commons.cli.Options} object containing the available options
     */
    public Options getOptions() {
        return new Options()
                .addOption("h", HELP_OPTION, false, "Print usage")
                .addOption("v", VERBOSE_OPTION, false, "Verbose output");
    }

    /**
     * Runs the command
     *
     * @param args
     *         all arguments, including the name of the command
     * @return 0 if successful
     * @throws Exception
     *         if there are issues parsing the options or any unhandled issues while running the command.
     */
    public final int run(final String[] args) throws Exception {

        final int argumentsToRemove = 1;

        // Run the command with the given args, excluding the command name itself.
        final CommandLine line = new DefaultParser().parse(getOptions(), Arrays.copyOfRange(args, argumentsToRemove, args.length));

        // Handle help options
        if (line.hasOption(HELP_OPTION)) {
            usage();
            return 0;
        }

        return run(line);
    }

    /**
     * Prints out usage information for the command.
     */
    public abstract void usage();

    /**
     * Run the implementing command.
     *
     * @param line
     *         the {@link org.apache.commons.cli.CommandLine} containing options and arumgents.
     * @return 0 if successful
     * @throws Exception
     */
    protected abstract int run(final CommandLine line) throws Exception;

    /**
     * Returns the name of the command.
     */
    public abstract String getName();

}
