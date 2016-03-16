package com.bignerdranch.android.criminalintent;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public abstract class SingleFragmentActivity extends FragmentActivity {
	 protected abstract Fragment createFragment();
	   
	    	//����FragmentTabHost����
	    	private FragmentTabHost mTabHost;
	    	
	    	//����һ������
	    	private LayoutInflater layoutInflater;
	    		
	    	//�������������Fragment����
	    	private Class fragmentArray[] = {CrimeListActivity.class,CrimeActivity.class,CrimePagerActivity.class};
	    	
	    	//������������Ű�ťͼƬ
	    	private int mImageViewArray[] = {R.drawable.tab_page_btn,R.drawable.tab_pencil_btn,R.drawable.tab_laptop_btn,
	    									};
	    	
	    	//Tabѡ�������
	    	private String mTextviewArray[] = {"��Ŀ�б�", "�༭", "���"};
	    	

	    	 
	    	/**
	    	 * ��ʼ�����
	    	 */
	    	private void initView(){
	    		FragmentManager manager = getSupportFragmentManager();
		        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);

		        if (fragment == null) {
		            fragment = createFragment();
		            manager.beginTransaction()
		                .add(R.id.fragmentContainer, fragment)
		                .commit();
		        }
	    		//ʵ�������ֶ���
	    		layoutInflater = LayoutInflater.from(this);
	    				
	    		//ʵ����TabHost���󣬵õ�TabHost
	    		mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
	    		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);	
	    		
	    		//�õ�fragment�ĸ���
	    		int count = fragmentArray.length;	
	    				
	    		for(int i = 0; i < count; i++){	
	    			//Ϊÿһ��Tab��ť����ͼ�ꡢ���ֺ�����
	    			TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
	    			//��Tab��ť���ӽ�Tabѡ���
	    			mTabHost.addTab(tabSpec, fragmentArray[i], null);
	    			//����Tab��ť�ı���
	    			mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
	    		}
	    	}
	    				
	    	/**
	    	 * ��Tab��ť����ͼ�������
	    	 */
	    	private View getTabItemView(int index){
	    		View view = layoutInflater.inflate(R.layout.tab_item_view, null);
	    	
	    		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
	    		imageView.setImageResource(mImageViewArray[index]);
	    		
	    		TextView textView = (TextView) view.findViewById(R.id.textview);		
	    		textView.setText(mTextviewArray[index]);
	    	
	    		return view;
	    	}

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_fragment);
	        initView();
	        
	    }
	    
}