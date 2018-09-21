package com.livelabdrools.model;

import java.util.List;

public class Data {

    private List<String[]> header;

    private List<String[]> data;

    public List<String[]> getHeader() {
        return header;
    }

    public void setHeader(List<String[]> header) {
        this.header = header;
    }

    public List<String[]> getData() {
        return data;
    }

    public void setData(List<String[]> data) {
        this.data = data;
    }
}
