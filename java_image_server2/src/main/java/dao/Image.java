package dao;

/**
 * Create with Darcula IDEA
 * Description:
 *
 * @Author CJP
 * @Date 2020/6/10
 * @Time 21:24
 */
public class Image {
    private int imageId;
    private String imageName;
    private  int size;
    private String uploadTime;
    private String contenType;
    private String path;
    private String md5;
//Alt+Insert
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getContenType() {
        return contenType;
    }

    public void setContenType(String contenType) {
        this.contenType = contenType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId=" + imageId +
                ", imageName='" + imageName + '\'' +
                ", size=" + size +
                ", uploadTime='" + uploadTime + '\'' +
                ", contenType='" + contenType + '\'' +
                ", path='" + path + '\'' +
                ", md5='" + md5 + '\'' +
                '}';
    }
}
