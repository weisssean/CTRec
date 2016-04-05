package com.rec.ct.rec_txt;

import android.app.ActionBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.rec.ct.rec_txt.data.RectifierDataSource;
import com.rec.ct.rec_txt.fragments.RectifierFragment;
import com.rec.ct.rec_txt.fragments.RectifierListFragment;
import com.rec.ct.rec_txt.fragments.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements AddRectifier.AddRectifierListener, RectifierListFragment.RectifierListListener, RectifierFragment.OnListFragmentInteractionListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private RectifierDataSource mDatasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatasource = new RectifierDataSource(this);
        mDatasource.open();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            // optional
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            // optional
            @Override
            public void onPageSelected(int position) {
                if (position == 0) getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                else getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            }

            // optional
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SmsManager smsManager = SmsManager.getDefault();

                smsManager.sendTextMessage("5086856467", null, "Are you getting this?", null, null);
                // smsManager.sendTextMessage("4132370383", null, "Are you getting this?", null, null);

//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "0015086856467"));
//                intent.putExtra("sms_body", "sms message2");
//                startActivity(intent);

                Snackbar.make(view, "Sending SMS", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_add_rec:
                mViewPager.setCurrentItem(2);
                return true;
            case android.R.id.home:
                mViewPager.setCurrentItem(0);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveRec(Rectifier r) {

        Rectifier rec = mDatasource.createRectifier(r);
        //.add(comment);

    }


    @Override
    protected void onResume() {
        mDatasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mDatasource.close();
        super.onPause();
    }

    @Override
    public void onSelectRec(Rectifier r) {
        mViewPager.setCurrentItem(1);

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return RectifierListFragment.newInstance();
                case 1:
                    return RectifierFragment.newInstance(position + 1);
                case 2:
                    return AddRectifier.newInstance();
                default:
                    return PlaceholderFragment.newInstance(position + 1);


            }
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
