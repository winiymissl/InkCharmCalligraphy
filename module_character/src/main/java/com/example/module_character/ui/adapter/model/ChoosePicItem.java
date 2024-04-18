package com.example.module_character.ui.adapter.model;

import java.io.File;

/**
 * @Author winiymissl
 * @Date 2024-04-18 14:21
 * @Version 1.0
 */
public class ChoosePicItem {
    File file;

    String url;

    public ChoosePicItem(File file) {
        this.file = file;
    }

    public ChoosePicItem(String url) {
        this.url = url;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ChoosePicItem(File file, String url) {
        this.file = file;
        this.url = url;
    }
}
