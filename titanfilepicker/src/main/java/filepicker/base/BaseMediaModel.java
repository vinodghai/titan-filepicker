package filepicker.base;

/**
 * Created by VINOD KUMAR on 3/27/2019.
 */
public class BaseMediaModel {

    protected String name;
    protected String path;

    public BaseMediaModel(String name, String path) {

        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
