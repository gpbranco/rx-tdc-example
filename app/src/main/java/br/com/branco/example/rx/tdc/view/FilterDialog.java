package br.com.branco.example.rx.tdc.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.branco.example.rx.tdc.R;

/**
 * Created by guilhermebranco on 5/8/16.
 */
public class FilterDialog extends DialogFragment implements AdapterView.OnItemClickListener{

    private ListView lvFilters;
    private ArrayList<CharSequence> filters = new ArrayList<>();
    private LectureListView.SelectFilterListener selectFilterListener;

    public void setSelectFilterListener(LectureListView.SelectFilterListener selectFilterListener) {
        this.selectFilterListener = selectFilterListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filters = getArguments().getCharSequenceArrayList(Constants.Extra.FILTERS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment, null, false);
        lvFilters = (ListView) view.findViewById(R.id.list);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, filters);

        lvFilters.setAdapter(adapter);

        lvFilters.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        dismiss();
        this.selectFilterListener.onSelectedFilter((String) filters.get(position));
    }
}
