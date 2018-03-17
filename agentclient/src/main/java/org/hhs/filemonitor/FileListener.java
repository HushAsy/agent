package org.hhs.filemonitor;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.hhs.config.Common;
import org.hhs.nettyClient.ClientBootStrap;
import org.hhs.share.MessageBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;

public class FileListener implements FileAlterationListener{
    private Logger loggerInfo = LoggerFactory.getLogger("RollingFile-normal");
    private Logger loggerError = LoggerFactory.getLogger("RollingFileErr");

    private ClientBootStrap clientBootStrap;

    public FileListener(ClientBootStrap clientBootStrap){
        this.clientBootStrap = clientBootStrap;
    }

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
        String head = file.getName().substring(0,file.getName().lastIndexOf("."));
        if (!head.toLowerCase().startsWith("agent")){
            Long start = Common.stringLongMap.get(head);
            String changeStr = getString(file, start, head);
            List<String> stringList = Arrays.asList(changeStr.split("\r\n"));
            MessageBody messageBody = new MessageBody();
            messageBody.setHead(head);
            messageBody.setBody(stringList);
            System.out.println(messageBody.toString());
            clientBootStrap.getSocketChannel().writeAndFlush(messageBody);
        }
    }

    @Override
    public void onFileDelete(File file) {

    }

    @Override
    public void onStop(FileAlterationObserver fileAlterationObserver) {

    }

    public String getString(File file, Long start, String head){
        if (start == null){
            start = 0l;
        }
        Long length = file.length();
        Common.stringLongMap.put(head, length-1);
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
