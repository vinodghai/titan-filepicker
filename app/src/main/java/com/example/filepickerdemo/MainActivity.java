package com.example.filepickerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import filepicker.request.document.DocumentPickerRequestBuilder;
import filepicker.request.image.ImagePickerRequestBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btImage).setOnClickListener(view ->
                new ImagePickerRequestBuilder()
                        .setTitle("Select Image")
                        .setMaxImageCount(3)
                        .build()
                        .pickImage(this));


        findViewById(R.id.btDoc).setOnClickListener(view ->
                new DocumentPickerRequestBuilder()
                        .setTitle("Select Document")
                        .addFileSupport("Docs", new String[]{".doc", ".docx"})
                        .addFileSupport("Notes", new String[]{".txt"})
                        .addFileSupport("Pdfs", new String[]{".pdf"})
                        .build()
                        .pickDocument(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && requestCode == ImagePickerRequestBuilder.REQUEST_CODE_IMAGE_SELECT) {
            ArrayList<String> images = data.getStringArrayListExtra(ImagePickerRequestBuilder.RESULT_IMAGE_ARRAY_LIST);
            Toast.makeText(this, String.valueOf(images.size()), Toast.LENGTH_LONG).show();
        }

        if (resultCode == RESULT_OK && data != null && requestCode == DocumentPickerRequestBuilder.REQUEST_CODE_DOCUMENT_SELECT) {
            ArrayList<String> images = data.getStringArrayListExtra(DocumentPickerRequestBuilder.RESULT_DOCUMENT_ARRAY_LIST);
            Toast.makeText(this, String.valueOf(images.size()), Toast.LENGTH_LONG).show();
        }
    }
}
