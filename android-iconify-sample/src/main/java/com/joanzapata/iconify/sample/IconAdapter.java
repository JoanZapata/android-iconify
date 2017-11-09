package com.joanzapata.iconify.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joanzapata.iconify.Icon;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.ViewHolder> {

    private List<Icon> icons;

    public void setIcons(List<Icon> icons) {
        this.icons = icons;
    }

    public void reset() {
        if (icons != null) {
            icons.clear();
        }
    }

    public IconAdapter() {
    }

    public IconAdapter(Icon[] icons) {
        this.icons = Arrays.asList(icons);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_icon, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String iconName = (String) v.getTag();
                SimilarIconsActivity.launch(v.getContext(), iconName);
            }
        });

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Icon icon = icons.get(position);
        viewHolder.icon.setText("{" + icon.key() + "}");
        viewHolder.name.setText(icon.key());

        viewHolder.itemView.setTag(icon.key());
    }

    @Override
    public int getItemCount() {
        if (icons != null) {
            return icons.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.icon)
        TextView icon;
        @Bind(R.id.name)
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
