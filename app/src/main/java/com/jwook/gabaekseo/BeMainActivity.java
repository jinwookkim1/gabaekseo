package com.jwook.gabaekseo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.kakao.usermgmt.UserManagement;

public class BeMainActivity extends AppCompatActivity {

    SearchView searchView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MyButtonAdapter myButtonAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_be_main);

        drawerLayout = findViewById(R.id.drawer_view);
        navigationView = findViewById(R.id.nv_view);

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.favorites:
                        Toast.makeText(BeMainActivity.this, "로그인 후 이용할 수 있는 서비스입니다.", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.my_reviews:
                        Toast.makeText(BeMainActivity.this, "로그인 후 이용할 수 있는 서비스입니다.", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.my_info:
                        Toast.makeText(BeMainActivity.this, "로그인 후 이용할 수 있는 서비스입니다.", Toast.LENGTH_SHORT).show();
                        break;
                }

                drawerLayout.closeDrawer(navigationView);

                return false;
            }
        });



        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_text);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("공 원"));
        tabs.addTab(tabs.newTab().setText("카 페"));
        tabs.addTab(tabs.newTab().setText("음식점"));
        tabs.addTab(tabs.newTab().setText("숙 소"));
        tabs.addTab(tabs.newTab().setText("동물병원"));
        tabs.setTabGravity(tabs.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.viewpager);
        myButtonAdapter = new MyButtonAdapter(getSupportFragmentManager(), 5);
        viewPager.setAdapter(myButtonAdapter);

        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        ActionBar actionBar = getSupportActionBar();
        actionBar.show();




    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.bar_search);
        searchView = (SearchView) item.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("검색어를 입력하세요.");
        searchView.setQuery("",false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(BeMainActivity.this, query+"를 검색했습니다.", Toast.LENGTH_SHORT).show();

                G.itemlist.clear();
                Fragment_1 frag1 = (Fragment_1)myButtonAdapter.tabs[0];
                for(Item item1 : frag1.arrayList){
                    if(item1.name.contains(query)||item1.address.contains(query)){
                        G.itemlist.add(item1);
                    }
                }

                Fragment_2 frag2 = (Fragment_2)myButtonAdapter.tabs[1];
                for(Item item1 : frag2.arrayList){
                    if(item1.name.contains(query)||item1.address.contains(query)){
                        G.itemlist.add(item1);
                    }
                }

                Fragment_3 frag3 = (Fragment_3)myButtonAdapter.tabs[2];
                for(Item item1 : frag3.arrayList){
                    if(item1.name.contains(query)||item1.address.contains(query)){
                        G.itemlist.add(item1);
                    }
                }

                Fragment_4 frag4 = (Fragment_4)myButtonAdapter.tabs[3];
                for(Item item1 : frag4.arrayList){
                    if(item1.name.contains(query)||item1.address.contains(query)){
                        G.itemlist.add(item1);
                    }
                }

                Fragment_5 frag5 = (Fragment_5)myButtonAdapter.tabs[4];
                for(Item item1 : frag5.arrayList){
                    if(item1.name.contains(query)||item1.address.contains(query)){
                        G.itemlist.add(item1);
                    }
                }

                startActivity(new Intent(BeMainActivity.this, SearchActivity.class));

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
