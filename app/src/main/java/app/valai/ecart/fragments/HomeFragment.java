package app.valai.ecart.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import app.valai.ecart.R;
import app.valai.ecart.adapters.AllCategoriesAdapter;
import app.valai.ecart.adapters.HomeTopViewPagerAdapter;
import app.valai.ecart.adapters.NewProductsViewPagerAdapter;
import app.valai.ecart.interfaces.MainMvpView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author by Mohit Arora on 22/8/18.
 * @projectname ECartApp
 */
public class HomeFragment extends BaseFragment {
    public static final String TAG = HomeFragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    @BindView(R.id.viewPager)
    RollPagerView viewPager;

    @BindView(R.id.recycleViewAllCat)
    RecyclerView recycleViewAllCat;

    @BindView(R.id.viewPagerNewPro)
    RollPagerView viewPagerNewPro;

    @BindView(R.id.viewPagerPopPro)
    RollPagerView viewPagerPopPro;

    private MainMvpView mainMvpView;

    private Integer[] mImageResources = {
            R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3,
            R.drawable.banner4
    };

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
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
        setAllCategoriesRecycleView();
        setNewProductsViewPager();
        setPopularProductsViewPager();
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
    private void setAllCategoriesRecycleView() {
        final AllCategoriesAdapter mAdapter = new AllCategoriesAdapter(getContext());
        recycleViewAllCat.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
                LinearSmoothScroller smoothScroller = new LinearSmoothScroller(getContext()) {
                    private static final float SPEED = 4000f;// Change this value (default=25f)

                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        return SPEED / displayMetrics.densityDpi;
                    }
                };
                smoothScroller.setTargetPosition(position);
                startSmoothScroll(smoothScroller);
            }
        };

        recycleViewAllCat.setLayoutManager(layoutManager);
        recycleViewAllCat.setAdapter(mAdapter);
    }

    private void setNewProductsViewPager() {
        viewPagerNewPro.setPlayDelay(3800);
        viewPagerNewPro.setAnimationDurtion(800);
        viewPagerNewPro.setHintView(null);
        NewProductsViewPagerAdapter mAdapter = new NewProductsViewPagerAdapter(viewPagerNewPro, getContext());
        viewPagerNewPro.setAdapter(mAdapter);
    }

    private void setPopularProductsViewPager() {
        viewPagerPopPro.setPlayDelay(3800);
        viewPagerPopPro.setAnimationDurtion(800);
        viewPagerPopPro.setHintView(null);
        NewProductsViewPagerAdapter mAdapter = new NewProductsViewPagerAdapter(viewPagerPopPro, getContext());
        viewPagerPopPro.setAdapter(mAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}