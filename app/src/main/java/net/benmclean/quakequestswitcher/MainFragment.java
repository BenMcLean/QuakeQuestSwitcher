package net.benmclean.quakequestswitcher;

import android.os.Bundle;
import android.support.v17.leanback.app.BrowseSupportFragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainFragment extends BrowseSupportFragment {
    private static final String TAG = "MainFragment";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onActivityCreated(savedInstanceState);
        setTitle(getString(R.string.browse_title)); // Badge, when set, takes precedent

        setOnSearchClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Implement your own in-app search", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
