package com.deloitte.flickerimageviewer;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.deloitte.flickerimageviewer.ui.MainActivity;
import com.deloitte.flickerimageviewer.ui.main.MainFragment;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertNotNull;


@RunWith(AndroidJUnit4.class)
public class MainFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mMainActivity = null;

    @Before
    public void setUp() throws Exception {
        mMainActivity = mainActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch_recycler_view_is_not_null() {
        ConstraintLayout programs_fragment_layout = (ConstraintLayout) mMainActivity.findViewById(R.id.main_fragment);
        assertNotNull(programs_fragment_layout);

        MainFragment programsFragment = new MainFragment();
        mMainActivity.getSupportFragmentManager().beginTransaction().add(programs_fragment_layout.getId(),
                programsFragment).commitAllowingStateLoss();

        getInstrumentation().waitForIdleSync();

        View recyclerView = programsFragment.getView().findViewById(R.id.rv_photo_list);
        assertNotNull(recyclerView);
    }

    @Test
    public void testLaunch_progressbar_is_not_null() {
        ConstraintLayout programs_fragment_layout = (ConstraintLayout) mMainActivity.findViewById(R.id.main_fragment);
        assertNotNull(programs_fragment_layout);

        MainFragment programsFragment = new MainFragment();
        mMainActivity.getSupportFragmentManager().beginTransaction().add(programs_fragment_layout.getId(),
                programsFragment).commitAllowingStateLoss();

        getInstrumentation().waitForIdleSync();

        View pagingProgressBar = programsFragment.getView().findViewById(R.id.paging_progressbar);
        assertNotNull(pagingProgressBar);
    }

    @After
    public void tearDown() throws Exception {
        mMainActivity = null;
        mainActivityTestRule = null;
    }

}
