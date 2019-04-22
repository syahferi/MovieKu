package com.studio.karya.submission4.menu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.studio.karya.submission4.R;
import com.studio.karya.submission4.menu.fragment.content.ContentFragment;
import com.studio.karya.submission4.menu.fragment.fav.FavContainerFragment;
import com.studio.karya.submission4.menu.fragment.fav.MovieFavFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentFragment contentFragment = new ContentFragment();
        final FavContainerFragment favFragment = new FavContainerFragment();

        final Bundle bundle = new Bundle();
        bundle.putString(ContentFragment.TYPE, "movie");
        contentFragment.setArguments(bundle);
        loadFragment(savedInstanceState, contentFragment);

        final BottomNavigationView menuBottom = findViewById(R.id.menu_bottom);
        menuBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment;

                switch (menuItem.getItemId()) {
                    case R.id.action_movie:
                        bundle.putString(ContentFragment.TYPE, "movie");
                        fragment = new ContentFragment();
                        fragment.setArguments(bundle);
                        loadFragment(savedInstanceState, fragment);
                        break;
                    case R.id.action_tv:
                        bundle.putString(ContentFragment.TYPE, "tv");
                        fragment = new ContentFragment();
                        fragment.setArguments(bundle);
                        loadFragment(savedInstanceState, fragment);
                        break;
                    case R.id.action_fav:
                        loadFragment(savedInstanceState, favFragment);
                        break;
                }
                return true;
            }
        });
    }

    private void loadFragment(Bundle savedInstanceState, Fragment fragment) {
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_language:
                //do something good
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = new MovieFavFragment();
        fragment.onActivityResult(requestCode, resultCode, data);

    }
}