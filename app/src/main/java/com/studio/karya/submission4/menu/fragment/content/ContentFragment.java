package com.studio.karya.submission4.menu.fragment.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.studio.karya.submission4.R;
import com.studio.karya.submission4.adapter.ContentAdapter;
import com.studio.karya.submission4.api.repository.Repository;
import com.studio.karya.submission4.model.ContentResponse;

public class ContentFragment extends Fragment implements ContentView {

    public static String TYPE = "type";
    private ContentAdapter contentAdapter;
    private ProgressBar progressContent;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content_fragment, container, false);
        progressContent = view.findViewById(R.id.loading);

        String type = null;
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString(TYPE);
        }

        //call presenter
        Repository repository = new Repository();
        ContentPresenter contentPresenter = new ContentPresenter(this, repository);
        contentPresenter.getContent(type);

        //call adapter
        contentAdapter = new ContentAdapter(getActivity(), type);

        RecyclerView rvContent = view.findViewById(R.id.rv_content);
        rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        rvContent.setHasFixedSize(true);
        rvContent.setAdapter(contentAdapter);

        return view;
    }

    @Override
    public void loadData(ContentResponse data) {
        contentAdapter.setListContent(data.getContentList());
    }

    @Override
    public void showLoading() {
        progressContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressContent.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }
}
