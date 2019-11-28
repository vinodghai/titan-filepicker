package filepicker.request.document;

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
 * Created by VINOD KUMAR on 3/28/2019.
 */
public class DocumentMediator extends BaseMediator implements DocumentFilePicker {

    DocumentMediator() {
        super(new ClientRequest());
    }

    @Override
    public void pickDocument(@NonNull Activity activity) {
        clientRequest.setRequestImage(false);
        Intent intent = new Intent(activity, FilePickerActivity.class);
        intent.putExtra(Constants.CLIENT_REQUEST, clientRequest);
        activity.startActivityForResult(intent, DocumentPickerRequestBuilder.REQUEST_CODE_DOCUMENT_SELECT);
    }

    @Override
    public void pickDocument(@NonNull Fragment fragment) {
        clientRequest.setRequestImage(false);
        Context context = fragment.getContext();
        if (context != null) {
            Intent intent = new Intent(context, FilePickerActivity.class);
            intent.putExtra(Constants.CLIENT_REQUEST, clientRequest);
            fragment.startActivityForResult(intent, DocumentPickerRequestBuilder.REQUEST_CODE_DOCUMENT_SELECT);
        }
    }
}
