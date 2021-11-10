package developer.subhadeep.threatball.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import developer.subhadeep.threatball.viewmodel.support.FakeLoadingErrorRepo;
import developer.subhadeep.threatball.viewmodel.support.FakeLoadingOkRepo;
import developer.subhadeep.threatball.viewmodel.support.LiveDataTestUtil;

import static org.junit.Assert.assertTrue;

public class ViewModel_Activity2Test {

    private ViewModel_Activity2 viewModelOk;
    private ViewModel_Activity2 viewModelError;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        viewModelOk = new ViewModel_Activity2 (new FakeLoadingOkRepo());
        viewModelError = new ViewModel_Activity2 (new FakeLoadingErrorRepo());
    }

    @After
    public void tearDown() {
        viewModelOk.onCleared();
        viewModelError.onCleared();
        viewModelOk = null;
        viewModelError = null;
    }

    @Test
    public void okTest() {
        boolean result = false;
        viewModelOk.subscribeToRepo();
        try {
            result = LiveDataTestUtil.getOrAwaitValue(viewModelOk.getData()).booleanValue();
        } catch (Exception e) {}
        assertTrue (result == true);
    }

    @Test
    public void errorTest () {
        boolean result = true;
        viewModelError.subscribeToRepo();
        try {
            result = LiveDataTestUtil.getOrAwaitValue(viewModelError.getData()).booleanValue();
        } catch (Exception e) {}
        assertTrue (result == false);
    }


}