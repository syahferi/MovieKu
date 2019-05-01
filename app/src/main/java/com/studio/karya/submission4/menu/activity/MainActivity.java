package com.studio.karya.submission4.menu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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
        switch (item.getItemId()) {
            case R.id.action_language:
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivityForResult(intent, 101);
                break;
            case R.id.action_search:

                //TODO with position menu, can filter with searchview with diferent fragment
                //check

                SearchView searchView = (SearchView) item.getActionView();
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        //call with api
                        Log.d(TAG, query+" submit");
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        //call with adapter
                        Log.d(TAG, newText);
                        return false;
                    }
                });
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