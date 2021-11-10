package developer.subhadeep.threatball.view.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import dagger.hilt.android.qualifiers.ApplicationContext;
import developer.subhadeep.threatball.model.position.ArtifactsPosition;
import developer.subhadeep.threatball.model.position.Positions;
import developer.subhadeep.threatball.view.artifacts.Ball;
import developer.subhadeep.threatball.view.scene.Choreographer;
import developer.subhadeep.threatball.view.scene.Scene;
import developer.subhadeep.threatball.view.scene.SceneManager;

/**
 *
 * <h1> GamePanelView </h1>
 *
 * <p> This is the game panel where different scenes of the game may be displayed. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public class GamePanelView extends SurfaceView implements SurfaceHolder.Callback, GamePanel {


    /**
     * It allows manipulation of the display surface.
     */
    private SurfaceHolder surfaceHolder;


    /**
     * It allow control over the scenes being displayed on screen.
     */
    private Choreographer sceneManager;


    /**
     * The constructor of this class.
     *
     * @param context is required by the {@link SurfaceView} class which is extended here.
     * @param sceneManager allows working with different scenes in the game.
     */
    @Inject
    public GamePanelView(@ApplicationContext Context context, SceneManager sceneManager) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        setFocusable(true);
        this.sceneManager = sceneManager;
    }


    /**
     * This method is automatically called when the surface is created.
     *
     * @param holder is the {@link SurfaceHolder} whose surface is being created.
     */
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {}


    /**
     * This method get called automatically when some changes to the surface occurs.
     *
     * @param holder is the {@link SurfaceHolder} whose surface has changed.
     * @param format is the new {@link android.graphics.PixelFormat} of the surface.
     * @param width is the integer width of the surface.
     * @param height is the integer height of the surface.
     */
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {}


    /**
     * This method get called automatically when the surface is being destroyed.
     *
     * @param holder is the {@link SurfaceHolder} whose surface is being destroyed.
     */
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {}


    /**
     * This method is used to request the {@link Choreographer} to draw the current {@link Scene}.
     *
     * @param canvas is used to draw the image of the {@link Scene}.
     */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        sceneManager.draw(canvas);
    }


    /**
     * This method is called by the application when it is time to draw a new frame to maintain the required FPS.
     */
    @Override
    public void requestDraw() {
        if (surfaceHolder != null) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                draw(canvas);
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }


    /**
     * This method is used to update the current {@link Scene} handled by the {@link Choreographer}.
     *
     * @param position the change in position identified using the sensor data.
     * @param inPlay indicates which Animation is to be displayed, useful especially for a {@link Ball} object.
     * @param message is a {@link String} to be displayed on screen.
     */
    @Override
    public void update (Point position, boolean inPlay, String message) { sceneManager.update(position, inPlay, message); }


    /**
     * This method is used to propagate the initial artifacts' position to the {@link Choreographer}.
     *
     * @param initialPositions contains the initial position of all artifacts.
     */
    @Override
    public void init (Positions initialPositions) {
        sceneManager.init (initialPositions);
    }


    /**
     * This method can be used to set a new active scene.
     *
     * @param scene is the desired game scene to be displayed.
     */
    @Override
    public void addNewSceneToManager (Scene scene) {
        sceneManager.startNewScene(scene);
    }


    /**
     * This method is used to remove an active scene.
     */
    @Override
    public void removeActiveSceneFromManager () {
        sceneManager.stopActiveScene();
    }


    /**
     * This method is used to query whether there is a current active scene being displayed.
     *
     * @return a boolean value.
     */
    @Override
    public boolean hasActiveScene () {
        return sceneManager.hasActiveScene();
    }


}
