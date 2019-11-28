package filepicker;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.titanfilepicker.R;

import java.util.ArrayList;
import java.util.List;

import filepicker.base.BaseFragment;
import filepicker.docviewer.view.DocPickerFragment;
import filepicker.imageviewer.view.ImagePickerFragment;
import filepicker.request.ClientRequest;
import filepicker.request.document.DocumentPickerRequestBuilder;
import filepicker.request.image.ImagePickerRequestBuilder;
import filepicker.utils.Constants;

public class FilePickerActivity extends AppCompatActivity implements HostActivity {

    private BaseFragment baseFragment;
    private ClientRequest clientRequest;

    @Override
    public ClientRequest getClientRequest() {
        return clientRequest;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_picker);
        setToolbar();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        else
            startFragment();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            startFragment();
        else {
            Toast.makeText(this, "Provide permissions from settings", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void startFragment() {
        ClientRequest clientRequest = (ClientRequest) getIntent().getSerializableExtra(Constants.CLIENT_REQUEST);
        this.clientRequest = clientRequest;
        if (clientRequest.isRequestImage())
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.mainContainer, new ImagePickerFragment(), ImagePickerFragment.class.getSimpleName())
                    .commit();
        else
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.mainContainer, new DocPickerFragment(), DocPickerFragment.class.getSimpleName())
                    .commit();
    }

    @Override
    public void registerFragment(BaseFragment baseFragment) {
        this.baseFragment = baseFragment;
    }

    @Override
    public void onImageSelected(List<String> mediaImageList) {
        Intent intent = new Intent();
        intent.putStringArrayListExtra(ImagePickerRequestBuilder.RESULT_IMAGE_ARRAY_LIST, new ArrayList<>(mediaImageList));
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void onDocumentSelected(List<String> mediaDocumentList) {
        Intent intent = new Intent();
        intent.putStringArrayListExtra(DocumentPickerRequestBuilder.RESULT_DOCUMENT_ARRAY_LIST, new ArrayList<>(mediaDocumentList));
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public View getHostActivityViewById(int id) {
        return findViewById(id);
    }

    @Override
    public void onBackPressed() {
        if (baseFragment == null || baseFragment.onBackPressed())
            super.onBackPressed();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);

        View backView = toolbar.findViewById(R.id.ivBack);

        backView.setOnClickListener(v -> onBackPressed());
        setSupportActionBar(toolbar);

    }
}
