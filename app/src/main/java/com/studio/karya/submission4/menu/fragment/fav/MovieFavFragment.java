package com.studio.karya.submission4.menu.fragment.fav;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.studio.karya.submission4.R;
import com.studio.karya.submission4.adapter.ContentAdapter;
import com.studio.karya.submission4.db.DML.ContentHelper;

import static com.studio.karya.submission4.db.DDL.DatabaseContract.TABLE_MOVIE;
import static com.studio.karya.submission4.menu.activity.DetailActivity.EXTRA_POSITION;
import static com.studio.karya.submission4.menu.activity.DetailActivity.REQUEST_UPDATE;
import static com.studio.karya.submission4.menu.activity.DetailActivity.RESULT_DELETE;

public class MovieFavFragment extends Fragment {

    private ContentHelper contentHelper;
    private ContentAdapter contentAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        contentHelper = ContentHelper.getInstance(getContext());
        contentHelper.open();

        contentAdapter = new ContentAdapter(getActivity(), "movie");
        contentAdapter.setListContent(contentHelper.getAllContent(TABLE_MOVIE));

        View view = inflater.inflate(R.layout.fragment_content_fragment, container, false);
        ProgressBar progressBar = view.findViewById(R.id.loading);
        progressBar.setVisibility(View.GONE);

        RecyclerView rvFav = view.findViewById(R.id.rv_content);
        rvFav.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFav.setHasFixedSize(true);
        rvFav.setAdapter(contentAdapter);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("dadada", requestCode+" "+resultCode);
        if (data != null) {
            if (requestCode == REQUEST_UPDATE) {
                if (resultCode == RESULT_DELETE) {
                    contentAdapter.removeItem(data.getIntExtra(EXTRA_POSITION, 0));
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        contentHelper.close();
    }

}
