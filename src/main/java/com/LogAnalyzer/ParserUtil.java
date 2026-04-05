package com.LogAnalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserUtil {

    private final Pattern logPattern;
    private final DateTimeFormatter formatter;

    public ParserUtil(String regex, String timeFormat) {
        logPattern = Pattern.compile(regex);
        formatter = DateTimeFormatter.ofPattern(timeFormat);
    }

    public List<LogEntry> analyse(String filePath) {
        List<LogEntry> logEntries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LogEntry entry = parseLine(line);
                if (!Objects.isNull(entry))
                    logEntries.add(entry);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return logEntries;
    }

    public LogEntry parseLine(String line) {
        // Example: "2026-04-05 12:30:45 ERROR Something failed"
        try {
            String[] parts = line.split(" ", 4);
            String dateTime = parts[0] + " " + parts[1];
            String message = parts[3];
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);

            Matcher matcher = logPattern.matcher(line);
            if (matcher.find()) {
                return new LogEntry(localDateTime, matcher.group(), message);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }
}
