package com.example.convenientbannertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private View mHead;
    private ArrayList<Integer> mLocalImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initConvenientBanner();
        initListView();
        mListView.addHeaderView(mHead);

    }

    private void initConvenientBanner() {
        mHead = View.inflate(this, R.layout.headder, null);
        initImages();
        ConvenientBanner<Integer> mConvenientBanner = mHead.findViewById(R.id.convenientBanner);
        mConvenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, mLocalImages)
                .setPageIndicator(new int[]{R.drawable.dark,
                        R.drawable.light})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(MainActivity.this, "" + position,
                                Toast.LENGTH_SHORT).show();
                    }
                });
        mConvenientBanner.startTurning(2000);
//        mConvenientBanner.setManualPageable(false);//设置不能手动影响

    }

    private void initImages() {
        mLocalImages.add(getResId("ic_launcher", R.mipmap.class));
        mLocalImages.add(getResId("ic_launcher_round", R.mipmap.class));
        mLocalImages.add(getResId("ic_launcher", R.mipmap.class));
        mLocalImages.add(getResId("ic_launcher", R.mipmap.class));

    }

    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    private void initListView() {
        mListView = findViewById(R.id.list_view);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(i + "");
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, list);
        mListView.setAdapter(arrayAdapter);

    }

}