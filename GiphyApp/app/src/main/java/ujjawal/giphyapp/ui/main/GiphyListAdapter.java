package ujjawal.giphyapp.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ujjawal.giphyapp.R;
import ujjawal.giphyapp.data.network.model.MainResponse;
import ujjawal.giphyapp.ui.base.BaseViewHolder;
import ujjawal.giphyapp.ui.details.DetailsActivity;
import ujjawal.giphyapp.utils.AppLogger;

/**
 * Created by Janisharali on 25-05-2017.
 */

public class GiphyListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private Callback mCallback;
    private List<MainResponse.DataInner> mBlogResponseList;

    public GiphyListAdapter(List<MainResponse.DataInner> blogResponseList) {
        mBlogResponseList = blogResponseList;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        if (mBlogResponseList != null && mBlogResponseList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        return mBlogResponseList.size();
    }

    public void addItems(List<MainResponse.DataInner> blogList) {
        mBlogResponseList.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearData() {
        mBlogResponseList.clear();
        notifyDataSetChanged();
    }

    public interface Callback {
        void onRepoEmptyViewRetryClick();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.cover_image_view)
        ImageView coverImageView;

        @BindView(R.id.title_text_view)
        TextView titleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            coverImageView.setImageDrawable(null);
            titleTextView.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            final MainResponse.DataInner blog = mBlogResponseList.get(position);

            final String imgUrl = blog.getImages().getPreviewGif().getUrl();
            if (!imgUrl.isEmpty()) {
                Glide.with(itemView.getContext())
                        .load(imgUrl)
                        .into(coverImageView);
            }

            if (blog.getTitle() != null) {
                titleTextView.setText(blog.getTitle());
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (blog.getEmbedUrl() != null) {
                        try {
                            Intent intent = new Intent(itemView.getContext(), DetailsActivity.class);
                            intent.putExtra("originalMp4", blog.getImages().getOriginalMp4().getMp4());
                            itemView.getContext().startActivity(intent);
                        } catch (Exception e) {
                            AppLogger.d("url error");
                        }
                    }
                }
            });
        }
    }
}
