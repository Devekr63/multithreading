package org.example.executors;

public enum ThreadColor {
    ANSI_RESET("\u001B[0m"),
    ANSI_RED("\u001b[31m"),
    ANSI_BLACK("\u001B[30m"),
    ANSI_WHITE("\u001B[37m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_YELLOW("\u001b[33m"),
    ANSI_BLUE("\u001B[34m"),
    ANSI_CYAN("\u001B[36m");

    private final String color;

    ThreadColor(String color) {
        this.color = color;
    }

    public String color() {
        return color;
    }
}
