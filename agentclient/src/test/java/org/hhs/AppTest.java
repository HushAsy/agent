package org.hhs;

import junit.framework.TestCase;
import org.hhs.parse.NetParse;
import org.hhs.vo.Cpu;
import org.hhs.vo.Mem;
import org.hhs.vo.Net;

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
        String memStr = "MemTotal:        3881692 kB\n" +
                "MemFree:         1757332 kB\n" +
                "MemAvailable:    3211652 kB\n" +
                "Buffers:          153200 kB\n" +
                "Cached:          1414512 kB\n" +
                "SwapCached:            0 kB\n" +
                "Active:          1259252 kB\n" +
                "Inactive:         626948 kB\n" +
                "Active(anon):     318840 kB\n" +
                "Inactive(anon):      328 kB\n" +
                "Active(file):     940412 kB\n" +
                "Inactive(file):   626620 kB\n" +
                "Unevictable:           0 kB\n" +
                "Mlocked:               0 kB\n" +
                "SwapTotal:             0 kB\n" +
                "SwapFree:              0 kB\n" +
                "Dirty:               400 kB\n" +
                "Writeback:             0 kB\n" +
                "AnonPages:        318484 kB\n" +
                "Mapped:            74316 kB\n" +
                "Shmem:               684 kB\n" +
                "Slab:             189684 kB\n" +
                "SReclaimable:     176524 kB\n" +
                "SUnreclaim:        13160 kB\n" +
                "KernelStack:        3264 kB\n" +
                "PageTables:         5532 kB\n" +
                "NFS_Unstable:          0 kB\n" +
                "Bounce:                0 kB\n" +
                "WritebackTmp:          0 kB\n" +
                "CommitLimit:     1940844 kB\n" +
                "Committed_AS:    1152540 kB\n" +
                "VmallocTotal:   34359738367 kB\n" +
                "VmallocUsed:       14264 kB\n" +
                "VmallocChunk:   34359715580 kB\n" +
                "HardwareCorrupted:     0 kB\n" +
                "AnonHugePages:    231424 kB\n" +
                "HugePages_Total:       0\n" +
                "HugePages_Free:        0\n" +
                "HugePages_Rsvd:        0\n" +
                "HugePages_Surp:        0\n" +
                "Hugepagesize:       2048 kB\n" +
                "DirectMap4k:       44928 kB\n" +
                "DirectMap2M:     3100672 kB\n" +
                "DirectMap1G:     3145728 kB";
//        String[] strs = strDh.replace("%25","%").replace("Mounted on","Mounted-on").split("\n");
//        String[] temps = iostat.split("\n");
//        String[] st = {temps[temps.length-2],temps[temps.length-1]};
//        System.out.println(initParamMap(st));
        String net = "#kernel\n" +
                "Interface        RX Pkts/Rate    TX Pkts/Rate    RX Data/Rate    TX Data/Rate  \n" +
                "                 RX Errs/Drop    TX Errs/Drop    RX Over/Rate    TX Coll/Rate  \n" +
                "lo                     0 0             0 0             0 0             0 0      \n" +
                "                       0 0             0 0             0 0             0 0      \n" +
                "eth0                   0 0             0 0             0 0             0 0      \n" +
                "                       0 0             0 0             0 0             0 0      \n" +
                "eth1                   2 0             1 0           184 0           118 0      \n" +
                "                       0 0             0 0             0 0             0 0";
        String[] str = net.split("\n");
        List<String> lists = new ArrayList<String>(Arrays.asList(str));
        lists.remove(0);

        List<String> listPkts = new ArrayList<String>();
        List<String> listErrs = new ArrayList<String>();
        String head = null;
        for (int i = 0; i < lists.size(); i++) {
            if (i%2 == 0) {
                head = lists.get(i).split("\\s+")[0];
                listPkts.add(lists.get(i));
            }else{
                listErrs.add(head+" "+lists.get(i));
            }
        }
        Object[]  strings = listPkts.toArray();
        System.out.println(initParamMap(objToStr(strings)));
        Object[]  strings1 = listErrs.toArray();
        System.out.println(initParamMap(objToStr(strings1)));
        NetParse netParse = new NetParse();
        List<Net> nets = netParse.getNetList(net);
        System.out.println(nets.toString());

//        System.out.println(initParamMap((String[]) listPkts.toArray()));
//        System.out.println(initParamMap((String[]) listErrs.toArray()));
    }

    private String[] objToStr(Object[] objects){
        String[] strings = new String[objects.length];
        for (int i = 0; i < objects.length; i++){
            strings[i] = objects[i].toString();
        }
        return strings;
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
                    if(strMap.get(strTemp.toLowerCase()) == null) {
                        strMap.put(strTemp.toLowerCase(), new ArrayList<String>());
                    }else {
                        strMap.put(strTemp, new ArrayList<String>());
                    }
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
