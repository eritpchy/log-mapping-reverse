package net.xdow.logmapping.reverse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.xdow.logmapping.reverse.bean.MappingInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogReverse {

    private static final Pattern LOG_PATTERN = Pattern.compile("\t@(-*\\d+)");

    public static void reverseFromFile(String mappingFilePath, String inputLogFilePath, String outputLogFilePath) throws IOException {
        Map<String, MappingInfo> mappingData = readMappingFile(mappingFilePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputLogFilePath)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputLogFilePath)));
        reverse(mappingData, br, bw);
    }

    public static void reverseFromStdin(String mappingFilePath) throws IOException {
        Map<String, MappingInfo> mappingData = readMappingFile(mappingFilePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        reverse(mappingData, br, bw);
    }

    private static void reverse(Map<String, MappingInfo> mappingData, BufferedReader inputReader, Writer writer) throws IOException {
        String line;
        while ((line = inputReader.readLine()) != null) {
            String reverseLog = reverseLine(mappingData, line);
            writer.append(reverseLog);
            writer.write('\n');
            writer.flush();
        }
        writer.flush();
    }

    private static String reverseLine(Map<String, MappingInfo> mappingData, String line) {
        StringBuffer sb = new StringBuffer();
        final Matcher matcher = LOG_PATTERN.matcher(line);
        while (matcher.find()) {
            String match = matcher.group(0);
            String key = matcher.group(1);
            String replacement = Optional.ofNullable(mappingData.get(key)).map(v -> "\t" + v.value + "\t").orElse(match);
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);
        String newLine = sb.toString();
        if (!newLine.equals(line)) {
            newLine = newLine.replaceAll("\t+", "\t");
        }
        return newLine;
    }

    private static Map<String, MappingInfo> readMappingFile(String mappingFilePath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(mappingFilePath));
        return new Gson().fromJson(new String(bytes), new TypeToken<Map<String, MappingInfo>>() {
        }.getType());
    }

}
