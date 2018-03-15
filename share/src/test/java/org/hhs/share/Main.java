package org.hhs.share;

import org.hhs.share.filemonitor.FileListener;
import org.hhs.share.filemonitor.FileMonitor;

import java.io.*;
import java.nio.file.*;

public class Main {
    public static void main(String...args) throws Exception {

        new Thread(new Runnable() {
            String line = System.getProperty("line.separator");
            public void run() {
                File file = new File("D:\\2018\\test.txt");
                if (!file.exists()){
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(file, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int i = 0;
                String content = "hello";
                while (true){
                    try {
                        fileWriter.write(content+i+line);
                        fileWriter.flush();
                        i++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
//        WatchService watchService = FileSystems.getDefault().newWatchService();
//        Paths.get("D:\\2018").register(watchService,
//                                                    StandardWatchEventKinds.ENTRY_CREATE,
//                                                    StandardWatchEventKinds.ENTRY_DELETE,
//                                                    StandardWatchEventKinds.ENTRY_MODIFY);
//
//        while (true){
//            WatchKey key = watchService.take();
//            for (WatchEvent<?> event:key.pollEvents()){
//                System.out.println(event.context()+"发生了"+event.kind()+"事件");
//            }
//            key.reset();
//        }
        FileMonitor m = new FileMonitor(1000);
        m.monitor("D:\\2018", new FileListener());
        m.start();
    }



}
