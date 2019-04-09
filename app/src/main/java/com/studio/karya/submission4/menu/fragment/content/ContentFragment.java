package com.studio.karya.submission4.menu.fragment.content;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.studio.karya.submission4.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {

    public static String TYPE = "type";
    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_content_fragment, container, false);

        TextView tes = view.findViewById(R.id.test);

        Bundle bundle = getArguments();
        if (bundle != null) {
            tes.setText(bundle.getString(TYPE));
        }

        return view;
    }
}
