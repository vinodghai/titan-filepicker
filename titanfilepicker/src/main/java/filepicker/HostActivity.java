package filepicker;

import android.view.View;

import androidx.annotation.IdRes;

import java.util.List;

import filepicker.base.BaseFragment;
import filepicker.request.ClientRequest;

/**
 * Created by VINOD KUMAR on 3/28/2019.
 */
public interface HostActivity {

    void registerFragment(BaseFragment baseFragment);

    void onImageSelected(List<String> mediaImageList);

    void onDocumentSelected(List<String> mediaDocumentList);

    ClientRequest getClientRequest();

    View getHostActivityViewById(@IdRes int id);
}
