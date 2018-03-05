package org.hhs.config;

public enum Config {
    cpu("mpstat -P ALL"),mem("cat /proc/meminfo"),;
    private String command;
    private Config(String command){
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
