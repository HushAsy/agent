package org.hhs.parse;

import org.hhs.vo.Cpu;

public class CpuParse {
    public Cpu parStrToCpu(){
        String str = "Linux 3.10.0-693.2.2.el7.x86_64 (iZwz9eocwwx9ccud20naq5Z) \t03/07/2018 \t_x86_64_\t(2 CPU)\n" +
            "\n" +
            "03:38:11 PM  CPU    %usr   %nice    %sys %iowait    %irq   %soft  %steal  %guest  %gnice   %idle\n" +
            "03:38:11 PM  all    1.10    0.00    1.12    0.06    0.00    0.01    0.00    0.00    0.00   97.71\n" +
            "03:38:11 PM    0    1.10    0.00    1.12    0.05    0.00    0.01    0.00    0.00    0.00   97.73\n" +
            "03:38:11 PM    1    1.11    0.00    1.12    0.07    0.00    0.01    0.00    0.00    0.00   97.69";
        String[] strCPu = str.split("\n");
        return null;
    }
}
