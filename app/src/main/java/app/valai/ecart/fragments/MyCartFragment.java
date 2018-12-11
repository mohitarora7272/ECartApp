package app.valai.ecart.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import app.valai.ecart.R;
import app.valai.ecart.activities.ProductDetailsActivity;
import app.valai.ecart.adapters.MyCartAdapter;
import app.valai.ecart.interfaces.MainMvpView;
import app.valai.ecart.utils.AnimationUtil;
import app.valai.ecart.utils.RecyclerItemClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static app.valai.ecart.utils.AppConstants.TAG_CHECK_OUT;

/**
 * @author by Mohit Arora on 24/8/18.
 * @projectname ECartApp
 */
public class MyCartFragment extends BaseFragment {
    public static final String TAG = MyCartFragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @BindView(R.id.tvTotal)
    TextView tvTotal;

    @BindView(R.id.btnCheckOut)
    Button btnCheckOut;

    private MainMvpView mainMvpView;

    public MyCartFragment() {
        // Required empty public constructor
    }

    public static MyCartFragment newInstance(String param1, String param2) {
        MyCartFragment fragment = new MyCartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_cart, container, false);
        setUnBinder(ButterKnife.bind(this, rootView));
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mainMvpView = (MainMvpView) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement MainMvpView ");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecycleView();
        setAnimationsOnView();
        //setRecycleTouchListener();
    }

    // Set adapter on Recycle view
    private void setRecycleView() {
        final MyCartAdapter mAdapter = new MyCartAdapter(getContext(), mainMvpView);
        recycleView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(mAdapter);
    }

    private void setRecycleTouchListener() {
        recycleView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recycleView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    //Apply Animations On Views
    private void setAnimationsOnView() {
        AnimationUtil animationUtil = new AnimationUtil(getContext());
        animationUtil.slideInUp(tvTotal);
        animationUtil.slideInUp(btnCheckOut);
    }

    @OnClick(R.id.btnCheckOut)
    void onCheckOutClick() {
        if (!getAppPreferenceHelper().isUserLogin()) {
            mainMvpView.openSignInActivity();
        } else {
            mainMvpView.openFragment(11, TAG_CHECK_OUT);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}