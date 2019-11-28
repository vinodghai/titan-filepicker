package filepicker.imageviewer.model;

import filepicker.base.BaseMediaModel;

/**
 * Created by VINOD KUMAR on 3/27/2019.
 */
public class MediaImage extends BaseMediaModel {

    private boolean isChecked;

    public MediaImage(String name, String path) {
        super(name, path);
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
