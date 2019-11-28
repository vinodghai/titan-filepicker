package filepicker.docviewer.model;

import filepicker.base.BaseMediaModel;

/**
 * Created by VINOD KUMAR on 3/27/2019.
 */
public class MediaDocument extends BaseMediaModel {

    private String size;

    public MediaDocument(String name, String path, String size) {
        super(name, path);
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
