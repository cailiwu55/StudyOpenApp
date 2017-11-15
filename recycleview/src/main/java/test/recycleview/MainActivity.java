package test.recycleview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
  private RecyclerView mRecyclerView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    RecyclerView.LayoutManager layoutManager = null;

    List<String> list = initData();
    mRecyclerView.setLayoutManager(
        new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
    mRecyclerView.setAdapter(new RecyclerAdapter(this, list));
    mRecyclerView.addItemDecoration(
        new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    //mRecyclerView.setItemAnimator(animator);
  }

  private List<String> initData() {
    List<String> list = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      list.add("item" + i);
    }
    return list;
  }

  class RecyclerAdapter extends RecyclerView.Adapter<ViewHolderA> {
    private Context mContext;
    private List<String> mList;

    private List<Integer> mHeight;

    public RecyclerAdapter(Context context, List<String> list) {
      mContext = context;
      mList = list;
      mHeight = new ArrayList<Integer>();
      for (int i = 0; i < mList.size(); i++) {
        mHeight.add((int) (80 + Math.random() * 300));
      }
    }

    @Override public ViewHolderA onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, parent, false);
      ViewHolderA viewHolderA = new ViewHolderA(view);
      return viewHolderA;
    }

    @Override public void onBindViewHolder(ViewHolderA holder, final int position) {
      TextView textView = holder.mTextView;
      textView.setText(mList.get(position));
      ViewGroup.LayoutParams lp = textView.getLayoutParams();
      lp.height = mHeight.get(position);

      textView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          Toast.makeText(mContext, "item"+position+" clicked", Toast.LENGTH_SHORT).show();
        }
      });
      textView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override public boolean onLongClick(View v) {
          Toast.makeText(mContext, "item"+position+" long clicked", Toast.LENGTH_SHORT).show();
          return false;
        }
      });

    }

    @Override public int getItemCount() {
      return mList.size();
    }
  }

  class ViewHolderA extends RecyclerView.ViewHolder {
    public TextView mTextView;

    public ViewHolderA(View itemView) {
      super(itemView);
      mTextView = (TextView) itemView.findViewById(R.id.textview);
    }
  }
}
