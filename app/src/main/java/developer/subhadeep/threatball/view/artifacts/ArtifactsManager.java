package developer.subhadeep.threatball.view.artifacts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;
import developer.subhadeep.threatball.AppConstants;
import developer.subhadeep.threatball.R;
import developer.subhadeep.threatball.model.position.Positions;
import developer.subhadeep.threatball.view.extra.BallAnimation;


/**
 *
 * <h1> ArtifactsManager </h1>
 *
 * <p> This class helps us maintain the different game artifacts displayed on the screen. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

@Singleton
public class ArtifactsManager implements ArtifactHandler {


    /**
     * This variable stores an {@link Ball} object.
     */
    private GameCircle ball;


    /**
     * This variable stores the destination {@link Hole} object.
     */
    private GameCircle destination;


    /**
     * This variable stores an {@link ArrayList} of obstacles {@link Hole}s.
     */
    private ArrayList<GameCircle> obstacles;


    /**
     * This variable stores a {@link String} message that is to be display.
     * Note that it may be null, in which case it is not displayed.
     */
    private String message;

    /**
     * This variable stores an object of the {@link Context} class.
     */
    private Context context;


    /**
     * Constructor used to instantiate this class.
     *
     * @param context allows accessing {@link Bitmap}s and {@link DisplayMetrics}
     *                which are necessary information for this class.
     */
    @Inject
    public ArtifactsManager (@ApplicationContext Context context) {
        clear();
        this.context = context;
    }


    /**
     * This method allows clearing all artifacts related information
     * especially useful when the scene is being destroyed.
     */
    @Override
    public void clear () {
        this.message = null;
        this.ball = null;
        this.destination = null;
        obstacles = null;
    }


    /**
     * This method allows the generation and initialization of the game artifacts for the current scene.
     *
     * @param initialPositions contains the initial position of all artifacts.
     */
    @Override
    public void init (Positions initialPositions) {
        setUpBall(initialPositions);
        setUpDestination(initialPositions);
        setUpObstacles(initialPositions);
    }


    /**
     * This method is used to setup the {@link Ball} artifact.
     *
     * @param initialPositions contains the initial position of all artifacts.
     */
    private void setUpBall (Positions initialPositions) {
        Bitmap ballBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball);
        ballBitmap = Bitmap.createScaledBitmap(ballBitmap, AppConstants.BALL_SIZE, AppConstants.BALL_SIZE, true);
        BallAnimation ballDefaultAnimation = new BallAnimation (new Bitmap[]{ballBitmap},0.03f);

        Bitmap ballFall[] = new Bitmap[] {
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.ball_fall_1), AppConstants.BALL_SIZE, AppConstants.BALL_SIZE, true),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.ball_fall_2), AppConstants.BALL_SIZE, AppConstants.BALL_SIZE, true),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.ball_fall_3), AppConstants.BALL_SIZE, AppConstants.BALL_SIZE, true),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.ball_fall_4), AppConstants.BALL_SIZE, AppConstants.BALL_SIZE, true),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.ball_fall_5), AppConstants.BALL_SIZE, AppConstants.BALL_SIZE, true)
        };
        BallAnimation ballFallAnimation = new BallAnimation (ballFall, 0.1f);
        ball = new Ball(initialPositions.getBallPosition(), ballBitmap.getWidth() / 2, ballDefaultAnimation, ballFallAnimation);
    }


    /**
     * This method is used to setup the destination {@link Hole} artifact.
     *
     * @param initialPositions contains the initial position of all artifacts.
     */
    private void setUpDestination(Positions initialPositions) {
        Bitmap destinationBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.win);
        destinationBitmap = Bitmap.createScaledBitmap(destinationBitmap, AppConstants.HOLE_SIZE, AppConstants.HOLE_SIZE, true);
        destination = new Hole(destinationBitmap, initialPositions.getWinHolePosition());
    }


    /**
     * This method is used to setup the obstacle {@link Hole} artifacts.
     *
     * @param initialPositions contains the initial position of all artifacts.
     */
    private void setUpObstacles(Positions initialPositions) {
        obstacles = new ArrayList<>();
        ArrayList<Point> positions = initialPositions.getObstaclesPosition();
        if (positions != null && !positions.isEmpty()) {
            Bitmap obstacleBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.lose);
            obstacleBitmap = Bitmap.createScaledBitmap(obstacleBitmap, AppConstants.HOLE_SIZE, AppConstants.HOLE_SIZE, true);
            for (Point position: positions) {
                obstacles.add( new Hole (obstacleBitmap, position));
            }
        }
    }


    /**
     * This method allows updating the position and interpretation of the artifact.
     * It is only useful in class of a {@link Ball} object.
     *
     * @param position the change in position of the artifact. It is only used by the {@link Ball} object.
     * @param inPlay indicates which Animation is to be displayed, useful especially for a {@link Ball}.
     * @param message is a {@link String} to be displayed on screen.
     */
    @Override
    public void update (Point position, boolean inPlay, String message) {
        if (ball != null)   ball.update(position, inPlay);
        this.message = message;
    }


    /**
     * This method allows the interpretation of the artifacts.
     *
     * @param canvas is used to draw the image of the artifacts.
     */
    @Override
    public void draw (Canvas canvas) {
        if (obstacles != null && !obstacles.isEmpty()) {
            for (GameCircle obstacle: obstacles)
                obstacle.draw(canvas);
        }
        if (destination != null) destination.draw(canvas);
        if (ball != null) ball.draw(canvas);
        if (message != null) drawText(canvas);
    }


    /**
     * This method allow the display of a custom text message.
     *
     * @param canvas is used to draw the text message.
     */
    private void drawText (Canvas canvas) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, AppConstants.TEXT_SIZE, displayMetrics));
        textPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics metric = textPaint.getFontMetrics();
        int textHeight = (int) Math.ceil(metric.descent - metric.ascent);
        int y = (int)(textHeight - metric.descent);
        canvas.drawText(message, displayMetrics.widthPixels/2, (displayMetrics.heightPixels - y)/2, textPaint);
    }


}
