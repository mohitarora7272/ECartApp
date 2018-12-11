package app.valai.ecart.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import app.valai.ecart.R;

/**
 * @author by Mohit Arora on 27/8/18.
 * @projectname ECartApp
 */
public class IntroViewPagerAdapter extends LoopPagerAdapter {

    private Integer[] mResources;

    public IntroViewPagerAdapter(RollPagerView rollPagerView, Integer[] mResources) {
        super(rollPagerView);
        this.mResources = mResources;
    }

    @Override
    public int getRealCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (object);
    }

    @Override
    public View getView(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(container.getContext()).inflate(R.layout.pager_item, container, false);

        ImageView imageView = itemView.findViewById(R.id.img_pager_item);
        imageView.setImageResource(mResources[position]);

        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}