package test.md;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public static MyFragment newInstance(String name) {
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        mRecyclerView = (RecyclerView) LayoutInflater.from(getContext()).inflate(R.layout.recyclerview, container, false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new Adapter());

        setHasOptionsMenu(true);

        return mRecyclerView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.b, menu);
    }

    class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_card_main, parent, false);
            registerForContextMenu(view);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final View view = holder.mView;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationZ", 20f, 0f);
                    animator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            //Snackbar.make(view, "position" + position + " clicked!", Snackbar.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(getContext(), DetailActivity.class);
                            getContext().startActivity(intent);
                        }
                    });
                    animator.start();
                }
            });
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
    }

}