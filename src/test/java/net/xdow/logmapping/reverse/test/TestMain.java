package net.xdow.logmapping.reverse.test;

import net.xdow.logmapping.reverse.Launcher;

public class TestMain {

    public static void main(String[] args) throws Exception {
        Launcher.main(new String[]{
                "--input-log", "/Users/jason/Downloads/1.458/proc/1/log_20200421.xlog.log",
                "--output-log", "/tmp/2.txt",
                "--mapping-file", "/Users/jason/Eric/project/Xscript/app/release/Xscript/mapping/Xscript_3.9504_release.signed.apk.log.mapping.txt",
        });
    }
}
