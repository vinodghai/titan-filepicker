package filepicker.imageviewer.view;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titanfilepicker.R;

import java.util.ArrayList;
import java.util.List;

import filepicker.HostActivity;
import filepicker.base.BaseFragment;
import filepicker.imageviewer.adapter.ImagesGridAdapter;
import filepicker.imageviewer.listener.ImageSelectListener;
import filepicker.imageviewer.model.MediaImage;
import filepicker.request.ClientRequest;
import filepicker.utils.MediaUtil;
import filepicker.utils.Util;

/**
 * Created by VINOD KUMAR on 3/27/2019.
 */
public class ImagePickerFragment extends BaseFragment implements ImageSelectListener {

    private RecyclerView rvImages;
    private AppCompatTextView tvSelectCount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_picker, container, false);

        rvImages = view.findViewById(R.id.rvImages);

        View selectCount = getHostActivityViewById(R.id.tvSelectCount);
        if (selectCount instanceof AppCompatTextView)
            tvSelectCount = (AppCompatTextView) selectCount;

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ClientRequest clientRequest = getClientRequest();


        Context context = getContext();

        if (context != null && clientRequest != null && getClientRequest().getMaxImageCount() > 0) {

            int col = Util.calculateNoOfColumns(context, 125);

            boolean isSingleImage = getClientRequest().getMaxImageCount() == 1;

            ImagesGridAdapter adapter = new ImagesGridAdapter(MediaUtil.getImages(context),
                    this,
                    isSingleImage,
                    getClientRequest().getMaxImageCount());

            rvImages.setLayoutManager(new GridLayoutManager(context, col));
            rvImages.setAdapter(adapter);

            configureDoneButton(isSingleImage, adapter);
        } else
            finishActivity();
    }

    @Override
    public void onImageCheckedChange(boolean isChecked) {
        if (tvSelectCount != null && tvSelectCount.getText() != null) {

            String maxImageCount = String.valueOf(getClientRequest().getMaxImageCount());

            if (TextUtils.isEmpty(tvSelectCount.getText())) {
                String string = "1/" + maxImageCount;
                tvSelectCount.setText(string);
                return;
            }

            String text = tvSelectCount.getText().toString();
            String[] splitText = text.split("/");

            int count = Integer.valueOf(splitText[0]);

            if (isChecked)
                count++;
            else
                count--;

            updateCount(count, maxImageCount);
        }
    }

    @Override
    public void onImageSelected(MediaImage mediaImage) {
        List<String> imagePaths = new ArrayList<>();
        imagePaths.add(mediaImage.getPath());
        sendResultsToHostActivity(imagePaths);
    }

    @Override
    public void onImagesSelected(List<String> imageList) {
        this.sendResultsToHostActivity(imageList);
    }

    private void configureDoneButton(boolean isSingleImage, ImagesGridAdapter adapter) {
        View tvDone = getHostActivityViewById(R.id.tvDone);
        if (tvDone != null) {

            if (isSingleImage)
                tvDone.setVisibility(View.GONE);

            else tvDone.setOnClickListener(view1 -> {
                tvDone.setEnabled(false);
                sendResultsToHostActivity(adapter.getCheckedImagesPaths());
            });
        }
    }

    private void updateCount(int count, String maxImageCount) {
        if (count > 0) {
            String updatedCount = count + "/" + maxImageCount;
            tvSelectCount.setText(updatedCount);
        } else
            tvSelectCount.setText("");
    }

    private void sendResultsToHostActivity(List<String> imagePaths) {
        HostActivity hostActivity = getHostActivity();
        if (hostActivity != null)
            hostActivity.onImageSelected(imagePaths);
    }
}
