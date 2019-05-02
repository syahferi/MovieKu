package com.studio.karya.submission4.menu.activity.search;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.studio.karya.submission4.R;
import com.studio.karya.submission4.adapter.ContentAdapter;
import com.studio.karya.submission4.api.repository.Repository;
import com.studio.karya.submission4.model.ContentResponse;

public class SearchActivity extends AppCompatActivity implements com.studio.karya.submission4.menu.activity.search.SearchView {

    public static final String HINT_SEARCH = "hint_search";
    public static final String SEARCH_TYPE = "search_type";

    private SearchPresenter searchPresenter;
    private ContentAdapter contentAdapter;

    private LottieAnimationView lottieAnimationView;

    String hintSearch, tipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);
        lottieAnimationView = findViewById(R.id.anim_no_data);

        hintSearch = getIntent().getStringExtra(HINT_SEARCH);
        tipe = getIntent().getStringExtra(SEARCH_TYPE);

        //call adapter
        switch (tipe) {
            case "movie":
                contentAdapter = new ContentAdapter(this, "movie");
                break;
            case "tv":
                contentAdapter = new ContentAdapter(this, "tv");
                break;
        }

        RecyclerView rvSearch = findViewById(R.id.rv_search);
        rvSearch.setLayoutManager(new LinearLayoutManager(this));
        rvSearch.setHasFixedSize(true);
        rvSearch.setAdapter(contentAdapter);

        Repository repository = new Repository();
        searchPresenter = new SearchPresenter(this, repository, tipe);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem mSearch = menu.findItem(R.id.action_search);
        searchView(mSearch);

        return super.onCreateOptionsMenu(menu);
    }

    private void searchView(MenuItem mSearch) {
        SearchView searchView = (SearchView) mSearch.getActionView();
        searchView.setIconified(false);
        searchView.setQueryHint(hintSearch);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchPresenter.searchContent(newText);
                return false;
            }
        });

    }

    @Override
    public void showData(ContentResponse data) {
        if (data != null) {
            lottieAnimationView.setVisibility(View.GONE);
            contentAdapter.setListContent(data.getContentList());
        }
    }
}
