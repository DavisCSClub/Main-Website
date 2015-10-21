package org.dcsc.core.model.carousel;

import javax.persistence.*;

/**
 * Created by tktong on 7/31/2015.
 */
@Entity
@Table(name = "dcsc_carousel", schema = "public")
public class CarouselBanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "caption")
    private String caption;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "relative_path", nullable = false)
    private String relativePath;

    @Column(name = "absolute_path", nullable = false)
    private String absolutePath;

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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }
}
