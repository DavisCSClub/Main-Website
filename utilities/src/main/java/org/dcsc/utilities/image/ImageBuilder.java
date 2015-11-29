package org.dcsc.utilities.image;

public class ImageBuilder {
    private long id;
    private String name;
    private String description;
    private String absolutePath;
    private String relativePath;

    public ImageBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public ImageBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ImageBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ImageBuilder setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
        return this;
    }

    public ImageBuilder setRelativePath(String relativePath) {
        this.relativePath = relativePath;
        return this;
    }

    public Image build() {
        return new Image(id, name, description, absolutePath, relativePath);
    }
}