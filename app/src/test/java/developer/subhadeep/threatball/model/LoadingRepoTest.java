package developer.subhadeep.threatball.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.TimeUnit;

import developer.subhadeep.threatball.AppConstants;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import io.reactivex.rxjava3.subscribers.TestSubscriber;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class LoadingRepoTest {

    private LoadingRepo loadingRepo;

    @Before
    public void setUp(){
        loadingRepo = new LoadingRepo();
    }

    @After
    public void tearDown() {
        loadingRepo = null;
    }

    @Test
    public void checkRuntime () {
        loadingRepo.get()
                .test()
                .awaitDone(AppConstants.MIN_LOADING_DELAY_MILLIS, TimeUnit.MILLISECONDS)
                .assertComplete();
    }


}