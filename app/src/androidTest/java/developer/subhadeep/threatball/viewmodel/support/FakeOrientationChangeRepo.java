package developer.subhadeep.threatball.viewmodel.support;

import android.graphics.Point;
import android.hardware.SensorManager;

import developer.subhadeep.threatball.model.sensor.OrientationManager;
import io.reactivex.rxjava3.core.Single;

public class FakeOrientationChangeRepo extends OrientationManager {

    public static Point point = new Point(3,5);

    public FakeOrientationChangeRepo(SensorManager sensorManager) {
        super(sensorManager);
    }

    @Override
    public void onOrientationChanged(float[] orientationData) {}

    @Override
    public boolean isRegistered() {
        return true;
    }

    @Override
    public Single<Point> requestChangeInPosition() {
        return Single.just(point);
    }


}
