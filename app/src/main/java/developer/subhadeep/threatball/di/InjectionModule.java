package developer.subhadeep.threatball.di;

import android.content.Context;
import android.hardware.SensorManager;
import android.util.DisplayMetrics;

import javax.inject.Singleton;

import androidx.datastore.rxjava3.RxDataStore;
import androidx.datastore.rxjava3.RxDataStoreBuilder;


import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import developer.subhadeep.threatball.AppConstants;
import developer.subhadeep.threatball.model.datastore.Data;
import developer.subhadeep.threatball.model.datastore.DataSerializer;


/**
 *
 * <h1> InjectionModule </h1>
 *
 * <p> This helps in Dependency Injection. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-20
 *
 */

@Module
@InstallIn(SingletonComponent.class)
public class InjectionModule {


    /**
     * This method aids the {@link InjectionModule#provideDataStore(Context, DataSerializer)}
     * method to create a {@link RxDataStore} object that is available during application lifetime.
     *
     * @return A non null {@link DataSerializer} object.
     */
    @Singleton
    @Provides
    public DataSerializer provideGameDataSerializer () {
        return new DataSerializer();
    }


    /**
     * This method aids the injection of a {@link RxDataStore} object that helps store and retrieve data.
     *
     * @return A non null {@link RxDataStore} object.
     */
    @Singleton
    @Provides
    public RxDataStore<Data> provideDataStore (@ApplicationContext Context context,
                                              DataSerializer dataSerializer) {
        return new RxDataStoreBuilder<> (
                context, AppConstants.DATA_FILE_NAME, dataSerializer
        ).build();
    }


    /**
     * This method aids the injection of a {@link DisplayMetrics} object that information about the display.
     *
     * @return A non null {@link DisplayMetrics} object.
     */
    @Singleton
    @Provides
    public DisplayMetrics provideDisplayMetrics (@ApplicationContext Context context) {
        return context.getResources().getDisplayMetrics();
    }


    /**
     * This method aids the injection of a {@link SensorManager} object that allows working with sensors.
     *
     * @return A non null {@link SensorManager} object.
     */
    @Singleton
    @Provides
    public SensorManager provideSensorManager (@ApplicationContext Context context) {
        return (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
    }



}
