package org.hhs;

import junit.framework.TestCase;
import org.hhs.utils.LoadClassName;
import org.hhs.vo.Cpu;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    public void testPackage() throws IOException {
        String str = "Linux 3.10.0-693.2.2.el7.x86_64 (iZwz9eocwwx9ccud20naq5Z) \t03/07/2018 \t_x86_64_\t(2 CPU)\n" +
                "\n" +
                "03:38:11 PM  CPU    %usr   %nice    %sys %iowait    %irq   %soft  %steal  %guest  %gnice   %idle\n" +
                "03:38:11 PM  all    1.10    0.00    1.12    0.06    0.00    0.01    0.00    0.00    0.00   97.71\n" +
                "03:38:11 PM    0    1.10    0.00    1.12    0.05    0.00    0.01    0.00    0.00    0.00   97.73\n" +
                "03:38:11 PM    1    1.11    0.00    1.12    0.07    0.00    0.01    0.00    0.00    0.00   97.69";
        String[] strCPu = str.split("\n");
        Map<String, String> paramsMap = new HashMap<String, String>();
        for (int i = 0; i < strCPu.length; i++){
            if (i <= 1){
                continue;
            } if (i == 2){
                paramsMap.put(strCPu[i], null);
            }else {
                split(strCPu[i]);
            }
        }
    }

    public Map<String, String> initParamMap(String str){

        return null;
    }

    public void split(String str){
        String strs[] = str.split("\\s+");
        Map<String, String> tempMap = new HashMap<String, String>();
        Cpu cpu = new Cpu();
        for (int i = 0; i < strs.length; i++){
            if (i <= 1){
                continue;
            } else {
//                tempMap.put();
            }
        }
    }
}
