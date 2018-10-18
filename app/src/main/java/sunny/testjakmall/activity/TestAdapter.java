package sunny.testjakmall.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import sunny.testjakmall.R;
import sunny.testjakmall.network.model.Value;


public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private List<Value> mItems;
    private Context mContext;
    private MainContract.mainView mView;
    private int itemCount = 0;

    public TestAdapter(Context context, List<Value> post) {
        this.mContext = context;
        this.mItems = post;
        mItems = post;
//        mContext = context;
    }

    public void updateAnswers(List<Value> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleTv1;
        TextView titleTv2;
        Button bUp;
        CardView cv;
        LinearLayout linear1;

        public ViewHolder(View itemView) {
            super(itemView);
            linear1 = itemView.findViewById(R.id.L1);
            cv = itemView.findViewById(R.id.cv);
            titleTv1 = itemView.findViewById(R.id.textTest1);
            titleTv2 = itemView.findViewById(R.id.textTest2);
            bUp = itemView.findViewById(R.id.b_up);

//            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    private Value getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }

    @NonNull
    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context ctx = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View postView = inflater.inflate(R.layout.simple_list2, parent, false);
        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final TestAdapter.ViewHolder holder, final int position) {
        Value value = mItems.get(position);
        final MainActivity ma = new MainActivity();
        TextView tv1 = holder.titleTv1;
        TextView tv2 = holder.titleTv2;
        final Button bUp = holder.bUp;
        final CardView cv = holder.cv;

        tv1.setText(value.getId().toString());
        tv2.setText(value.getJoke());

        bUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "clicked", Toast.LENGTH_SHORT).show();
                ma.scrollToTop(position);
            }

        });

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "clicked 2", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

}
