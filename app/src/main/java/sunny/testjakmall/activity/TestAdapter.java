package sunny.testjakmall.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sunny.testjakmall.R;
import sunny.testjakmall.network.model.Value;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private List<Value> mItems;
    private Context mContext;
    LayoutInflater mInflater;
    private Boolean cek = false;

    public TestAdapter(Context context, List<Value> post) {
        this.mContext = context;
        this.mItems = post;
        notifyDataSetChanged();

        mInflater = LayoutInflater.from(context);
    }

    public void updateAnswers(List<Value> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.cv)
        CardView cv;
        @BindView(R.id.textTest1)
        TextView tv;
        @BindView(R.id.textTest2)
        TextView tv2;
        @BindView(R.id.b_up)
        Button bUp;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            tv = itemView.findViewById(R.id.textTest1);
            tv2 = itemView.findViewById(R.id.textTest2);
            bUp = itemView.findViewById(R.id.b_up);
            ButterKnife.bind(this, itemView);
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list2,
                parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Value value = mItems.get(position);
        holder.tv.setText(value.getId().toString());
        holder.tv2.setText(value.getJoke());
        holder.bUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cek) {
                    int i = holder.getAdapterPosition();
                    Toast.makeText(mContext, String.valueOf(i), Toast.LENGTH_SHORT).show();
                    Collections.swap(mItems, 0, position);
                    notifyItemMoved(position, 0);
                    cek = true;
                }
            }

        });

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private void showAlertDialog(View v) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
        alertDialogBuilder.setMessage("Hello World!");
        alertDialogBuilder.setPositiveButton("Hello World!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }

}
