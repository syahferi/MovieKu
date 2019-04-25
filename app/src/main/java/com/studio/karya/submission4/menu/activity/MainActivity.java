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
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.studio.karya.submission4.R;
import com.studio.karya.submission4.menu.fragment.content.ContentFragment;
import com.studio.karya.submission4.menu.fragment.fav.FavContainerFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView menuBottom;

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
                        bundle.putString(ContentFragment.TYPE, "movie");
                        fragment = new ContentFragment();
                        fragment.setArguments(bundle);
                        loadFragment(fragment);
                        break;
                    case R.id.action_tv:
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

    //todo add change language
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_language) {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivityForResult(intent, 101);
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