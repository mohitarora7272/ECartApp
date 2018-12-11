package app.valai.ecart.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import app.valai.ecart.R;
import app.valai.ecart.interfaces.MainMvpView;
import app.valai.ecart.utils.AnimationUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author by Mohit Arora on 2/9/18.
 * @projectname ECartApp
 */
public class AddressFragment extends BaseFragment {
    public static final String TAG = AddressFragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    @BindView(R.id.btnNextStep)
    Button btnNextStep;

    private MainMvpView mainMvpView;

    public AddressFragment() {
        // Required empty public constructor
    }

    public static AddressFragment newInstance(String param1, String param2) {
        AddressFragment fragment = new AddressFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_address, container, false);
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
        setAnimationsOnView();
    }

    //Apply Animations On Views
    private void setAnimationsOnView() {
        AnimationUtil animationUtil = new AnimationUtil(getContext());
        animationUtil.slideInUp(btnNextStep);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}