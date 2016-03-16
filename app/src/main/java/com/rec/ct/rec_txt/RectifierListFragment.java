package com.rec.ct.rec_txt;

 import android.content.Context;
 import android.database.Cursor;
 import android.os.Bundle;
 import android.support.v4.app.ListFragment;
 import android.support.v4.widget.CursorAdapter;
 import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
 import android.widget.ListView;
 import android.widget.TextView;

 import com.rec.ct.rec_txt.data.P2SDBHelper;
 import com.rec.ct.rec_txt.data.RectifierDataSource;

import java.util.List;

public class RectifierListFragment extends ListFragment {
    private RectifierListListener mListener;

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

        TodoCursorAdapter adapter = new TodoCursorAdapter(getActivity(),mDatasource.getAllRectifiersCursor(),0);

      //  ArrayAdapter<Rectifier> adapter = new ArrayAdapter<Rectifier>(getActivity(),
      //          android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mListener.onSelectRec( mDatasource.cursorToRectifier((Cursor) l.getItemAtPosition(position)));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RectifierListListener) {
            mListener = (RectifierListListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AddRectifierListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
     }


    public class TodoCursorAdapter extends CursorAdapter {
        public TodoCursorAdapter(Context context, Cursor cursor, int flags) {
            super(context, cursor, 0);
        }

        // The newView method is used to inflate a new view and return it,
        // you don't bind any data to the view at this point.
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_activated_2, parent, false);
        }

        // The bindView method is used to bind all data to a given view
        // such as setting the text on a TextView.
        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            // Find fields to populate in inflated template
            TextView tvBody = (TextView) view.findViewById(android.R.id.text1);
            TextView tvPriority = (TextView) view.findViewById(android.R.id.text2);
            // Extract properties from cursor
            String body = cursor.getString(cursor.getColumnIndexOrThrow(P2SDBHelper.COLUMN_NAME));
            String priority = cursor.getString(cursor.getColumnIndexOrThrow(P2SDBHelper.COLUMN_PHONE));
            // Populate fields with extracted properties
            tvBody.setText(body);
            tvPriority.setText(priority);
        }
    }
    public interface RectifierListListener {
        // TODO: Update argument type and name
        void onSelectRec(Rectifier r);
    }
}
