package developer.subhadeep.threatball.viewmodel;

import static org.junit.Assert.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import developer.subhadeep.threatball.AppConstants;
import developer.subhadeep.threatball.model.datastore.GameData;
import developer.subhadeep.threatball.viewmodel.support.FakeDataStore;
import developer.subhadeep.threatball.viewmodel.support.LiveDataTestUtil;


@RunWith(AndroidJUnit4.class)
public class ViewModel_Activity1Test {

    private FakeDataStore fakeDataStore;
    private ViewModel_Activity1 viewModel_activity1;

    @Rule public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void setUp () {
        fakeDataStore = new FakeDataStore();
        viewModel_activity1 = new ViewModel_Activity1(fakeDataStore);
        viewModel_activity1.subscribeToRepo();
    }

    @After
    public void tearDown() {
        viewModel_activity1.onCleared();
    }

    @Test
    public void testLiveData () {
        fakeDataStore.store(new GameData(AppConstants.SCORE_TEST_VAL));
        int result = 0;
        try {
            result = LiveDataTestUtil.getOrAwaitValue(viewModel_activity1.getData()).getHighScore();
        } catch (Exception e) {}
        assertTrue( result == AppConstants.SCORE_TEST_VAL);
    }


}