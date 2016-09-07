package com.hzq.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.materialdesigndemo.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.LayoutAssertions.noEllipsizedText;
import static android.support.test.espresso.assertion.LayoutAssertions.noMultilineButtons;
import static android.support.test.espresso.assertion.LayoutAssertions.noOverlaps;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.assertion.ViewAssertions.selectedDescendantsMatch;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by hezhiqiang on 16/8/12.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class HierarchyActivityTest {

    @Rule
    public ActivityTestRule<HierarchyActivity> rule = new ActivityTestRule(HierarchyActivity.class);

    /**
     * 测试子节点选择的View
     */
    @Test
    public void testSelectDescendants(){
        selectedDescendantsMatch(isAssignableFrom(TextView.class),withText(rule.getActivity().getString(R.string.hierarchy_text))).check(createATestView(),null);
    }

    @Test
    public void testNoEllipsizedText(){
        onView(withId(R.id.hierarchy_parent)).check(noEllipsizedText());
    }

    @Test
    public void testNoMultilineButtons(){
        onView(withId(R.id.hierarchy_parent)).check(noMultilineButtons());
    }

    //子节点没有重叠
    @Test
    public void testNoOverlap(){
        onView(withId(R.id.hierarchy_parent)).check(noOverlaps());
    }

    @Test
    public void testWithParent(){
        String contentDescription = rule.getActivity().getString(R.string.hierarchy_text);
        onView(allOf(withContentDescription(contentDescription),withParent(withId(R.id.hierarchy_parent_two)))).check(matches(isDisplayed()));
    }

    @Test
    public void testWithChild(){
        onView(allOf(withId(R.id.hierarchy_parent_two),withChild(withId(R.id.hierarchy_text_three)))).check(matches(isDisplayed()));
    }

    //是否有子节点
    @Test
    public void tesetHasDescendant(){
        onView(allOf(withId(R.id.hierarchy_parent_two),hasDescendant(withId(R.id.hierarchy_text_three)))).check(matches(isDisplayed()));
    }

    @Test
    public void testIsDescendantOfA(){
        onView(allOf(withId(R.id.hierarchy_text_three),isDescendantOfA(withId(R.id.hierarchy_parent_two)))).check(matches(isDisplayed()));
    }

    //是否有兄弟节点
    @Test
    public void testHasSibling(){
        onView(allOf(withId(R.id.hierarchy_text_three),hasSibling(withId(R.id.hierarchy_text_four)))).check(matches(isDisplayed()));
    }

    public View createATestView(){
        ViewGroup parent = new RelativeLayout(rule.getActivity());
        TextView tv = new TextView(rule.getActivity());
        tv.setText(rule.getActivity().getString(R.string.hierarchy_text));
        parent.addView(tv);
        return parent;
    }

}