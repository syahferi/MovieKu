package com.studio.karya.submission4.menu.fragment.fav;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.studio.karya.submission4.R;
import com.studio.karya.submission4.adapter.ContentAdapter;
import com.studio.karya.submission4.db.DML.ContentHelper;

import static com.studio.karya.submission4.db.DDL.DatabaseContract.TABLE_MOVIE;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TABLE_TV;

public class TvFavFragment extends Fragment {

    private ContentAdapter contentAdapter;
    private ContentHelper contentHelper;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        contentHelper = ContentHelper.getInstance(getContext());
        contentHelper.open();

        contentAdapter = new ContentAdapter(getActivity(), "tv");
        contentAdapter.setListContent(contentHelper.getAllContent(TABLE_TV));

        View view = inflater.inflate(R.layout.fragment_content_fragment, container, false);
        ProgressBar progressBar = view.findViewById(R.id.loading);
        progressBar.setVisibility(View.GONE);

        RecyclerView rvTvFav = view.findViewById(R.id.rv_content);
        rvTvFav.setHasFixedSize(true);
        rvTvFav.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTvFav.setAdapter(contentAdapter);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        contentHelper.close();
    }
}
