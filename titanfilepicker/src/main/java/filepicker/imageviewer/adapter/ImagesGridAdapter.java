package filepicker.imageviewer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.titanfilepicker.R;

import java.util.ArrayList;
import java.util.List;

import filepicker.imageviewer.listener.ImageSelectListener;
import filepicker.imageviewer.model.MediaImage;

/**
 * Created by VINOD KUMAR on 3/27/2019.
 */
public class ImagesGridAdapter extends RecyclerView.Adapter<ImagesGridAdapter.ImageViewHolder> {

    private List<MediaImage> items;
    private ImageSelectListener listener;

    private boolean isSingleImage;
    private int maxAllowedImages;
    private int totalSelected;

    public ImagesGridAdapter(@NonNull List<MediaImage> items,
                             @Nullable ImageSelectListener listener,
                             boolean isSingleImage,
                             int maxAllowedImages) {
        this.items = items;
        this.listener = listener;
        this.isSingleImage = isSingleImage;
        this.maxAllowedImages = maxAllowedImages;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.onBind(position, this.items, this.listener);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    @NonNull
    public List<String> getCheckedImagesPaths() {
        List<String> checkedImages = new ArrayList<>();
        for (MediaImage image : items)
            if (image.isChecked())
                checkedImages.add(image.getPath());
        return checkedImages;
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatImageView ivThumbnail;
        private final AppCompatCheckBox cbThumbnail;
        private final View viewOverlay;

        ImageViewHolder(View itemView) {
            super(itemView);
            this.ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            this.cbThumbnail = itemView.findViewById(R.id.cbThumbnail);
            this.viewOverlay = itemView.findViewById(R.id.viewOverlay);
        }

        void onBind(int position, List<MediaImage> items, @Nullable ImageSelectListener listener) {

            if (listener == null)
                return;

            MediaImage item = items.get(position);

            Glide.with(itemView)
                    .load(item.getPath())
                    .centerCrop()
                    .into(ivThumbnail);

            cbThumbnail.setOnCheckedChangeListener(null);

            viewOverlay.setVisibility(item.isChecked() ? View.VISIBLE : View.GONE);

            cbThumbnail.setChecked(item.isChecked());

            cbThumbnail.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (totalSelected <= maxAllowedImages)
                    onCheckedChange(item, listener, isChecked);

                if (totalSelected == maxAllowedImages) {
                    totalSelected++;
                    listener.onImagesSelected(getCheckedImagesPaths());
                }
            });

            if (isSingleImage) {
                this.cbThumbnail.setVisibility(View.GONE);
                this.itemView.setOnClickListener(view -> {
                    view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.anim_item_clik));
                    listener.onImageSelected(item);
                });
            } else
                this.itemView.setOnClickListener(view -> {
                    view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.anim_item_clik));
                    cbThumbnail.setChecked(!cbThumbnail.isChecked());
                });
        }

        private void onCheckedChange(MediaImage item, ImageSelectListener listener, boolean isChecked) {
            item.setChecked(isChecked);
            if (isChecked) {
                totalSelected++;
                viewOverlay.setVisibility(View.VISIBLE);
            } else {
                totalSelected--;
                viewOverlay.setVisibility(View.GONE);
            }

            listener.onImageCheckedChange(isChecked);
        }
    }
}
