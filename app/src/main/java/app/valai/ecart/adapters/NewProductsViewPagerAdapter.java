package app.valai.ecart.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import app.valai.ecart.R;
import app.valai.ecart.activities.ProductDetailsActivity;
import butterknife.ButterKnife;

/**
 * @author by Mohit Arora on 23/08/18.
 * @projectname ECartApp
 */
public class NewProductsViewPagerAdapter extends LoopPagerAdapter {
    private Context ctx;

    public NewProductsViewPagerAdapter(RollPagerView rollPagerView, Context ctx) {
        super(rollPagerView);
        this.ctx = ctx;
    }

    @Override
    public int getRealCount() {
        return 10;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (object);
    }

    @Override
    public View getView(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(container.getContext()).inflate(R.layout.new_products_pager_item, container, false);
        Button btn = itemView.findViewById(R.id.btnShopNow);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, ProductDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                ctx.startActivity(intent);
            }
        });
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}