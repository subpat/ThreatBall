package developer.subhadeep.threatball.model.position;

import java.util.ArrayList;
import javax.inject.Inject;
import android.graphics.Point;
import androidx.core.util.Pair;
import developer.subhadeep.threatball.AppConstants;



/**
 *
 * <h1> ArtifactsPosition </h1>
 *
 * <p> This class generates and stores the positions of all game artifacts. </>
 * <p> Note that the number of artifacts and their positions are randomly generated. </>
 * <p> It heavily depends on a {@link Generator} like {@link PositionGenerator} for working. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public class ArtifactsPosition implements Positions {

    /**
     * It aids in the generation of random (x,y) coordinates.
     */
    private Generator generator;


    /**
     * Stores a draft of the artifacts type and location when artifacts are being populated.
     */
    private Pair<ARTIFACT_TYPE, Point>[][] artifactsLocation;


    /**
     * This variable stores the final position of the ball once the artifact population process is complete.
     */
    private Point ballPosition;


    /**
     * This variable stores the final position of the destination hole once the artifact population process is complete.
     */
    private Point winHolePosition;


    /**
     * This variable stores the final position of all obstacles once the artifact population process is complete.
     */
    private ArrayList<Point> obstaclesPosition;



    /**
     * The constructor to create an instance of this class.
     *
     * @param generator is taken advantage to generate random coordinates.
     */
    @Inject
    public ArtifactsPosition(PositionGenerator generator) {
        this.generator = generator;
    }


    /**
     * This method starts the artifact generation process. It must be called before using {@link ArtifactsPosition#getBallPosition()},
     * {@link ArtifactsPosition#getWinHolePosition()} or {@link ArtifactsPosition#getObstaclesPosition()}.
     *
     * @return a boolean that denotes whether the artifact generation process has been successful.
     */
    @Override
    public boolean generate () {
        reset ();
        if (generator.getRowMax() > 1 && generator.getColMax() > 0) { // at least one col and two rows
            generateBallPosition ();
            generateDestination ();
            generateDestinationLevelObstacles ();
            generateIntermediateLevelObstacles ();
            generateFinalResult ();
            return true;
        }
        return false;
    }



    /**
     * This method is used to reset the values of all global variables.
     */
    private void reset () {
        ballPosition = null;
        winHolePosition = null;
        obstaclesPosition = new ArrayList<>();
        artifactsLocation = new Pair[generator.getRowMax()][generator.getColMax()];
    }


    /**
     * This method generates the position of the ball with which the player will interact.
     * The ball is always generated close to the bottom of the screen with a bit of horizontal variation.
     */
    private void generateBallPosition () {
        int ballRow = generator.getRowMax () - 1; // row is fixed to the bottom of the screen
        int ballCol = generator.getRandomInt (generator.getColMax());
        Point point = generator.getRandomPoint (ballRow, ballCol, AppConstants.BALL_SIZE);
        artifactsLocation[ballRow][ballCol] = new Pair<>(ARTIFACT_TYPE.BALL, point);
    }


    /**
     * This method generates the position of the ball with which the player will interact.
     * The ball is always generated close to the bottom of the screen with a bit of horizontal variation.
     */
    private void generateDestination () {
        int destinationRow = 0; // row is fixed to the top of the screen
        int destinationCol = generator.getRandomInt (generator.getColMax());
        Point point = generator.getRandomPoint (destinationRow, destinationCol, AppConstants.HOLE_SIZE);
        artifactsLocation[destinationRow][destinationCol] = new Pair<>(ARTIFACT_TYPE.DESTINATION, point);
    }


    /**
     * This method generates the position of a variable number of obstacles near the destination hole.
     */
    private void generateDestinationLevelObstacles () {
        int obstacleRow = 0;
        for (int obstacleCol = 0; obstacleCol < generator.getColMax(); obstacleCol++) {
            if (artifactsLocation[obstacleRow][obstacleCol] == null && generator.getRandomInt(2) == 1) {
                Point point = generator.getRandomPoint (obstacleRow, obstacleCol, AppConstants.HOLE_SIZE);
                artifactsLocation[obstacleRow][obstacleCol] = new Pair<>(ARTIFACT_TYPE.OBSTACLE, point);
            }
        }
    }


    /**
     * This method generates the position of obstacles between the ball and its destination.
     */
    private void generateIntermediateLevelObstacles () {
        for (int obstacleRow = 1; obstacleRow < generator.getRowMax() - 1; obstacleRow++) {
            for (int obstacleCol = 0; obstacleCol < generator.getColMax(); obstacleCol++) {
                if (generator.getRandomInt(2) == 1) { // Flip a coin to decide whether to put an obstacle ;)
                    Point point = generator.getRandomPoint(obstacleRow, obstacleCol, AppConstants.HOLE_SIZE);
                    artifactsLocation[obstacleRow][obstacleCol] = new Pair<>(ARTIFACT_TYPE.OBSTACLE, point);
                }
            }
        }
    }


    /**
     * This method is automatically called once the position of the ball, destination and obstacles have been generated.
     * It prepares a fresh actionable summary of the generated information.
     */
    private void generateFinalResult () {
        for (int i = 0; i < generator.getRowMax(); i++) {
            for (int j = 0; j < generator.getColMax(); j++) {
                if (artifactsLocation[i][j] != null) {
                    switch (artifactsLocation[i][j].first) {
                        case BALL:
                            ballPosition = artifactsLocation[i][j].second;
                            break;
                        case DESTINATION:
                            winHolePosition = artifactsLocation[i][j].second;
                            break;
                        case OBSTACLE:
                            obstaclesPosition.add(artifactsLocation[i][j].second);
                            break;
                    }
                }
            }
        }
    }


    /**
     * This method can be used to query the location of the ball.
     *
     * @return a {@link Point} object.
     */
    @Override
    public Point getBallPosition () { return ballPosition; }


    /**
     * This method can be used to query the location of the destination hole.
     *
     * @return a {@link Point} object.
     */
    @Override
    public Point getWinHolePosition () { return winHolePosition; }


    /**
     * This method can be used to query the location of the obstacles.
     *
     * @return a {@link ArrayList} of {@link Point}.
     */
    @Override
    public ArrayList<Point> getObstaclesPosition() { return obstaclesPosition; }

    
}
