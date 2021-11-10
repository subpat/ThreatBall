package developer.subhadeep.threatball.model.support;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Arrays;

import developer.subhadeep.threatball.model.position.Positions;

public class FakeArtifactsPosition implements Positions {

    public static final Point BALL_POSITION = new Point(100, 150);
    public static final Point WIN_HOLE_POSITION = new Point(50, 35);
    public static final Point[] OBSTACLES_POSITION = new Point[] {new Point(23,56), new Point(55, 90)};


    @Override
    public boolean generate() {
        return true;
    }

    @Override
    public Point getBallPosition() {
        return BALL_POSITION;
    }

    @Override
    public Point getWinHolePosition() {
        return WIN_HOLE_POSITION;
    }

    @Override
    public ArrayList<Point> getObstaclesPosition() {
        ArrayList<Point> points = new ArrayList<>();
        for (Point p: OBSTACLES_POSITION)
            points.add(p);
        return points;
    }

}
