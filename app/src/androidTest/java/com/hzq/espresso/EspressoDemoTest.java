package com.hzq.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.materialdesigndemo.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openContextualActionModeOverflowMenu;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.isFocusable;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;


/**
 * Created by hezhiqiang on 16/8/8.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoDemoTest {

    @Rule
    public ActivityTestRule<EspressoDemo> activityTestRule = new ActivityTestRule<>(EspressoDemo.class);

    @Test
    public void testSwapText(){
        onView(withId(R.id.test_fragment_example_text)).check(matches(withText(R.string.example_text_before)));
        onView(withId(R.id.change_text_button)).perform(click());
        onView(allOf(withId(R.id.test_fragment_example_text),withText(R.string.example_text_after))).check(matches(isDisplayed()));
    }

    @Test
    public void testIsEnabled(){
        //检查按钮是否可点击
        onView(withId(R.id.change_text_button)).check(matches(isEnabled()));
    }

    @Test
    public void testCheckingACheckBox(){
        //检查checkbox的选中与未选中逻辑
        onView(withId(R.id.enabled_checkbox)).check(matches(isNotChecked())).perform(click()).check(matches(isChecked()));
    }

    @Test
    public void testIsCheckboxDisabled(){
        //检查checkbox是否可点击
        onView(withId(R.id.disabled_checkbox)).check(matches(not(isEnabled())));
    }

    @Test
    public void testIsClickable(){
        //检查按钮是否可点击
        onView(withId(R.id.change_text_button)).check(matches(isClickable()));
    }

    @Test
    public void testActionMenuItemClick(){
        openContextualActionModeOverflowMenu();
        //检查menu菜单
        onView(withText(R.string.action_settings)).perform(click());
    }

    @Test
    public void testEditTextisFocusable(){
        //检查edittext是否获取焦点
        onView(withId(R.id.test_fragment_edittext)).check(matches(isFocusable()));
    }

    @Test
    public void testTypeText(){
        String exampleText = "here is a long piece of text to out";
        //检查edittext输入文本与显示
        onView(withId(R.id.test_fragment_edittext)).perform(typeText(exampleText));
        onView(withText(exampleText)).check(matches(isDisplayed()));
    }

    @Test
    public void testTypeTextThenClear(){
        String exampleText = "here is a long piece of text to out";
        onView(withId(R.id.test_fragment_edittext)).perform(typeText(exampleText));
        onView(withText(exampleText)).check(matches(isDisplayed()));

        //检查清理方法
        onView(withId(R.id.test_fragment_edittext)).perform(clearText());
        onView(withId(R.id.test_fragment_edittext)).check(matches(withText("")));
    }

    @Test
    public void testTypeTextThenReplace(){
        String exampleText = "here is a long piece of text to type out.";
        String exampleReplaceText = "here is a long piece of text to replace.";
        //测试替换输入文本
        onView(withId(R.id.test_fragment_edittext)).perform(typeText(exampleText));

        onView(withText(exampleText)).check(matches(isDisplayed()));
        onView(withId(R.id.test_fragment_edittext)).perform(replaceText(exampleReplaceText));

        onView(allOf(withId(R.id.test_fragment_edittext),withText(exampleReplaceText))).check(matches(isDisplayed()));
    }

    @Test
    public void testTypeTextWithTextAndId(){
        String exampleText = "Here is a long piece of text to type out.";
        onView(withId(R.id.test_fragment_edittext)).perform(typeText(exampleText));
        closeSoftKeyboard();
//        onView(withText(exampleText)).check(matches(isDisplayed()));
        onView(allOf(withText(exampleText),withId(R.id.test_fragment_edittext))).check(matches(isDisplayed()));
    }

    @Test
    public void testTypeTextCloseSoftKeyboard(){
        onView(withId(R.id.test_fragment_edittext)).perform(click());
        closeSoftKeyboard();
    }

    @Test
    public void testContentDescription(){
        String exampleContentDescription = "Example Content Description Dest";
        onView(withId(R.id.test_fragment_content_description_text)).check(matches(hasContentDescription()));
        onView(allOf(withId(R.id.test_fragment_content_description_text),withContentDescription(exampleContentDescription))).check(matches(isDisplayed()));
    }

    @Test
    public void testStartWith(){
        String textStartsWith = "Example Content Description Dest".substring(0,5);
        onView(allOf(withId(R.id.test_fragment_content_description_text),withText(startsWith(textStartsWith)))).check(matches(isDisplayed()));
    }

    @Test
    public void testEndsWith(){
        String textEndsWith = "Example Content Description Dest";
        textEndsWith = textEndsWith.substring(textEndsWith.length() - 4);
        onView(allOf(withId(R.id.test_fragment_content_description_text),withText(endsWith(textEndsWith)))).check(matches(isDisplayed()));
    }

    @Test
    public void testScrollButton(){
        //测试滑动
        onView(withId(R.id.offscreen_button)).check(matches(not(isDisplayed()))).perform(scrollTo()).check(matches(isDisplayed()));
    }

    @Test
    public void testScrollDown(){
        onView(withId(R.id.scroll_view)).perform(swipeUp());
    }

    @Test
    public void testScrollUp(){
        onView(withId(R.id.scroll_view)).perform(swipeDown());
    }

    @Test
    public void testSelectWithHint(){
        onView(withHint(R.string.example_text_hint)).check(matches(isDisplayed()));
    }
}