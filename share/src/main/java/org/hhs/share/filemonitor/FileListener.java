package org.hhs.share.filemonitor;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileListener implements FileAlterationListener{
    private Logger loggerInfo = LoggerFactory.getLogger("RollingFile-normal");
    private Logger loggerError = LoggerFactory.getLogger("RollingFileErr");

    Map<String, Long> stringLongMap = new ConcurrentHashMap(8);

    @Override
    public void onStart(FileAlterationObserver fileAlterationObserver) {

    }

    @Override
    public void onDirectoryCreate(File file) {

    }

    @Override
    public void onDirectoryChange(File file) {

    }

    @Override
    public void onDirectoryDelete(File file) {

    }

    @Override
    public void onFileCreate(File file) {

    }

    @Override
    public void onFileChange(File file) {
        String str = getString(file);
        System.out.println(str);
    }

    @Override
    public void onFileDelete(File file) {

    }

    @Override
    public void onStop(FileAlterationObserver fileAlterationObserver) {
    }

    public String getString(File file){
        String str = file.getName().substring(0,file.getName().lastIndexOf("."));
        Long start = stringLongMap.get(str);
        if (start == null){
            start = 0l;
        }
        Long length = file.length();
        stringLongMap.put(str, length-1);
        String result = "";
        RandomAccessFile randomAccessFile = null;
        byte[] bytes = new byte[(int) (length-start)];
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            randomAccessFile.seek(start.intValue());
            randomAccessFile.read(bytes);
            result = new String(bytes,"GBK");
        } catch (FileNotFoundException e) {
            loggerError.error("io Exception", e);
        } catch (IOException e) {
            loggerError.error("io Exception", e);
        }finally {
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                loggerError.error("io Exception", e);
            }
        }
        return result.trim();
    }
}
