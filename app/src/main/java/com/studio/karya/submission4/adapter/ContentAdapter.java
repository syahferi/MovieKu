package com.studio.karya.submission4.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.studio.karya.submission4.R;
import com.studio.karya.submission4.menu.activity.DetailActivity;
import com.studio.karya.submission4.model.Content;
import com.studio.karya.submission4.utils.ConvertDate;
import com.studio.karya.submission4.utils.CustomOnItemClick;

import java.util.ArrayList;

import static com.studio.karya.submission4.BuildConfig.IMG_URL;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.CONTENT_URI_MOVIE;
import static com.studio.karya.submission4.db.DDL.DatabaseContract.TableColumns.CONTENT_URI_TV;
import static com.studio.karya.submission4.menu.activity.DetailActivity.REQUEST_UPDATE;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

    private ArrayList<Content> listContent = new ArrayList<>();
    private Activity activity;
    private String type;
    private Fragment fragment;
    private String fragOrigin;

    public ContentAdapter(Activity activity, String type, String fragOrigin) {
        this.activity = activity;
        this.type = type;
        this.fragOrigin = fragOrigin;
    }

    public ContentAdapter(Activity activity, Fragment fragment, String type, String fragOrigin) {
        this.activity = activity;
        this.type = type;
        this.fragment = fragment;
        this.fragOrigin = fragOrigin;
    }

    public void setListContent(ArrayList<Content> listContent) {

        if (listContent.size() > 0) {
            this.listContent = listContent;
        }
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        this.listContent.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listContent.size());
    }

    @NonNull
    @Override
    public ContentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.adapter_content, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentAdapter.ViewHolder holder, int position) {

        ConvertDate convertDate = new ConvertDate();

        if (type.equals("movie")) {
            holder.tvTitle.setText(listContent.get(position).getTitleFilm());
            holder.tvTitleDate.setText(activity.getString(R.string.release_date));
            holder.tvDate.setText(convertDate.date(listContent.get(position).getReleaseDate()));
        } else {
            holder.tvTitle.setText(listContent.get(position).getTitleTv());
            holder.tvTitleDate.setText(activity.getString(R.string.first_air_date));
            holder.tvDate.setText(convertDate.date(listContent.get(position).getFirstAirDateTv()));
        }
        holder.tvDesc.setText(listContent.get(position).getOverview());

        Picasso.get()
                .load(IMG_URL + listContent.get(position).getPosterPath())
                .fit()
                .into(holder.imgPoster);

        holder.itemView.setOnClickListener(new CustomOnItemClick(position, new CustomOnItemClick.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {

                Uri uri;
                if (type.equals("movie")){
                    uri = Uri.parse(CONTENT_URI_MOVIE + "/" + listContent.get(position).getId());
                } else {
                    uri = Uri.parse(CONTENT_URI_TV + "/" + listContent.get(position).getId());
                }

                Intent intent = new Intent(activity, DetailActivity.class);
                intent.setData(uri);
                intent.putExtra(DetailActivity.DATA, listContent.get(position));
                intent.putExtra(DetailActivity.TYPE, type);
                intent.putExtra(DetailActivity.EXTRA_POSITION, String.valueOf(position));
                intent.putExtra(DetailActivity.ORIGIN_FRAGMENT, fragOrigin);
                if (fragment == null) {
                    activity.startActivityForResult(intent, REQUEST_UPDATE);
                } else {
                    fragment.startActivityForResult(intent, REQUEST_UPDATE);
                }
            }
        }));
    }

    @Override
    public int getItemCount() {
        return listContent.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDesc, tvDate, tvTitleDate;
        ImageView imgPoster;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvDate = itemView.findViewById(R.id.tv_release_date);
            tvTitleDate = itemView.findViewById(R.id.tv_title_release_date);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }
    }
}
