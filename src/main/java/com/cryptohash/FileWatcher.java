//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cryptohash;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.util.Iterator;

public class FileWatcher {
    public FileWatcher() {
    }

    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("/home/nilesh.chordia/idea/filespractice/windows");
        WatchService watchService = FileSystems.getDefault().newWatchService();
        dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

        while(true) {
            try {
                WatchKey key = watchService.take();
                Iterator var4 = key.pollEvents().iterator();

                while(var4.hasNext()) {
                    WatchEvent<?> watchEvents = (WatchEvent)var4.next();
                    Kind<?> kind = watchEvents.kind();
                    if (kind != StandardWatchEventKinds.OVERFLOW) {
                        Path fileName = (Path)watchEvents.context();
                        Path child = dir.resolve(fileName);
                        System.out.println(child.toAbsolutePath() + "  " + watchEvents.kind());
                    }
                }

                boolean valid = key.reset();
                if (!valid) {
                    return;
                }
            } catch (InterruptedException var10) {
                var10.printStackTrace();
            }
        }
    }
}
