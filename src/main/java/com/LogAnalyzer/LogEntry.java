package com.LogAnalyzer;

import java.time.LocalDateTime;

public class LogEntry {

    private final LocalDateTime timeStamp;
    private final String logger;
    private final String message;

    public LogEntry(LocalDateTime timeStamp, String logger, String message) {
        this.timeStamp = timeStamp;
        this.logger = logger;
        this.message = message;
//        this.message = message.replace(logger,"").trim();
    }

    public String toString() {
        return timeStamp + ": " + logger + " -> " + message;
    }
}
