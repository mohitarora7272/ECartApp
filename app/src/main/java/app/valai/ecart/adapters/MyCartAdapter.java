package app.valai.ecart.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import app.valai.ecart.R;
import app.valai.ecart.interfaces.MainMvpView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author by Mohit Arora on 26/8/18.
 * @projectname ECartApp
 */
public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyViewHolder> {

    private Context context;
    private MainMvpView mainMvpView;

    public MyCartAdapter(Context context, MainMvpView mainMvpView) {
        super();
        this.context = context;
        this.mainMvpView = mainMvpView;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.btnPlus)
        Button btnPlus;

        @BindView(R.id.btnMinus)
        Button btnMinus;

        @BindView(R.id.edtRange)
        EditText edtRange;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.btnPlus)
        void onPlusClick(View view) {
            mainMvpView.hideKeyboard();
            if (edtRange.getText() != null && !edtRange.getText().toString().equals("")) {
                edtRange.setText(String.valueOf(Integer.parseInt(edtRange.getText().toString()) + 1));
            } else {
                edtRange.setText("1");
            }
        }

        @OnClick(R.id.btnMinus)
        void onMinusClick(View view) {
            mainMvpView.hideKeyboard();
            if(edtRange.getText() != null && edtRange.getText().toString().equals("1")){
                return;
            }
            if (edtRange.getText() != null && !edtRange.getText().toString().equals("")) {
                edtRange.setText(String.valueOf(Integer.parseInt(edtRange.getText().toString()) - 1));
            } else {
                edtRange.setText("1");
            }
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_my_cart_items, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.btnPlus.setTag(position);
        holder.btnMinus.setTag(position);
        holder.edtRange.setTag(position);
        holder.edtRange.setText("1");
        holder.edtRange.setCursorVisible(false);
        holder.edtRange.setSelection(holder.edtRange.getText().length());
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}