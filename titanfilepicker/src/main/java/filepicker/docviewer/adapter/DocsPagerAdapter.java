package filepicker.docviewer.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

import filepicker.docviewer.view.SingleDocFragment;

/**
 * Created by VINOD KUMAR on 3/28/2019.
 */
public class DocsPagerAdapter extends FragmentPagerAdapter {

    private List<SingleDocFragment> data;


    public DocsPagerAdapter(FragmentManager fm, List<SingleDocFragment> data) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.data = data;
    }

    public void add(SingleDocFragment singleDocFragment) {
        this.data.add(singleDocFragment);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        return data.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return data != null ? data.get(position).getTitle() : "";
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }
}
