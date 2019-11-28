package filepicker.docviewer.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titanfilepicker.R;

import java.util.ArrayList;
import java.util.List;

import filepicker.HostActivity;
import filepicker.base.BaseFragment;
import filepicker.docviewer.adapter.DocListAdapter;
import filepicker.docviewer.listener.DocSelectListener;
import filepicker.docviewer.model.MediaDocument;
import filepicker.utils.MediaUtil;

/**
 * Created by VINOD KUMAR on 3/28/2019.
 */
public class SingleDocFragment extends BaseFragment implements DocSelectListener {

    private String title;
    private String[] extensionArray;
    private RecyclerView rvDocItem;

    static SingleDocFragment newInstance(String title, String[] extension) {
        SingleDocFragment singleDocFragment = new SingleDocFragment();
        singleDocFragment.setTitle(title);
        singleDocFragment.setExtensionArray(extension);
        return singleDocFragment;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private String[] getExtensionArray() {
        return extensionArray;
    }

    private void setExtensionArray(String[] extensionArray) {
        this.extensionArray = extensionArray;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_document, container, false);
        rvDocItem = view.findViewById(R.id.rvDocItem);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = getContext();

        if (context != null) {

            List<MediaDocument> documentList = MediaUtil.getDocuments(context, getExtensionArray());

            if (documentList.isEmpty()) {
                rvDocItem.setVisibility(View.GONE);
                view.findViewById(R.id.cvNoDocFoundError).setVisibility(View.VISIBLE);
            } else {
                rvDocItem.setLayoutManager(new LinearLayoutManager(context));
                rvDocItem.setAdapter(new DocListAdapter(documentList, this, getExtensionArray()));
            }
        } else
            finishActivity();
    }

    @Override
    public void OnDocSelected(MediaDocument document) {
        List<String> documentPaths = new ArrayList<>();
        documentPaths.add(document.getPath());
        HostActivity hostActivity = getHostActivity();
        if (hostActivity != null)
            hostActivity.onDocumentSelected(documentPaths);
    }
}
