package net.xdow.logmapping.reverse.test;

import net.xdow.logmapping.reverse.Launcher;

public class TestMain {

    public static void main(String[] args) throws Exception {
        Launcher.main(new String[]{
                "--input-log", "/tmp/1.txt",
                "--output-log", "/tmp/2.txt",
                "--mapping-file", "/tmp/log.mapping.txt",
        });
    }
}
