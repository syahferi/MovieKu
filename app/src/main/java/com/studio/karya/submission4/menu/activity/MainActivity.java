package com.studio.karya.submission4.menu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.studio.karya.submission4.R;
import com.studio.karya.submission4.menu.fragment.content.ContentFragment;
import com.studio.karya.submission4.menu.fragment.fav.FavContainerFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    BottomNavigationView menuBottom;
    int position_menu = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentFragment contentFragment = new ContentFragment();
        final FavContainerFragment favFragment = new FavContainerFragment();

        final Bundle bundle = new Bundle();
        bundle.putString(ContentFragment.TYPE, "movie");
        contentFragment.setArguments(bundle);
        loadFragment(contentFragment);

        menuBottom = findViewById(R.id.menu_bottom);
        menuBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_movie:
                        position_menu = 0;
                        bundle.putString(ContentFragment.TYPE, "movie");
                        fragment = new ContentFragment();
                        fragment.setArguments(bundle);
                        loadFragment(fragment);

                        break;
                    case R.id.action_tv:
                        position_menu = 1;
                        bundle.putString(ContentFragment.TYPE, "tv");
                        fragment = new ContentFragment();
                        fragment.setArguments(bundle);
                        loadFragment(fragment);
                        break;
                    case R.id.action_fav:
                        loadFragment(favFragment);
                        break;
                }
                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
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
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_language:
                intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivityForResult(intent, 101);
                break;
            case R.id.action_search:
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            menuBottom.setSelectedItemId(R.id.action_movie);
        }
    }
}