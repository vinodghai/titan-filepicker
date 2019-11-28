package filepicker.docviewer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titanfilepicker.R;

import java.util.List;

import filepicker.docviewer.listener.DocSelectListener;
import filepicker.docviewer.model.MediaDocument;
import filepicker.utils.MediaUtil;
import filepicker.utils.Util;

/**
 * Created by VINOD KUMAR on 3/28/2019.
 */
public class DocListAdapter extends RecyclerView.Adapter<DocListAdapter.DocViewHolder> {

    private List<MediaDocument> items;
    private DocSelectListener listener;
    private String[] docExtensionArray;

    public DocListAdapter(List<MediaDocument> items, DocSelectListener listener, String[] docExtensionArray) {
        this.items = items;
        this.listener = listener;
        this.docExtensionArray = docExtensionArray;
    }

    @NonNull
    @Override
    public DocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DocViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_doc, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DocViewHolder holder, int position) {
        holder.onBind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    class DocViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatImageView ivDocImage;
        private final AppCompatTextView tvDocName;
        private final AppCompatTextView tvDocSize;

        DocViewHolder(View itemView) {
            super(itemView);
            this.ivDocImage = itemView.findViewById(R.id.ivDocImage);
            this.tvDocName = itemView.findViewById(R.id.tvDocName);
            this.tvDocSize = itemView.findViewById(R.id.tvDocSize);
        }


        void onBind(MediaDocument item, @Nullable DocSelectListener listener) {
            this.ivDocImage.setImageResource(getExtensionDrawable(docExtensionArray));
            this.tvDocName.setText(item.getName());
            this.tvDocSize.setText(getSize(item.getSize()));
            if (listener != null)
                this.itemView.setOnClickListener(view -> {
                    view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.anim_item_clik));
                    listener.OnDocSelected(item);
                });
        }

        @DrawableRes
        private int getExtensionDrawable(String[] extensionArray) {

            for (String extension : extensionArray) {
                if (extension.matches(MediaUtil.PDF_EXT))
                    return R.drawable.ic_pdf;

                if (extension.matches(MediaUtil.DOC_EXT) || extension.matches(MediaUtil.DOCX_EXT))
                    return R.drawable.ic_word;
            }

            return R.drawable.ic_txt;
        }

        private String getSize(String size) {
            int byteSize = Integer.valueOf(size);
            if (byteSize > 1000) {
                int kbSize = Util.scaleUpByte(byteSize);
                if (kbSize > 1000) {
                    int mbSize = Util.scaleUpByte(kbSize);
                    return mbSize + "MB";
                } else
                    return kbSize + "KB";
            }
            return byteSize + "B";
        }
    }
}
