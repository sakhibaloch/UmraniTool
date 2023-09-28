package com.umrani.tool.view.main;

import java.io.File;

public class FileItem implements Comparable<FileItem> {
    private File file;
    private boolean isDirectory;

    public FileItem(File file, boolean b) {
        this.file = file;
        this.isDirectory = isDirectory;

    }

    public File getFile() {
        return file;
    }


    public boolean isDirectory() {
        return isDirectory;
    }

    public String getDisplayName() {
        return file.getName();
    }

    @Override
    public int compareTo(FileItem other) {
        if (this.isDirectory() && !other.isDirectory()) {
            return -1;
        } else if (!this.isDirectory() && other.isDirectory()) {
            return 1;
        } else {
            return this.getDisplayName().compareToIgnoreCase(other.getDisplayName());
        }
    }
}

