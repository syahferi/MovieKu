package com.studio.karya.submission4.menu.fragment.fav;

import android.content.Intent;
import android.database.Cursor;
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

import com.airbnb.lottie.LottieAnimationView;
import com.studio.karya.submission4.R;
import com.studio.karya.submission4.adapter.ContentAdapter;
import com.studio.karya.submission4.db.DDL.DatabaseContract;
import com.studio.karya.submission4.model.Content;

import java.util.ArrayList;

import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.CONTENT_URI_MOVIE;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.CONTENT_URI_TV;
import static com.studio.karya.submission4.menu.activity.DetailActivity.EXTRA_POSITION;
import static com.studio.karya.submission4.menu.activity.DetailActivity.REQUEST_UPDATE;
import static com.studio.karya.submission4.menu.activity.DetailActivity.RESULT_DELETE;
import static com.studio.karya.submission4.utils.MappingHelper.mapCursorToArrayList;

public class FavFragment extends Fragment {

    //private ContentHelper contentHelper;
    private ContentAdapter contentAdapter;
    private LottieAnimationView anim_no_data;

    /*private static HandlerThread handlerThread;
    private DataObserver myObserver;*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /*contentHelper = ContentHelper.getInstance(getContext());
        contentHelper.open();*/

        /*handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        myObserver = new DataObserver(handler, this);
        getContext().getContentResolver().registerContentObserver(CONTENT_URI_MOVIE, true, myObserver);*/

        View view = inflater.inflate(R.layout.fragment_content_fragment, container, false);
        anim_no_data = view.findViewById(R.id.anim_no_data);
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
                contentAdapter = new ContentAdapter(getActivity(), FavFragment.this, tipe, "fav");

                //contentHelper.getAllContent(TABLE_MOVIE).isEmpty()
                Cursor cursor = getContext().getContentResolver()
                        .query(CONTENT_URI_MOVIE, null, null, null, null);

                if (cursor != null) {
                    ArrayList<Content> contentList = mapCursorToArrayList(cursor);
                    anim_no_data.setVisibility(View.GONE);
                    contentAdapter.setListContent(contentList); //contentHelper.getAllContent(TABLE_MOVIE)
                } else {
                    anim_no_data.setVisibility(View.VISIBLE);
                }

            } else {
                contentAdapter = new ContentAdapter(getActivity(), FavFragment.this, tipe, "fav");

                //contentHelper.getAllContent(TABLE_MOVIE).isEmpty()
                Cursor cursor = getContext().getContentResolver()
                        .query(CONTENT_URI_TV, null, null, null, null);

                if (cursor != null && cursor.moveToNext()) {

                    ArrayList<Content> contentList = mapCursorToArrayList(cursor);

                    if (cursor == null) {
                        anim_no_data.setVisibility(View.VISIBLE);
                    } else {
                        anim_no_data.setVisibility(View.GONE);
                        contentAdapter.setListContent(contentList); //contentHelper.getAllContent(TABLE_TV)
                    }
                }
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
                    if (contentAdapter.getItemCount() == 0) {
                        anim_no_data.setVisibility(View.VISIBLE);
                    } else {
                        anim_no_data.setVisibility(View.GONE);
                    }
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //contentHelper.close();
    }
}
