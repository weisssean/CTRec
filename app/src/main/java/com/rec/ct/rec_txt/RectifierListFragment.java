package com.rec.ct.rec_txt;

 import android.content.Context;
import android.os.Bundle;
 import android.support.v4.app.ListFragment;
 import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.rec.ct.rec_txt.data.RectifierDataSource;

import java.util.List;

public class RectifierListFragment extends ListFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
      private RectifierDataSource mDatasource;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RectifierListFragment() {
    }
    public static RectifierListFragment newInstance() {
        RectifierListFragment fragment = new RectifierListFragment();
          return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatasource = new RectifierDataSource(getActivity());
        mDatasource.open();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rectifier_list, container, false);


        List<Rectifier> values = mDatasource.getAllRectifiers();

        ArrayAdapter<Rectifier> adapter = new ArrayAdapter<Rectifier>(getActivity(),
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
     }

}
