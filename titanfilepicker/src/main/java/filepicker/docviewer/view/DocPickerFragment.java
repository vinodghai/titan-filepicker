package filepicker.docviewer.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.titanfilepicker.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import filepicker.base.BaseFragment;
import filepicker.docviewer.adapter.DocsPagerAdapter;

/**
 * Created by VINOD KUMAR on 3/28/2019.
 */
public class DocPickerFragment extends BaseFragment {

    private ViewPager docsViewPager;
    private TabLayout docsTabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doc_picker, container, false);
        docsViewPager = view.findViewById(R.id.docsViewPager);
        docsTabLayout = view.findViewById(R.id.docsTabLayout);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View selectCount = getHostActivityViewById(R.id.tvSelectCount);
        View done = getHostActivityViewById(R.id.tvDone);

        if (selectCount != null)
            selectCount.setVisibility(View.GONE);

        if (done != null)
            done.setVisibility(View.GONE);

        List<SingleDocFragment> singleDocFragments = new ArrayList<>();
        Map<String, String[]> fileDetails = getClientRequest().getFileDetails();

        if (fileDetails == null) {
            finishActivity();
            return;
        }

        for (Map.Entry entry : fileDetails.entrySet()) {
            String title = (String) entry.getKey();
            String[] extensionArray = (String[]) entry.getValue();
            singleDocFragments.add(SingleDocFragment.newInstance(title, extensionArray));
        }

        DocsPagerAdapter docsPagerAdapter = new DocsPagerAdapter(getChildFragmentManager(), singleDocFragments);
        docsViewPager.setAdapter(docsPagerAdapter);
        docsTabLayout.setupWithViewPager(docsViewPager);
    }
}
