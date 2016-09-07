package com.hzq.espresso;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.materialdesigndemo.BaseActivity;
import com.example.materialdesigndemo.R;
import butterknife.InjectView;

/**
 * Created by hezhiqiang on 16/8/8.
 */
public class EspressoDemo extends BaseActivity {

    @InjectView(R.id.test_fragment_example_text)
    TextView testFragmentExampleText;
    @InjectView(R.id.change_text_button)
    Button changeTextButton;
    private TextSwapper textSwapper;

    @Override
    public int initLayoutId() {
        return R.layout.espresso_layout;
    }

    @Override
    public void initPageView() {
        textSwapper = new TextSwapper(getResources().getString(R.string.example_text_before), getResources().getString(R.string.example_text_after));
        changeTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testFragmentExampleText.setText(textSwapper.swap());
//                TestActivity.invoke1(EspressoDemo.this,new long[]{1L,3L});
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
