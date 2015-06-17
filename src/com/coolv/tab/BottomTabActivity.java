package com.coolv.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import com.coolv.base.TabView;

public abstract class BottomTabActivity extends FragmentActivity {
	public abstract Fragment[] getTabContentFragments();
	public abstract int getDefaultItem();
	public abstract MainTab getMainTab();
	
	 private ViewPager mViewPager;
	    private TabView mTabView ;
	    
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	        mViewPager = (ViewPager)findViewById(R.id.id_view_pager) ;
	        mViewPager.setOffscreenPageLimit(3);
	        mViewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));
	        mTabView = (TabView)findViewById(R.id.id_tab);
	        mViewPager.setCurrentItem(getDefaultItem(), false);
	        mTabView.setViewPager(mViewPager);
	    }

	    public void setCurrentItem(int position){
	    	mViewPager.setCurrentItem(position, false);
	    }
	    
	    @Override
		protected void onResume() {
			super.onResume();
		}

		class PageAdapter extends FragmentStatePagerAdapter implements TabView.OnItemIconTextSelectListener{

	        public PageAdapter(FragmentManager fm) {
	            super(fm);
	        }
	        
	        @Override
	        public Fragment getItem(int position) {
	            return getTabContentFragments()[position];
	        }
	        
	        @Override
	        public int[] onIconSelect(int position) {
	            int icon[] = new int[2] ;
	            icon[0] = getMainTab().getmIconSelect()[position];
	            icon[1] = getMainTab().getmIconNormal()[position];
	            return icon;
	        }
	        @Override
	        public String onTextSelect(int position) {
	            return getMainTab().getMtitle()[position];
	        }

	        @Override
	        public int getCount() {
	            return getMainTab().getMtitle().length;
	        }
	    }
}
