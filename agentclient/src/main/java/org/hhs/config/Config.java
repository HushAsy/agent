package org.hhs.config;

public enum Config {
    cpu("mpstat -P ALL"),//cpu监控
    mem("cat /proc/meminfo"),//内存监控
    disk_df_h("df -h"),//磁盘使用监控
    disk_df_i("df -i"),//磁盘node监控
    iostat("iostat -x"),//io监控
    ifstat("ifstat");//网络监控

    private String command;

    private Config(String command){
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
