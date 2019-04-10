package com.studio.karya.submission4.menu.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;
import com.studio.karya.submission4.R;
import com.studio.karya.submission4.model.Content;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import static com.studio.karya.submission4.BuildConfig.IMG_URL;

public class DetailActivity extends AppCompatActivity {

    public static final String DATA = "data";
    public static final String TYPE = "type";

    private ImageView imgBackdrop, imgPoster;
    private TextView tvTitle, tvPopularity, tvVoteCount, tvVoteAvg, tvOverview;

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        type = getIntent().getStringExtra(TYPE);
        Content content = getIntent().getParcelableExtra(DATA);

        settingToolbar(content);

        imgBackdrop = findViewById(R.id.img_backdrop);
        imgPoster = findViewById(R.id.img_poster_detail);
        tvTitle = findViewById(R.id.title_detail);
        tvPopularity = findViewById(R.id.popularity_detail);
        tvVoteCount = findViewById(R.id.vote_count_detail);
        tvVoteAvg = findViewById(R.id.vote_avg_detail);
        tvOverview = findViewById(R.id.overview_detail);

        bindContent(content);
    }

    private void settingToolbar(Content content) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        if (type.equals("movie")) {
            collapsingToolbarLayout.setTitle(content.getTitleFilm());
        } else {
            collapsingToolbarLayout.setTitle(content.getTitleTv());
        }
        collapsingToolbarLayout.setCollapsedTitleTextColor(
                ContextCompat.getColor(this, android.R.color.black));
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, android.R.color.transparent));
    }

    private void bindContent(Content content) {
        Picasso.get().load(IMG_URL + content.getBackdropPath()).fit().into(imgBackdrop);
        Picasso.get().load(IMG_URL + content.getPosterPath()).fit().into(imgPoster);

        if (type.equals("movie")) {
            tvTitle.setText(content.getTitleFilm());
        } else {
            tvTitle.setText(content.getTitleTv());
        }

        tvPopularity.setText(content.getPopularity());
        tvVoteCount.setText(content.getVoteCount());
        tvVoteAvg.setText(content.getVoteAverage());
        tvOverview.setText(content.getOverview());
    }
}