package app.valai.ecart.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import app.valai.ecart.R;
import app.valai.ecart.adapters.HomeTopViewPagerAdapter;
import app.valai.ecart.adapters.OffersAdapter;
import app.valai.ecart.interfaces.MainMvpView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author by Mohit Arora on 30/8/18.
 * @projectname ECartApp
 */
public class OffersFragment extends BaseFragment {
    public static final String TAG = OffersFragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    @BindView(R.id.viewPager)
    RollPagerView viewPager;

    @BindView(R.id.recycleViewOffers)
    RecyclerView recycleViewOffers;

    private MainMvpView mainMvpView;

    private Integer[] mImageResources = {
            R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3,
            R.drawable.banner4
    };

    public OffersFragment() {
        // Required empty public constructor
    }

    public static OffersFragment newInstance(String param1, String param2) {
        OffersFragment fragment = new OffersFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_offers, container, false);
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
        setHomeTopViewPager();
        setOffersRecycleView();
    }

    private void setHomeTopViewPager() {
        viewPager.setPlayDelay(3000);
        viewPager.setAnimationDurtion(800);
        viewPager.setHintPadding(0, 0, 0, 30);
        viewPager.setHintView(new ColorPointHintView(getContext(),
                Color.parseColor("#689f38"), Color.parseColor("#C8E6C9")));
        HomeTopViewPagerAdapter mAdapter = new HomeTopViewPagerAdapter(viewPager, mImageResources);
        viewPager.setAdapter(mAdapter);
    }

    // Set adapter on Recycle view
    private void setOffersRecycleView() {
        final OffersAdapter mAdapter = new OffersAdapter(getContext());
        recycleViewOffers.setHasFixedSize(true);
        recycleViewOffers.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recycleViewOffers.setAdapter(mAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}