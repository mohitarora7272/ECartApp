package app.valai.ecart.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.valai.ecart.R;
import app.valai.ecart.activities.ProductDetailsActivity;
import app.valai.ecart.adapters.FragmentGridAdapter;
import app.valai.ecart.interfaces.MainMvpView;
import app.valai.ecart.utils.RecyclerItemClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author by Mohit Arora on 23/8/18.
 * @projectname ECartApp
 */
public class FragmentGrid extends BaseFragment {
    public static final String TAG = FragmentGrid.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    private MainMvpView mainMvpView;

    public FragmentGrid() {
        // Required empty public constructor
    }

    public static FragmentGrid newInstance(String param1, String param2) {
        FragmentGrid fragment = new FragmentGrid();
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
        View rootView = inflater.inflate(R.layout.fragment_grid, container, false);
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
        //setRecycleTouchListener();
    }

    // Set adapter on Recycle view
    private void setRecycleView() {
        final FragmentGridAdapter mAdapter = new FragmentGridAdapter(getContext(), mainMvpView);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new GridLayoutManager(getContext(), 2));
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

    @Override
    public void onDetach() {
        super.onDetach();
    }
}