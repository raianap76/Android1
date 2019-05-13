package br.com.raiana.raianapereirafbexample.appparte2accelerometer;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link leafContent.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link leafContent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class leafContent extends Fragment {
    private static final String nodeID = "idNode";
    private static final String nodeContent = "content";

    private String mNodeID;
    private String mNodeContent;

    private OnFragmentInteractionListener mListener;

    private TextView txtID;
    private TextView txtContent;
    private Button btnBack;

    public leafContent() {
        // Required empty public constructor
    }
    public static leafContent newInstance(String id, String content) {
        leafContent fragment = new leafContent();
        Bundle args = new Bundle();
        args.putString(nodeID, id);
        args.putString(nodeContent, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNodeID = getArguments().getString(nodeID);
            mNodeContent = getArguments().getString(nodeContent);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fView = inflater.inflate(R.layout.activity_leaf_content, container, false);
        txtID = fView.findViewById(R.id.txtNodeID);
        txtContent = fView.findViewById(R.id.txtNodeContent);
        btnBack = fView.findViewById(R.id.btnBack);

        txtID.setText(mNodeID);
        txtContent.setText(mNodeContent);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarMain();
            }
        });

        return fView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void voltarMain() {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }
}