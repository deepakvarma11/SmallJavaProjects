package com.LogAnalyzer;

import java.util.List;

public class LogAnalyzerMain {
//    Build a log analyzer to work with files, timestamps, and patterns

    public static void main(String[] args) {

        ParserUtil parserUtil = new ParserUtil("ERROR|WARN", "yyyy-MM-dd HH:mm:ss");
        List<LogEntry> entryList = parserUtil.analyse("app.log");

        entryList.forEach(System.out::println);

    }
}
