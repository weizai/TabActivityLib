package com.coolv.tab;


public class MainTab {
	private String mtitle[];
	private int mIconNormal[];
	private int mIconSelect[];
	
	public MainTab(String mtitle[], int mIconNormal[], int mIconSelect[]){
		this.mtitle = mtitle;
		this.mIconNormal = mIconNormal;
		this.mIconSelect = mIconSelect;
	}

	public String[] getMtitle() {
		return mtitle;
	}

	public void setMtitle(String[] mtitle) {
		this.mtitle = mtitle;
	}

	public int[] getmIconNormal() {
		return mIconNormal;
	}

	public void setmIconNormal(int[] mIconNormal) {
		this.mIconNormal = mIconNormal;
	}

	public int[] getmIconSelect() {
		return mIconSelect;
	}

	public void setmIconSelect(int[] mIconSelect) {
		this.mIconSelect = mIconSelect;
	}
	 //public static int[] mTitle = {R.string.main_bottom_tab_bookshelf, R.string.main_bottom_tab_booklib, R.string.main_bottom_tab_mine};
	 //public static int[] mIconNormal = {R.drawable.hometab_bookshelf_icon, R.drawable.hometab_bookstore_icon, R.drawable.hometab_profile_icon};
	// public static int[] mIconSelect = {R.drawable.hometab_bookshelf_icon_hover, R.drawable.hometab_bookstore_icon_hover, R.drawable.hometab_profile_icon_hover};
	
}
