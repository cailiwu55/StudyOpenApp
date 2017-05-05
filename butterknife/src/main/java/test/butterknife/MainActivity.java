package test.butterknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * a ButterKnife demo
 * @see {https://github.com/JakeWharton/butterknife}
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tip)
    TextView mTextView;
    @BindView(R.id.btn)
    Button mButton;
    @BindView(R.id.grid_view)
    GridView mGridView;

    private String[][] list = new String[][]{new String[]{"a", "sa"}, new String[]{"b", "sb"}, new String[]{"c", "sc"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mGridView.setAdapter(new MyGridViewAdapter());
    }

    @OnClick(R.id.btn)
    public void submit() {
        Toast.makeText(this, "btn is clicked,", Toast.LENGTH_SHORT).show();
    }

    public class MyGridViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.length;
        }

        @Override
        public Object getItem(int position) {
            return list[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.grid_view_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //set data
            String[] data = (String[]) getItem(position);
            viewHolder.mTitleView.setText(data[0]);
            viewHolder.mSubTitleView.setText(data[1]);

            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.title)
            TextView mTitleView;
            @BindView(R.id.sub_title)
            TextView mSubTitleView;

            public ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
