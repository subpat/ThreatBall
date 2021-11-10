package developer.subhadeep.threatball.viewmodel.support;

import developer.subhadeep.threatball.model.Loading;
import io.reactivex.rxjava3.core.Completable;

public class FakeLoadingErrorRepo implements Loading {

    @Override
    public Completable get() {
        return Completable.error(new Throwable("Fake error"));
    }

}
