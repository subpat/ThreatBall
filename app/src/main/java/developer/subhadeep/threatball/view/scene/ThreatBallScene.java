package developer.subhadeep.threatball.view.scene;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import developer.subhadeep.threatball.R;
import developer.subhadeep.threatball.model.position.ArtifactsPosition;
import developer.subhadeep.threatball.model.position.Positions;
import developer.subhadeep.threatball.view.artifacts.ArtifactHandler;
import developer.subhadeep.threatball.view.artifacts.ArtifactsManager;
import developer.subhadeep.threatball.view.artifacts.Ball;


/**
 *
 * <h1> ThreatBallScene </h1>
 *
 * <p> This is the one and only scene used in our application.
 * It consists of a ball, a destination hole and several randomly generate obstacles.</>
 *
 * <p> The objective of the game is to steer the ball to the destination hole
 * using physical movement of the smartphone.</>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public class ThreatBallScene implements Scene {

    /**
     * Used to manipulate the artifacts part of this scene, namely the ball, destination hole
     * and several obstacles.
     */
    private ArtifactHandler artifactHandler;


    /**
     * Contains the background image used in this scene.
     */
    private BitmapDrawable backgroundTileBitmap;


    /**
     * Constructor of this class.
     *
     * @param context used to load images.
     * @param artifactsManager used to manipulate game artifacts.
     */
    @Inject
    public ThreatBallScene (@ApplicationContext Context context, ArtifactsManager artifactsManager) {
        this.artifactHandler = artifactsManager;
        this.backgroundTileBitmap = new BitmapDrawable(context.getResources(),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.background));
    }


    /**
     * This method is used to clear all the artifacts that are a part of this scene.
     */
    @Override
    public void clearScene() {
        artifactHandler.clear();
    }


    /**
     * This method may used to update the current {@link Scene}.
     *
     * @param position the change in position identified using the sensor data, required by the {@link Ball} artifact.
     * @param inPlay indicates which Animation is to be displayed, useful especially for a {@link Ball} object.
     * @param message is a {@link String} to be displayed on screen.
     */
    @Override
    public void update(Point position, boolean inPlay, String message) {
        artifactHandler.update (position, inPlay, message);
    }


    /**
     * This method is used to receive the initial position of game artifacts.
     *
     * @param initialPositions contains the initial position of all artifacts.
     */
    @Override
    public void init (Positions initialPositions) {
        artifactHandler.init (initialPositions);
    }


    /**
     * This method allows displaying the current scene on screen.
     *
     * @param canvas is used to draw the scene.
     */
    @Override
    public void draw(Canvas canvas) {
        if (backgroundTileBitmap != null) {
            backgroundTileBitmap.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            backgroundTileBitmap.setBounds(canvas.getClipBounds());
            backgroundTileBitmap.draw(canvas);
        }
        else canvas.drawColor(Color.WHITE);

        if (artifactHandler != null) artifactHandler.draw(canvas);
    }


}
