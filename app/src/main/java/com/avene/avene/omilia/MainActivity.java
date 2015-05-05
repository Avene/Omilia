package com.avene.avene.omilia;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, SectionFragment.SentenceFragmentListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    @InjectView(R.id.my_awesome_toolbar)
    Toolbar mToolBar;

    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        mToolBar.setOnMenuItemClickListener(menuItem -> true);

        mToolBar.inflateMenu(R.menu.main);
        mToolBar.setNavigationIcon(R.drawable.ic_drawer);
        mToolBar.setOnMenuItemClickListener(menuItem -> {
            return true;
        });

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, mDrawerLayout);

        mToolBar.setNavigationOnClickListener(v -> mDrawerLayout.openDrawer(Gravity.LEFT));

        mToolBar.setTitle(mTitle);

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, SectionFragment.newInstance("Chapter " + position + 1))
                .commit();
    }

    public void onSectionAttached(String title) {
        mTitle = title;
    }

    public void restoreActionBar() {
        mToolBar.setTitle(mTitle);
// TODO navigation drawer design guidelines,
// updates the action bar to show the global app 'context',
// rather than just what's in the current screen.
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInteraction(String id) {

    }

}
