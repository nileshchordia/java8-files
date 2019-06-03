//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cryptohash;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class FileWatcherAlternative {
    private static String FOLDER = "/home/nilesh.chordia/idea/filespractice/windows";

    public FileWatcherAlternative() {
    }

    public static void main(String[] args) throws Exception {
        long pollingInterval = 5000L;
        File folder = new File(FOLDER);
        if (!folder.exists()) {
            throw new RuntimeException("Directory not found " + folder.getName());
        } else {
            FileAlterationObserver observer = new FileAlterationObserver(folder);
            FileAlterationMonitor monitor = new FileAlterationMonitor(5000L);
            FileAlterationListener listener = new FileAlterationListener() {
                public void onStart(FileAlterationObserver fileAlterationObserver) {
                }

                public void onDirectoryCreate(File file) {
                }

                public void onDirectoryChange(File file) {
                }

                public void onDirectoryDelete(File file) {
                }

                public void onFileCreate(File file) {
                    System.out.println("File is created " + file.getName());

                    try {
                        System.out.println(file.getCanonicalPath());
                    } catch (IOException var3) {
                        var3.printStackTrace();
                    }

                }

                public void onFileChange(File file) {
                    System.out.println("File is change " + file.getName());

                    try {
                        System.out.println(file.getCanonicalPath());
                    } catch (IOException var3) {
                        var3.printStackTrace();
                    }

                }

                public void onFileDelete(File file) {
                    System.out.println("File is delete " + file.getName());

                    try {
                        System.out.println(file.getCanonicalPath());
                    } catch (IOException var3) {
                        var3.printStackTrace();
                    }

                }

                public void onStop(FileAlterationObserver fileAlterationObserver) {
                }
            };
            observer.addListener(listener);
            monitor.addObserver(observer);
            monitor.start();
        }
    }
}
