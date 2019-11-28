package filepicker.request.image;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import filepicker.base.BaseFilePicker;

/**
 * Created by VINOD KUMAR on 3/28/2019.
 */
public interface ImageFilePicker extends BaseFilePicker {

    void pickImage(@NonNull Activity activity);

    void pickImage(@NonNull Fragment fragment);
}
