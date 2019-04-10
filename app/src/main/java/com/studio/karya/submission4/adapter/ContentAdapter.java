package com.studio.karya.submission4.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.studio.karya.submission4.R;
import com.studio.karya.submission4.model.Content;
import com.studio.karya.submission4.utils.ConvertDate;
import com.studio.karya.submission4.utils.CustomOnItemClick;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.studio.karya.submission4.BuildConfig.IMG_URL;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

    private ArrayList<Content> listContent = new ArrayList<>();
    private Activity activity;
    private String TYPE;

    public ContentAdapter(Activity activity, String TYPE) {
        this.activity = activity;
        this.TYPE = TYPE;
    }

    public ArrayList<Content> getListContent() {
        return listContent;
    }

    public void setListContent(ArrayList<Content> listContent) {
        if (listContent.size() > 0) {
            this.listContent = listContent;
        }
        this.listContent.addAll(listContent);
        notifyDataSetChanged();
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

        if (TYPE.equals("movie")) {
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
