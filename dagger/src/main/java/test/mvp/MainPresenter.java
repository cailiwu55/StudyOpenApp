package test.mvp;

/**
 * Created by clw on 2017/8/26.
 */

public class MainPresenter {
    private MainContact.View mView;

    public MainPresenter(MainContact.View view){
        mView = view;
    }

    public void loadData(){
        mView.updateView();
    }
}
