package filepicker.request.image;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import filepicker.FilePickerActivity;
import filepicker.base.BaseMediator;
import filepicker.request.ClientRequest;
import filepicker.utils.Constants;

/**
 * Created by VINOD KUMAR on 1/10/2019.
 */
public class ImageMediator extends BaseMediator implements ImageFilePicker {

    ImageMediator() {
        super(new ClientRequest());
    }

    @Override
    public void pickImage(@NonNull Activity activity) {
        clientRequest.setRequestImage(true);
        Intent intent = new Intent(activity, FilePickerActivity.class);
        intent.putExtra(Constants.CLIENT_REQUEST, clientRequest);
        activity.startActivityForResult(intent, ImagePickerRequestBuilder.REQUEST_CODE_IMAGE_SELECT);
    }

    @Override
    public void pickImage(@NonNull Fragment fragment) {
        clientRequest.setRequestImage(true);
        Context context = fragment.getContext();
        if (context != null) {
            Intent intent = new Intent(context, FilePickerActivity.class);
            intent.putExtra(Constants.CLIENT_REQUEST, clientRequest);
            fragment.startActivityForResult(intent, ImagePickerRequestBuilder.REQUEST_CODE_IMAGE_SELECT);
        }
    }
}
