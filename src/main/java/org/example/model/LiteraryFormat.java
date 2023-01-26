package org.example.model;

public class LiteraryFormat {
    private long id;
    private String format;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "LiteraryFormat{" +
                "id=" + id +
                ", format='" + format + '\'' +
                '}';
    }
}
