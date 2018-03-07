package org.hhs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

public class LoadClassName {
    private static Logger logger = LoggerFactory.getLogger("RollingFile-normal");

    public  List<String> getStringListLinux(){
        List<String> stringList = new ArrayList();
        String url = this.getClass().getResource("/").getFile();
        System.out.println(url);
        File file = new File(url);
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(file);
        } catch (IOException e) {
            logger.error("init jarFile error", e);
        }
        Enumeration<JarEntry> entries = jarFile.entries();
        logger.info(entries.toString());
        while (entries.hasMoreElements()){
            JarEntry jarEntry = entries.nextElement();
            if (jarEntry.getName().startsWith("org/hhs/monitor/")&&!"org/hhs/monitor/".equals(jarEntry.getName())) {
                stringList.add(jarEntry.getName().replace("/","."));
            }
        }
        return stringList;
    }

    //根据包名，获取类的全路径名称:中间用.隔开
    public List<String> getStringList(String packageName){
//        String packageName = "org.hhs.monitor";
        List<String> classNames = null;
        try {
            classNames = getClassName(packageName, false);
        } catch (Exception e) {
            logger.error("loadClassName error", e);
        }
        return classNames;
    }

    public  List<String> getStringListWindow(){
        String packageName = this.getClass().getResource("/org/hhs/monitor").getPath();
        List<String> stringList = new ArrayList<String>();
        File fileDir = new File(packageName);
        String clazzName = null;
        if (fileDir.isDirectory()){
            for (File file:fileDir.listFiles()){
                clazzName = file.getName().substring(0,file.getName().indexOf("."));
                stringList.add("org.hhs.monitor."+clazzName);
            }
        }
        return stringList;
    }

    private static List<String> getClassNameByFile(String filePath, List<String> className, boolean childPackage) {
        List<String> myClassName = new ArrayList<String>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                if (childPackage) {
                    myClassName.addAll(getClassNameByFile(childFile.getPath(), myClassName, childPackage));
                }
            } else {
                String childFilePath = childFile.getPath();
                if (childFilePath.endsWith(".class")) {
                    childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9, childFilePath.lastIndexOf("."));
                    childFilePath = childFilePath.replace("\\", ".");
                    myClassName.add(childFilePath);
                }
            }
        }

        return myClassName;
    }

    public static List<String> getClassName(String packageName, boolean childPackage) throws Exception {
        List<String> fileNames = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replace(".", "/");
        URL url = loader.getResource(packagePath);
        if (url != null) {
            String type = url.getProtocol();
            if ("file".equals(type)) {
                fileNames = getClassNameByFile(url.getPath(), null, childPackage);
            } else if (("jar").equals(type)) {
                fileNames = getClassNameByJar(url.getPath(), childPackage);
            }
        } else {
            fileNames = getClassNameByJars(((URLClassLoader) loader).getURLs(), packagePath, childPackage);
        }
        return fileNames;
    }

    private static List<String> getClassNameByJars(URL[] urls, String packagePath, boolean childPackage) throws Exception {
        List<String> myClassName = new ArrayList<String>();
        if (urls != null) {
            for (int i = 0; i < urls.length; i++) {
                URL url = urls[i];
                String urlPath = url.getPath();
                // 不必搜索classes文件夹
                if (urlPath.endsWith("classes/")) {
                    continue;
                }
                String jarPath = urlPath + "!/" + packagePath;
                myClassName.addAll(getClassNameByJar(jarPath, childPackage));
            }
        }
        return myClassName;
    }

    private static List<String> getClassNameByJar(String jarPath, boolean childPackage) throws Exception {
        List<String> myClassName = new ArrayList<String>();
        String[] jarInfo = jarPath.split("!");
        String jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf("/"));
        String packagePath = jarInfo[1].substring(1);
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(jarFilePath);
            Enumeration<JarEntry> entrys = jarFile.entries();
            while (entrys.hasMoreElements()) {
                JarEntry jarEntry = entrys.nextElement();
                String entryName = jarEntry.getName();
                if (entryName.endsWith(".class")) {
                    if (childPackage) {
                        if (entryName.startsWith(packagePath)) {
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
                            myClassName.add(entryName);
                        }
                    } else {
                        int index = entryName.lastIndexOf("/");
                        String myPackagePath;
                        if (index != -1) {
                            myPackagePath = entryName.substring(0, index);
                        } else {
                            myPackagePath = entryName;
                        }
                        if (myPackagePath.equals(packagePath)) {
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
                            myClassName.add(entryName);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }finally{
            if(null != jarFile){
                jarFile.close();
            }
        }
        return myClassName;
    }
}
