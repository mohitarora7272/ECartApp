package app.valai.ecart.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import app.valai.ecart.R;
import app.valai.ecart.activities.ProductDetailsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author by Mohit Arora on 27/8/18.
 * @projectname ECartApp
 */
public class WeeklySpecialsAdapter extends RecyclerView.Adapter<WeeklySpecialsAdapter.MyViewHolder> {

    private Context context;

    public WeeklySpecialsAdapter(Context context) {
        super();
        this.context = context;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.btnShopNow)
        Button btnShopNow;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.btnShopNow)
        void onShopNowClick(View view) {
            Intent intent = new Intent(context, ProductDetailsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.new_products_pager_item, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
