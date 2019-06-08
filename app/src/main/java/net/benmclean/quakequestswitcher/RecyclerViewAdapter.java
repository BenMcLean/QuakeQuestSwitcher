package net.benmclean.quakequestswitcher;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    protected String[] strings;
    protected Context context;
    protected View view;
    protected ViewHolder viewHolder;
    protected OnItemListener onItemListener;

    public RecyclerViewAdapter(Context context, String[] strings, OnItemListener onItemListener) {
        this.strings = strings;
        this.context = context;
        this.onItemListener = onItemListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        public OnItemListener onItemListener;

        public ViewHolder(View v, OnItemListener onItemListener) {
            super(v);
            textView = (TextView) v.findViewById(R.id.filename_textview);
            this.onItemListener = onItemListener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.recyclerview_items, parent, false);
        viewHolder = new ViewHolder(view, onItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(strings[position]);
    }

    @Override
    public int getItemCount() {
        return strings.length;
    }

    public interface OnItemListener {
        void onItemClick(int position);
    }
}
