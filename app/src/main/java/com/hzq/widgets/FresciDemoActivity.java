package com.hzq.widgets;

import android.net.Uri;
import android.os.Bundle;

import com.example.materialdesigndemo.BaseActivity;
import com.example.materialdesigndemo.R;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by hezhiqiang on 16/7/13.
 */
public class FresciDemoActivity extends BaseActivity {
    @InjectView(R.id.my_image_view)
    SimpleDraweeView myImageView;

    @InjectView(R.id.my_image_view1)
    SimpleDraweeView myImageView1;
    @InjectView(R.id.my_image_view2)
    SimpleDraweeView myImageView2;

    @Override
    public int initLayoutId() {
        return R.layout.fresco_layout;
    }

    @Override
    public void initPageView() {
        setTitle("FresciDemo");
        //加载网络图片
        Uri uri = Uri.parse("http://static.test.weibangong.com/files/5785e22a7694a4618f22b1a5");
        myImageView.setImageURI(uri);

        GenericDraweeHierarchy hierarchy = myImageView.getHierarchy();
        hierarchy.setPlaceholderImage(R.mipmap.cheese_4);
        //加载本地res下的图片
        myImageView1.setImageURI(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.cheese_3));

//        myImageView2.setImageURI(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.cheese_3));
        myImageView2.setImageURI(uri);
        //本地图片
        //Uri.parse("file://[包名]"+文件名);
    }

    /**
     * 动态设置图片的属性
     */
    private void changeImageParams() {
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
        GenericDraweeHierarchy hierarchy = builder.setFadeDuration(1000).build();

        myImageView.setHierarchy(hierarchy);
    }

}
