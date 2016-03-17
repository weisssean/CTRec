package com.rec.ct.rec_txt;

import android.app.ActionBar;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddRectifier.AddRectifierListener} interface
 * to handle interaction events.
 */
public class AddRectifier extends Fragment {

    private AddRectifierListener mListener;

    private View.OnClickListener saveRecListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (validate())
            {
            Rectifier rec = new Rectifier(et_name.getText().toString(),et_phone_num.getText().toString());
            if (mListener!=null)
                mListener.onSaveRec(rec);
            }
        }
    };

    private boolean validate() {
        return true;
    }

    private EditText et_name;
    private EditText et_phone_num;

    public AddRectifier() {
        // Required empty public constructor
    }

    public static Fragment newInstance(){
        return new AddRectifier();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_add_rectifier, container, false);

        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar!=null){
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        }


        v.findViewById(R.id.btn_rec).setOnClickListener(saveRecListener);
         et_name = (EditText) v.findViewById(R.id.et_name);
        et_phone_num = (EditText) v.findViewById(R.id.et_phone_num);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSaveRec(new Rectifier());
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddRectifierListener) {
            mListener = (AddRectifierListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AddRectifierListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface AddRectifierListener {
        // TODO: Update argument type and name
        void onSaveRec(Rectifier r);
    }
}
