package app.valai.ecart.adapters;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

/**
 * @author by Mohit Arora on 27/8/18.
 * @projectname ECartApp
 */
public class HomeTopViewPagerAdapter extends LoopPagerAdapter {

    private Integer[] mResources;

    public HomeTopViewPagerAdapter(RollPagerView rollPagerView, Integer[] mResources) {
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
        ImageView view = new ImageView(container.getContext());
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view.setImageResource(mResources[position]);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}