package filepicker.imageviewer.listener;

import java.util.List;

import filepicker.imageviewer.model.MediaImage;

/**
 * Created by VINOD KUMAR on 3/27/2019.
 */
public interface ImageSelectListener {

    void onImageCheckedChange(boolean isChecked);

    void onImageSelected(MediaImage mediaImage);

    void onImagesSelected(List<String> imageList);
}
