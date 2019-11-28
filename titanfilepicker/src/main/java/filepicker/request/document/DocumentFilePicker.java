package filepicker.request.document;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import filepicker.base.BaseFilePicker;

/**
 * Created by VINOD KUMAR on 3/28/2019.
 */
public interface DocumentFilePicker extends BaseFilePicker {


    void pickDocument(@NonNull Activity activity);

    void pickDocument(@NonNull Fragment fragment);
}
