package com.systemdesign.logger;

import com.systemdesign.sinks.Appender;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.systemdesign.logger.LogLevel.*;

/**
 * @author sushil
 */
public class Logger {
    private static volatile Logger INSTANCE;
    private final List<Appender> appenders;
    private final ExecutorService executorService;
    private final static int QUEUE_CAPACITY = 100_000;

    public Logger() {
        this.appenders = new ArrayList<>();
        this.executorService = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(QUEUE_CAPACITY));
    }

    private void logAsync(Runnable task) {
        executorService.submit(task);
    }

    private String formatMessage(LogLevel level, String message) {
        return String.format("[%s] [%s] [%s] %s",
                new java.util.Date(), level, Thread.currentThread().getName(), message);
    }

    private static final class LoggerHolder {
        private static final Logger INSTANCE = new Logger();
    }

    public void fatal(String message) {
        logAsync(() -> this.appenders.forEach(appender -> appender.log(FATAL, formatMessage(FATAL, message))));
    }

    public void error(String message) {
        logAsync(() -> this.appenders.forEach(sink -> sink.log(ERROR, formatMessage(ERROR, message))));
    }

    public void warn(String message) {
        logAsync(() -> this.appenders.forEach(sink -> sink.log(WARN, formatMessage(WARN, message))));
    }

    public void info(String message) {
        logAsync(() -> this.appenders.forEach(sink -> sink.log(INFO, formatMessage(INFO, message))));
    }

    public void debug(String message) {
        logAsync(() -> this.appenders.forEach(sink -> sink.log(DEBUG, formatMessage(DEBUG, message))));
    }

    public void addSink(Appender appender) {
        this.appenders.add(appender);
    }

    public static Logger getInstance() {
        return LoggerHolder.INSTANCE;
    }
}