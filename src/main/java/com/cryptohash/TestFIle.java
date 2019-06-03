//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cryptohash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.DirectoryStream.Filter;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class TestFIle {
    public TestFIle() {
    }

    @Test
    public void path_test() {
        Path path = Paths.get("/home/nilesh.chordia/idea/filespractice/windows/license.txt");
        Assertions.assertThat(Files.exists(path, new LinkOption[0]));
        path = Paths.get("home", "nilesh.chordia", "idea", "filespractice", "windows", "license.txt");
        Assertions.assertThat(Files.exists(path, new LinkOption[0]));
        path = Paths.get("home", "nilesh.chordia", "idea", "filespractice", "windows").resolve("license.txt");
        Assertions.assertThat(Files.exists(path, new LinkOption[0]));
    }

    @Test
    public void can_read_path() throws IOException {
        Path path = Paths.get("/home/nilesh.chordia/idea/filespractice/windows/license.txt");
        Assertions.assertThat(Files.exists(path, new LinkOption[0]));
        String s = new String(Files.readAllBytes(path), StandardCharsets.ISO_8859_1);
        Assertions.assertThat(s).isEqualTo("This is grand license");
    }

    @Test
    public void can_write_path() throws IOException {
        Path path = Paths.get("/home/nilesh.chordia/idea/filespractice/windows/readme.txt");
        Assertions.assertThat(Files.exists(path, new LinkOption[0]));
        Files.write(path, "This is jacket".getBytes(), new OpenOption[0]);
    }

    @Test
    public void move() throws IOException {
        Path path = Paths.get("/home/nilesh.chordia/idea/filespractice/windows/readme.txt");
        Files.move(path, Paths.get("/home/nilesh.chordia/idea/filespractice/windows/version/readme.txt"), StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    public void file_walk() throws IOException {
        Path path = Paths.get("/home/nilesh.chordia/idea/filespractice/windows/");
        Stream var10000 = Files.walk(path);
        PrintStream var10001 = System.out;
        var10000.forEach(var10001::println);
    }

    @Test
    public void move_and_deletes_file() throws Exception {
        Path path = Paths.get("/home/nilesh.chordia/idea/filespractice/windows/readme.txt");
        Files.walk(path).sorted(Comparator.reverseOrder()).forEach((p) -> {
            try {
                Files.delete(p);
            } catch (IOException var2) {
                var2.printStackTrace();
            }

        });
    }

    @Test
    public void paths() throws Exception {
        Path absolutePath = Paths.get("/home/nilesh.chordia/idea/filespractice/windows/readme.txt");
        Path relativePath = Paths.get("./windows/../windows/readme.txt");
        Assertions.assertThat(Files.exists(relativePath, new LinkOption[0])).isTrue();
        Assertions.assertThat(relativePath.isAbsolute()).isFalse();
        System.out.println("relativePath = " + relativePath);
        System.out.println("relativePath = " + relativePath.toAbsolutePath());
        System.out.println("relativePath.normalize().toAbsolutePath() = " + relativePath.normalize().toAbsolutePath());
        System.out.println("absolutePath = " + Paths.get("/home").relativize(absolutePath));
    }

    @Test
    public void list_files() throws IOException {
        Path path1 = Paths.get("/home/nilesh.chordia/idea/filespractice/windows/");
        Stream var10000 = Files.list(path1);
        PrintStream var10001 = System.out;
        var10000.forEach(var10001::println);
        System.out.println("--------------------");
        DirectoryStream var2 = Files.newDirectoryStream(path1, (path) -> {
            return Files.isRegularFile(path, new LinkOption[0]) && path.toString().endsWith(".txt");
        });
        var10001 = System.out;
        var2.forEach(var10001::println);
        System.out.println("---------------------");
        var10000 = Files.walk(path1);
        var10001 = System.out;
        var10000.forEach(var10001::println);
    }

    @Test
    public void read_file() throws IOException {
        Path path = Paths.get("/home/nilesh.chordia/idea/filespractice/windows/license.txt");
        InputStream is = Files.newInputStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        System.out.println(bufferedReader.readLine());
    }

    @Test
    public void create_temp_files() throws IOException {
        Path path = Paths.get("/home/nilesh.chordia/idea/filespractice/windows/");
        Path tempFile1 = Files.createTempFile((String)null, ".jpeg");
        System.out.println(tempFile1);
        Path tempFile2 = Files.createTempFile(path, "null", ".jpeg");
        System.out.println(tempFile2);
    }
}
