package ujjawal.giphyapp.ui.details;

import com.google.android.exoplayer2.Player;

import javax.inject.Inject;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.reactivex.disposables.CompositeDisposable;
import ujjawal.giphyapp.data.DataManager;
import ujjawal.giphyapp.data.network.model.ReviewModel;
import ujjawal.giphyapp.ui.base.BasePresenter;
import ujjawal.giphyapp.utils.rx.SchedulerProvider;

/**
 * Created by ujjawal on 30/05/18.
 */

public class DetailsPresenter<V extends DetailsMvpView> extends BasePresenter<V>
        implements DetailsMvpPresenter<V> {

    private static final String TAG = "MainPresenter";

    @Inject
    public DetailsPresenter(DataManager dataManager, SchedulerProvider schedulerProvider,
                            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void updateTextCount(long id, boolean isUp, String vidId) {
        Box<ReviewModel> voteBox = getMvpView().getBox().boxFor(ReviewModel.class);

        ReviewModel rm = new ReviewModel();
        rm.setId(id);
        rm.setGifId(vidId);
        if (isUp) {
            rm.setThumbDown(0);
            rm.setThumbUp(1);
        } else {
            rm.setThumbDown(1);
            rm.setThumbUp(0);
        }
        voteBox.put(rm);

        getMvpView().getReviews();
    }
}