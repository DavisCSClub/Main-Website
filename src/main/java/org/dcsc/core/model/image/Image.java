package org.dcsc.core.model.image;

import javax.persistence.*;

@Entity
@Table(name = "dcsc_images", schema = "public")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "absolute_path")
    private String absolutePath;

    @Column(name = "relative_path")
    private String relativePath;

    public Image() {
    }

    public Image(long id, String name, String description, String absolutePath, String relativePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.absolutePath = absolutePath;
        this.relativePath = relativePath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }
}
