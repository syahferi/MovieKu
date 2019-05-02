package com.studio.karya.submission4.menu.fragment.fav;

import android.content.Intent;
import android.os.Bundle;
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
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TABLE_TV;
import static com.studio.karya.submission4.menu.activity.DetailActivity.EXTRA_POSITION;
import static com.studio.karya.submission4.menu.activity.DetailActivity.REQUEST_UPDATE;
import static com.studio.karya.submission4.menu.activity.DetailActivity.RESULT_DELETE;

public class FavFragment extends Fragment {

    private ContentHelper contentHelper;
    private ContentAdapter contentAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        contentHelper = ContentHelper.getInstance(getContext());
        contentHelper.open();

        View view = inflater.inflate(R.layout.fragment_content_fragment, container, false);
        ProgressBar progressBar = view.findViewById(R.id.loading);
        progressBar.setVisibility(View.GONE);

        checkTypeFragment();

        RecyclerView rvFav = view.findViewById(R.id.rv_content);
        rvFav.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFav.setHasFixedSize(true);
        rvFav.setAdapter(contentAdapter);
        return view;
    }

    private void checkTypeFragment() {
        String tipe = null;
        if (getArguments() != null) {
            tipe = getArguments().getString("type");
        }
        if (tipe != null) {
            if (tipe.equals("movie")) {
                contentAdapter = new ContentAdapter(getActivity(), FavFragment.this, tipe);
                contentAdapter.setListContent(contentHelper.getAllContent(TABLE_MOVIE));
            } else {
                contentAdapter = new ContentAdapter(getActivity(), FavFragment.this, tipe);
                contentAdapter.setListContent(contentHelper.getAllContent(TABLE_TV));
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == REQUEST_UPDATE) {
                if (resultCode == RESULT_DELETE) {
                    contentAdapter.removeItem(Integer.valueOf(data.getStringExtra(EXTRA_POSITION)));
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
