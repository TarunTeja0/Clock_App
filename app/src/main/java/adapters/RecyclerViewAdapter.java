package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clockapp.R;

import java.util.ArrayList;

import ModelClass.Time;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<Time> times;
    public RecyclerViewAdapter(Context context, ArrayList<Time> times){
        this.context = context;
        this.times =times;
    }
    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_layout, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(times.get(position).getHour() + ":" + times.get(position).getMin());
    }

    @Override
    public int getItemCount() {
        return times.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyViewHolder(View view){
            super(view);
            textView = view.findViewById(R.id.textViewRow);
        }
    }

}
