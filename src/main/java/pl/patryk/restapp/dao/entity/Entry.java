package pl.patryk.restapp.dao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author Patryk Kurzeja
 */
@Entity
public class Entry {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String fileName;

    private LocalDateTime uploadTime;

    private String newspaperName;

    private int width;

    private int height;

    private int dpi;

    public Entry() {
    }

    public Entry(String fileName, LocalDateTime uploadTime, String newspaperName, int width, int height, int dpi) {
        this.fileName = fileName;
        this.uploadTime = uploadTime;
        this.newspaperName = newspaperName;
        this.width = width;
        this.height = height;
        this.dpi = dpi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getNewspaperName() {
        return newspaperName;
    }

    public void setNewspaperName(String newspaperName) {
        this.newspaperName = newspaperName;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }
}
