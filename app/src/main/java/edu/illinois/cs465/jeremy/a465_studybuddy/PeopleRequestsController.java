package edu.illinois.cs465.jeremy.a465_studybuddy;


import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.PagerAdapter;




public class PeopleRequestsController extends AppCompatActivity implements PeopleFrag.OnFragmentInteractionListener{
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people_req_main);

        toolbar = (Toolbar) findViewById(R.id.con_toolbar);


        viewPager = (ViewPager) findViewById(R.id.con_pager);
        //setupWithViewPage(viewPager)

        //tabLayout = (TabLayout) findViewById(R.id.tabs);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_control_layout);

        tabLayout.addTab(tabLayout.newTab().setText("People"));
        tabLayout.addTab(tabLayout.newTab().setText("Requests"));

        PagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void lookAtPeople() {

    }


      /* @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof PeopleFrag) {
            PeopleFrag pFrag = (PeopleFrag) fragment;
            PeopleFrag.setOnFragmentInteractionListener(PeopleRequestsController.this);
        }
    }

    public enum TabPagerEnum {

    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void lookAtPeople() {

    }*/
}
