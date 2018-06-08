package ujjawal.giphyapp.ui.details;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.Surface;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.objectbox.Box;
import io.objectbox.query.Query;
import ujjawal.giphyapp.R;
import ujjawal.giphyapp.data.network.model.ReviewModel;
import ujjawal.giphyapp.data.network.model.ReviewModel_;
import ujjawal.giphyapp.ui.base.BaseActivity;
import ujjawal.giphyapp.ui.main.MainActivity;

/**
 * Created by ujjawal on 30/05/18.
 */

public class DetailsActivity extends BaseActivity implements DetailsMvpView {

    private static final String TAG = "DetailsActivity";
    @Inject
    DetailsMvpPresenter<DetailsMvpView> mPresenter;

    @BindView(R.id.player_view)
    SimpleExoPlayerView simpleExoPlayerView;

    @BindView(R.id.thumbUpIcon)
    AppCompatImageView thumbUpIcon;
    @BindView(R.id.thumbDownIcon)
    AppCompatImageView thumbDownIcon;
    @BindView(R.id.thumbUpText)
    TextView thumbUpText;
    @BindView(R.id.thumbDownText)
    TextView thumbDownText;

    SimpleExoPlayer player;

    String mainMp4Video;
    String vidId;
    long iD;
    int upVote = 0, downVote = 0;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detais);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        mainMp4Video = getIntent().getStringExtra("originalMp4");
        vidId = getIntent().getStringExtra("vidId");

        setUp();
    }

    @Override
    protected void setUp() {

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        LoadControl loadControl = new DefaultLoadControl();

        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
        simpleExoPlayerView = new SimpleExoPlayerView(this);
        simpleExoPlayerView = (SimpleExoPlayerView) findViewById(R.id.player_view);

        simpleExoPlayerView.setUseController(true);
        simpleExoPlayerView.requestFocus();

        simpleExoPlayerView.setPlayer(player);

        Uri mp4VideoUri = Uri.parse(mainMp4Video);

        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "GiphyApp"), null);

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        MediaSource videoSource = new ExtractorMediaSource(mp4VideoUri, dataSourceFactory,
                extractorsFactory, null, null);

        player.prepare(videoSource);

        player.setPlayWhenReady(true);

        getReviews();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop()...");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart()...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume()...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause()...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy()...");
        player.release();
        mPresenter.onDetach();
    }

    @OnClick(R.id.thumbUpIcon)
    @Override
    public void doThumbsUp() {
        upVote++;
        mPresenter.updateTextCount(iD, upVote, downVote, vidId);
    }

    @OnClick(R.id.thumbDownIcon)
    @Override
    public void doThumbsDown() {
        downVote++;
        mPresenter.updateTextCount(iD, upVote, downVote, vidId);
    }

    @Override
    public void getReviews() {
        Box<ReviewModel> voteBox = getBox().boxFor(ReviewModel.class);
        Query<ReviewModel> query = voteBox.query().equal(ReviewModel_.gifId, vidId).build();
        List<ReviewModel> votes = query.find();

        if (votes.size() > 0) {
            iD = votes.get(0).getId();
            upVote = votes.get(0).getThumbUp();
            downVote = votes.get(0).getThumbDown();
            Log.e("Votes_ID", "--" + votes.get(0).getId());
            Log.e("Votes_VID", "--" + votes.get(0).getGifId());
            Log.e("Votes_UP", "--" + votes.get(0).getThumbUp());
            Log.e("Votes_DN", "--" + votes.get(0).getThumbDown());
        } else {
            iD = 0;
        }
        setUpImageView(upVote, downVote);
    }

    private void setUpImageView(int thumbUp, int thumbDown) {
        if (thumbUp == 0) {
            thumbUpIcon.setAlpha(0.1f);
        } else {
            thumbUpIcon.setAlpha(1f);
        }
        if (thumbDown == 0) {
            thumbDownIcon.setAlpha(0.1f);
        } else {
            thumbDownIcon.setAlpha(1f);
        }
        thumbUpText.setText(thumbUp + " Votes");
        thumbDownText.setText(thumbDown + " Votes");
    }

}
