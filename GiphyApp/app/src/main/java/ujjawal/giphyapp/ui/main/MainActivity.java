package ujjawal.giphyapp.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ujjawal.giphyapp.R;
import ujjawal.giphyapp.data.network.model.MainResponse;
import ujjawal.giphyapp.ui.base.BaseActivity;

/**
 * Created by ujjawal on 30/05/18.
 */

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @Inject
    GiphyListAdapter mBlogAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.blog_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.searchEt)
    EditText searchEt;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @OnClick({R.id.searchBtn})
    void searchClick() {
        String search_ = searchEt.getText().toString().trim();
        if (search_.isEmpty()) {
            showMessage("Please enter Search Keyword.");
            return;
        }
        hideKeyboard();
        mPresenter.doSearch(search_);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    protected void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mBlogAdapter);
    }

    @Override
    public void updateAdapter(List<MainResponse.DataInner> blogList) {
        mBlogAdapter.clearData();
        mBlogAdapter.addItems(blogList);
    }
}
