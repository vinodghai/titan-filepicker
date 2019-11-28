package filepicker.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.example.titanfilepicker.R;

import filepicker.HostActivity;
import filepicker.request.ClientRequest;

/**
 * Created by VINOD KUMAR on 3/27/2019.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getClientRequest() == null)
            finishActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() instanceof HostActivity)
            ((HostActivity) getActivity()).registerFragment(this);
    }


    @Nullable
    protected HostActivity getHostActivity() {
        return (HostActivity) getActivity();
    }

    @Nullable
    protected View getHostActivityViewById(@IdRes int id) {
        if (getHostActivity() != null)
            return getHostActivity().getHostActivityViewById(id);
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String title = getClientRequest().getTitle();

        String safeTitle = title != null ? title : "";

        View textView = getHostActivityViewById(R.id.tvTitle);
        if (textView instanceof AppCompatTextView)
            ((AppCompatTextView) textView).setText(safeTitle);
    }

    public boolean onBackPressed() {
        return true;
    }

    protected ClientRequest getClientRequest() {
        HostActivity hostActivity = getHostActivity();
        return hostActivity != null ? hostActivity.getClientRequest() : null;
    }

    protected void finishActivity() {
        if (getActivity() != null)
            getActivity().finish();
    }
}
