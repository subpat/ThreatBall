package developer.subhadeep.threatball.model;

import android.graphics.Point;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import developer.subhadeep.threatball.model.position.Positions;
import developer.subhadeep.threatball.model.support.FakeArtifactsPosition;


@RunWith(JUnit4.class)
public class ArtifactsInitializationRepoTest {

    private ArtifactsInitializationRepo  artifactsInitializationRepo;

    @Before
    public void setUp() {
        artifactsInitializationRepo = new ArtifactsInitializationRepo (new FakeArtifactsPosition());
    }

    @After
    public void tearDown() {
        artifactsInitializationRepo = null;
    }

    @Test
    public void testBallPosition () {
        Positions positions = artifactsInitializationRepo.get().blockingGet();
        assertTrue(positions.getBallPosition().x == FakeArtifactsPosition.BALL_POSITION.x);
        assertTrue(positions.getBallPosition().y == FakeArtifactsPosition.BALL_POSITION.y);
    }

    @Test
    public void testDestinationPosition () {
        Positions positions = artifactsInitializationRepo.get().blockingGet();
        assertTrue(positions.getWinHolePosition().x == FakeArtifactsPosition.WIN_HOLE_POSITION.x);
        assertTrue(positions.getWinHolePosition().y == FakeArtifactsPosition.WIN_HOLE_POSITION.y);
    }

    @Test
    public void testObstaclesPosition () {
        Positions positions = artifactsInitializationRepo.get().blockingGet();
        ArrayList<Point> obstaclesPosition = positions.getObstaclesPosition();
        for (int i = 0; i < obstaclesPosition.size(); i++) {
            assertTrue(obstaclesPosition.get(i).x == FakeArtifactsPosition.OBSTACLES_POSITION[i].x);
            assertTrue(obstaclesPosition.get(i).y == FakeArtifactsPosition.OBSTACLES_POSITION[i].y);
        }
    }


}