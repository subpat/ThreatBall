package developer.subhadeep.threatball.viewmodel;

import android.content.Context;
import android.graphics.Point;
import android.hardware.SensorManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.datastore.rxjava3.RxDataStore;
import androidx.datastore.rxjava3.RxDataStoreBuilder;
import androidx.lifecycle.LiveData;
import androidx.test.core.app.ApplicationProvider;
import developer.subhadeep.threatball.AppConstants;
import developer.subhadeep.threatball.STATE;
import developer.subhadeep.threatball.model.ArtifactsInitializationRepo;
import developer.subhadeep.threatball.model.FrameControlRepo;
import developer.subhadeep.threatball.model.LocalDataStoreRepo;
import developer.subhadeep.threatball.model.datastore.Data;
import developer.subhadeep.threatball.model.datastore.DataSerializer;
import developer.subhadeep.threatball.model.position.Positions;
import developer.subhadeep.threatball.viewmodel.misc.StateHandler;
import developer.subhadeep.threatball.viewmodel.misc.StateManager;
import developer.subhadeep.threatball.viewmodel.support.FakeArtifactsPosition;
import developer.subhadeep.threatball.viewmodel.support.FakeOrientationChangeRepo;
import developer.subhadeep.threatball.viewmodel.support.LiveDataTestUtil;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class ViewModel_Activity3Test {

    private ViewModel_Activity3 viewModel_activity3;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();

        DataSerializer dataSerializer = new DataSerializer();
        RxDataStore<Data> rxDataStore = new RxDataStoreBuilder<>(
                context, AppConstants.TEST_FILE_NAME, dataSerializer
        ).build();

        viewModel_activity3 = new ViewModel_Activity3(
                new StateManager(), new LocalDataStoreRepo(rxDataStore), new FrameControlRepo(),
                new ArtifactsInitializationRepo(new FakeArtifactsPosition()),
                new FakeOrientationChangeRepo((SensorManager) context.getSystemService(context.SENSOR_SERVICE)));
    }

    @After
    public void tearDown() {
        viewModel_activity3.unSubscribeFromRepo ();
        viewModel_activity3 = null;
    }

    @Test
    public void frameLiveDataTest () {
        Long aLong = null;
        viewModel_activity3.onResume();
        try {
            aLong = LiveDataTestUtil.getOrAwaitValue(viewModel_activity3.getData());
        } catch (Exception e) {}
        // Check if frame related data is received.
        assertTrue(aLong != null);
    }


    @Test
    public void initialPositionsLiveDataTest () {
        Positions positions = null;
        viewModel_activity3.onResume();
        viewModel_activity3.initGame();
        try {
             positions = LiveDataTestUtil.getOrAwaitValue(viewModel_activity3.getInitialPositionData());
        } catch (Exception e) {}

        // Check if the ball position has been correctly generated
        assertTrue(positions.getBallPosition().x == FakeArtifactsPosition.BALL_POSITION.x);
        assertTrue(positions.getBallPosition().y == FakeArtifactsPosition.BALL_POSITION.y);

        // Check if the destination hole position has been correctly generated
        assertTrue(positions.getWinHolePosition().x == FakeArtifactsPosition.WIN_HOLE_POSITION.x);
        assertTrue(positions.getWinHolePosition().y == FakeArtifactsPosition.WIN_HOLE_POSITION.y);

        // Check if generated obstacle position has been correctly received
        ArrayList<Point> obstaclesPosition = positions.getObstaclesPosition();
        for (int i = 0; i < obstaclesPosition.size(); i++) {
            assertTrue(obstaclesPosition.get(i).x == FakeArtifactsPosition.OBSTACLES_POSITION[i].x);
            assertTrue(obstaclesPosition.get(i).y == FakeArtifactsPosition.OBSTACLES_POSITION[i].y);
        }
    }

    @Test
    public void changeInPositionsLiveDataTest () {
        Point changeInPosition = null;
        viewModel_activity3.onResume();
        viewModel_activity3.initGame();
        viewModel_activity3.startGame();
        try {
            changeInPosition = LiveDataTestUtil.getOrAwaitValue(viewModel_activity3.getPositionChangeUpdate());
        } catch (Exception e) {}

        // Check if the ball position has been correctly generated
        assertTrue(changeInPosition.x == FakeOrientationChangeRepo.point.x);
        assertTrue(changeInPosition.y == FakeOrientationChangeRepo.point.y);
    }


}