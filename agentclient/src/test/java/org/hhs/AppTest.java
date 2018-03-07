package org.hhs;

import junit.framework.TestCase;
import org.hhs.vo.Cpu;

import java.io.IOException;
import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest<T> extends TestCase {
    public void testPackage() throws IOException {
        String strCpu = "Linux 3.10.0-693.2.2.el7.x86_64 (iZwz9eocwwx9ccud20naq5Z) \t03/07/2018 \t_x86_64_\t(2 CPU)\n" +
                "\n" +
                "03:38:11 PM  CPU    %usr   %nice    %sys %iowait    %irq   %soft  %steal  %guest  %gnice   %idle\n" +
                "03:38:11 PM  all    1.10    0.00    1.12    0.06    0.00    0.01    0.00    0.00    0.00   97.71\n" +
                "03:38:11 PM    0    1.10    0.00    1.12    0.05    0.00    0.01    0.00    0.00    0.00   97.73\n" +
                "03:38:11 PM    1    1.11    0.00    1.12    0.07    0.00    0.01    0.00    0.00    0.00   97.69";

        String strDh = "Filesystem      Size  Used Avail Use%25 Mounted on\n" +
                        "/dev/vda1        40G  3.3G   34G   9%25 /\n" +
                        "devtmpfs        1.9G     0  1.9G   0%25 /dev\n" +
                        "tmpfs           1.9G     0  1.9G   0%25 /dev/shm\n" +
                        "tmpfs           1.9G  528K  1.9G   1%25 /run\n" +
                        "tmpfs           1.9G     0  1.9G   0%25 /sys/fs/cgroup\n" +
                        "tmpfs           380M     0  380M   0%25 /run/user/0\n" +
                        "tmpfs           380M     0  380M   0%25 /run/user/1000";
        String strDi = "Filesystem      Inodes IUsed   IFree IUse%25 Mounted on\n" +
                "/dev/vda1      2621440 46280 2575160    2%25 /\n" +
                "devtmpfs        482632   328  482304    1%25 /dev\n" +
                "tmpfs           485211     1  485210    1%25 /dev/shm\n" +
                "tmpfs           485211   427  484784    1%25 /run\n" +
                "tmpfs           485211    16  485195    1%25 /sys/fs/cgroup\n" +
                "tmpfs           485211     1  485210    1%25 /run/user/0\n" +
                "tmpfs           485211     1  485210    1%25 /run/user/1000";
        String iostat = "Linux 3.10.0-693.2.2.el7.x86_64 (iZwz9eocwwx9ccud20naq5Z) \t03/07/2018 \t_x86_64_\t(2 CPU)\n" +
                "\n" +
                "avg-cpu:  %user   %nice %system %iowait  %steal   %idle\n" +
                "           1.08    0.00    1.10    0.06    0.00   97.76\n" +
                "\n" +
                "Device:         rrqm/s   wrqm/s     r/s     w/s    rkB/s    wkB/s avgrq-sz avgqu-sz   await r_await w_await  svctm  %util\n" +
                "vda               0.00     3.31    0.02    6.10     0.90    43.62    14.54     0.14   22.22    3.01   22.30   0.48   0.30\n";

        String[] strs = strDh.replace("%25","%").replace("Mounted on","Mounted-on").split("\n");
        String[] temps = iostat.split("\n");
        String[] st = {temps[temps.length-2],temps[temps.length-1]};
        System.out.println(initParamMap(st));
    }

    private Map<String, List<String>> initParamMap(String[] str) {
        Map<String, List<String>> strMap = new LinkedHashMap<String, List<String>>();
        for (int i = 0; i < str.length; ++i) {
            if (str[i] == null) {
                continue;
            }
            if (i == 0) {
                String strs[] = str[i].split("\\s+");
                for (String strTemp : strs) {
                    strMap.put(strTemp.toLowerCase(), new ArrayList<String>());
                }
            } else {
                String strs[] = str[i].split("\\s+");
                Iterator<String> it = strMap.keySet().iterator();
                for (int k = 0; it.hasNext(); k++) {
                    String key = it.next();
                    List<String> list = strMap.get(key);
                    list.add(strs[k]);
                }
            }
        }
        return strMap;
    }
}
