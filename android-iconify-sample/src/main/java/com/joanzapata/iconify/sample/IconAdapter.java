package com.joanzapata.iconify.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.joanzapata.iconify.Icon;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.ViewHolder> {

    private final Icon[] icons;

    public IconAdapter(Icon[] icons) {
        this.icons = icons;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_icon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Icon icon = icons[position];
        viewHolder.icon.setText("{" + icon.key() + "}");
        viewHolder.name.setText(icon.key());
    }

    @Override
    public int getItemCount() {
        return icons.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.icon) TextView icon;
        @Bind(R.id.name) TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
