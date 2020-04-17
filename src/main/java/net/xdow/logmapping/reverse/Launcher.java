package net.xdow.logmapping.reverse;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPResult;
import com.martiansoftware.jsap.Switch;
import net.xdow.logmapping.reverse.util.StringUtils;

public class Launcher {

    private static String getVersionMessage() {
        return "Log Mapping Reverse Processor version 1.0.0";
    }

    public static void main(String[] args) throws Exception {

        JSAP jsap = new JSAP();

        jsap.registerParameter(new FlaggedOption("input-log")
                .setStringParser(JSAP.STRING_PARSER)
                .setRequired(false)
                .setShortFlag('i')
                .setLongFlag("input-log")
                .setHelp("input log file"));

        jsap.registerParameter(new FlaggedOption("output-log")
                .setStringParser(JSAP.STRING_PARSER)
                .setRequired(false)
                .setShortFlag('o')
                .setLongFlag("output-log")
                .setHelp("output destination file"));

        jsap.registerParameter(new FlaggedOption("mapping-file")
                .setStringParser(JSAP.STRING_PARSER)
                .setRequired(true)
                .setShortFlag('m')
                .setLongFlag("mapping-file")
                .setHelp("mapping.txt destination file"));

        jsap.registerParameter(new Switch("help").setShortFlag('h').setLongFlag("help").setDefault("false"));

        JSAPResult arguments = jsap.parse(args);
        if (!arguments.success()) {
            // print out specific error messages describing the problems
            for (java.util.Iterator<?> errs = arguments.getErrorMessageIterator(); errs.hasNext(); ) {
                System.err.println("Error: " + errs.next());
            }
            System.err.println();
        }
        if (!arguments.success() || arguments.getBoolean("help")) {
            System.err.println(getVersionMessage());
            System.err.println("Reverse log mapping for LogMapping");
            System.err.println("Copyright (C) 2020 by Jason Eric");
            System.err.println("Web site: https://github.com/eritpchy/log-mapping-reverse");
            System.err.println();
            System.err.println("Usage: java -jar <launcher name> [option(s)]");
            System.err.println();
            System.err.println("Options : ");
            System.err.println();
            System.err.println(jsap.getHelp());
            System.exit(-1);
        }

        String inputLogFilePath = arguments.getString("input-log");
        String outputLogFilePath = arguments.getString("output-log");
        String mappingFilePath = arguments.getString("mapping-file");
        if (!StringUtils.isEmpty(inputLogFilePath) && !StringUtils.isEmpty(outputLogFilePath)) {
            LogReverse.reverseFromFile(mappingFilePath, inputLogFilePath, outputLogFilePath);
        } else {
            LogReverse.reverseFromStdin(mappingFilePath);
        }
    }
}
