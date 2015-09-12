package com.joanzapata.iconify.sample;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.sample.utils.AndroidUtils;

public class FontIconsViewPagerAdapter extends PagerAdapter {

    public interface FontWithTitle {
        IconFontDescriptor getFont();

        String getTitle();
    }

    private final FontWithTitle[] fonts;

    public FontIconsViewPagerAdapter(FontWithTitle[] fonts) {
        this.fonts = fonts;
    }

    @Override
    public int getCount() {
        return fonts.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Context context = container.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_font, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        int nbColumns = AndroidUtils.getScreenSize((Activity) context).width /
                context.getResources().getDimensionPixelSize(R.dimen.item_width);
        recyclerView.setLayoutManager(new GridLayoutManager(context, nbColumns));
        recyclerView.setAdapter(new IconAdapter(fonts[position].getFont().characters()));
        container.addView(view);
        return view;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fonts[position].getTitle();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
