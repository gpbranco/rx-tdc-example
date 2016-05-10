package br.com.branco.example.rx.tdc.view;

import java.util.ArrayList;
import java.util.List;

import br.com.branco.example.rx.tdc.model.Lecture;

/**
 * Created by guilhermebranco on 5/4/16.
 */
public interface LectureListView {

    void renderItems(List<Lecture> lectures);
    void showLoading();
    void hideLoading();
    void showFilter(String filter);
    void showFilterSelectionWith(ArrayList<CharSequence> filters);
    void setAddFilterClickListener(AddFilterClickListener addFilterClickListener);
    void setSelectFilterListener(SelectFilterListener selectFilterListener);

    interface AddFilterClickListener{
        void onClick();
    }

    interface SelectFilterListener{
        void onSelectedFilter(String filter);
    }
}
