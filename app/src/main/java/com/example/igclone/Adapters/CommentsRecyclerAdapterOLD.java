package com.example.igclone.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.igclone.Comments.DataModel.ListItem;
import com.example.igclone.Comments.DataModel.MainItem;
import com.example.igclone.Comments.DataModel.RepliesItem;
import com.example.igclone.R;
import com.example.igclone.Util.CommentsUtil;
import com.example.igclone.ViewHolders.MainItemVH;
import com.example.igclone.ViewHolders.RepliesItemVH;

import java.util.*;

public class CommentsRecyclerAdapterOLD extends RecyclerView.Adapter {

    private ArrayList<ListItem> dataset;
    //    private TreeMap<Long, ListItem> dataset;
    private MainItemVH mainItemVH;
    private RepliesItemVH repliesItemVH;

    public CommentsRecyclerAdapterOLD(ArrayList<ListItem> dataset)
    {
        System.out.println("Set Comments Recycler Data");
        this.dataset = dataset;
    }

//    public void updateDataset(long timestamp, ListItem item)
//    {
//      dataset.put(timestamp, item);
//      notifyDataSetChanged();
//    }

    public void updateDataset(ListItem item)
    {
        dataset.add(item);
        notifyDataSetChanged();
    }

//    public ListItem getDatasetListItem(long timestamp)
//    {
//        return dataset.get(timestamp);
//    }

    public ListItem getDatasetListItem(int position)
    {
        return dataset.get(position);
    }

    public ArrayList<ListItem> getDataset()
    {
        return dataset;
    }

    public void addItemAtPosition(int position, ListItem item)
    {
        dataset.add(position, item);
        notifyDataSetChanged();
    }

//    @Override
//    public int getItemViewType(int position) {
//        ArrayList<Long> keys = new ArrayList<>(dataset.keySet());
//
//        if(dataset.get(keys.get(position)).getType()== CommentsActivity.MAIN_COMMENT)
//            System.out.println("Returning Main Comment Type Position "+position);
//        else
//            System.out.println("Returning Reply Comment Type Position "+position);
//
//        return dataset.get(keys.get(position)).getType();
//    }


    @Override
    public int getItemViewType(int position) {
        return dataset.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int type = getItemViewType(i);
        if(type == ListItem.TYPE_MAIN)
        {
            System.out.println("Position "+i+" Setting type Main");
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_comment, viewGroup, false);
            return new MainItemVH(itemView);
        }
        if(type == ListItem.TYPE_REPLIES)
        {
            System.out.println("Position "+i+" Setting type Reply");
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_replies, viewGroup, false);
            return new RepliesItemVH(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        if (type == ListItem.TYPE_MAIN) {
//            ArrayList<Long> keys = new ArrayList<>(dataset.keySet());
//            final MainItem mainItem = (MainItem) dataset.get(keys.get(i));
            final MainItem mainItem = (MainItem) dataset.get(i);
            System.out.println(mainItem.getMainData().getComment()+"Main Item Position"+i);
            mainItemVH = (MainItemVH) viewHolder;
            mainItemVH.bind(mainItem.getMainCommentTimestamp(), mainItem.getMainItemPos(), mainItem.getMainData());
            mainItemVH.likeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mainItem.getMainData().isLiked())
                    {
                        CommentsUtil.likeBtnUnlikedInteractionAnimate(mainItemVH.likeBtn, mainItemVH.itemView.getContext());
                        mainItem.getMainData().decrementLikeCount();
                        mainItem.getMainData().setLiked(false);
                    }
                    else {
                        CommentsUtil.likeBtnLikedInteractionAnimate(mainItemVH.likeBtn, mainItemVH.itemView.getContext());
                        mainItem.getMainData().incrementLikeCount();
                        mainItem.getMainData().setLiked(true);
                    }

                    CommentsUtil.setMainData(mainItem.getMainData());
                    notifyItemChanged(dataset.indexOf(mainItem));
//                    int pos=0;
//                    for(Long key:dataset.keySet())
//                    {
//                        if (dataset.get(key)==mainItem) {
//                            notifyItemChanged(pos);
//                            break;
//                        }
//                        pos++;
//                    }

                }
            });
        }
        if (type == ListItem.TYPE_REPLIES){
//            ArrayList<Long> keys = new ArrayList<>(dataset.keySet());
//            RepliesItem repliesItem = (RepliesItem) dataset.get(keys.get(i));
            RepliesItem repliesItem = (RepliesItem) dataset.get(i);
            System.out.println(repliesItem.getRepliesArrayData().get(0).getComment()+" REply Item");
            repliesItemVH = (RepliesItemVH) viewHolder;
            repliesItem.setRepliesVH(repliesItemVH);
            repliesItemVH.bind(repliesItem.getMainCommentTimestamp(), repliesItem.getMainItemPos(), repliesItem.getRepliesArrayData());

        }
    }

    @Override
    public int getItemCount() {
//        return dataset.keySet().size();
        return dataset.size();
    }
}
