package t.n.b.v.c.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Music> mList;
    private Context mContext;
    public MusicAdapter(Context context,List<Music> mList) {
        this.mList = mList;
        mContext=context;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mPosition;
        TextView mName;
        TextView mSinger;
        TextView mDuration;
        public MyViewHolder(View itemView) {
            super(itemView);
            mPosition=(TextView)itemView.findViewById(R.id.item_music_num_tv);
            mName=(TextView)itemView.findViewById(R.id.item_music_center_name);
            mSinger=(TextView)itemView.findViewById(R.id.item_music_center_author);
            mDuration=(TextView)itemView.findViewById(R.id.item_music_time);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_music, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Music music=mList.get(position);
        ((MyViewHolder)holder).mName.setText(music.getName());
        ((MyViewHolder)holder).mSinger.setText(music.getSinger());
        ((MyViewHolder)holder).mPosition.setText(String.valueOf(position+1));
        ((MyViewHolder)holder).mDuration.setText(Scan.formatTime(music.duration));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
