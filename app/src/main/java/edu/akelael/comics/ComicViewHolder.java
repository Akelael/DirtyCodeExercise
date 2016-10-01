package edu.akelael.comics;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.akelael.comics.Marvel.Data.Comic;

class ComicViewHolder extends RecyclerView.ViewHolder {
    ImageView thumbnail;
    TextView title;
    Comic item;

    ComicViewHolder(View itemView) {
        super(itemView);
        this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        this.title = (TextView) itemView.findViewById(R.id.title);
    }
}
