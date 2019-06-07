package com.example.igclone.Comments.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.igclone.Comments.CommentsActivity;
import com.example.igclone.Comments.DataModel.ListItem;
import com.example.igclone.Comments.DataModel.MainItem;
import com.example.igclone.Comments.DataModel.RepliesContainerItem;
import com.example.igclone.Comments.ViewHolders.RepliesContainerVH;
import com.example.igclone.R;
import com.example.igclone.Comments.Util.CommentsUtil;
import com.example.igclone.Comments.ViewHolders.MainItemVH;

import java.util.*;

public class CommentsRecyclerAdapter extends RecyclerView.Adapter {

    private static List<String> dataset;
    private static TreeMap<String, ListItem> preDataset;
    private MainItemVH mainItemVH;
    private RepliesContainerVH repliesContainerVH;

    public CommentsRecyclerAdapter()
    {
//        System.out.println("Set Comments Recycler Data");
        preDataset = new TreeMap<>();
        dataset = new ArrayList<>();

    }

    public void updatePreDataset(ListItem item)
    {
        if (item.getType()== CommentsActivity.MAIN_COMMENT) {

            preDataset.put(String.valueOf(((MainItem)item).getMainCommentTimestamp()), item);
        }

        else {

            preDataset.put(((RepliesContainerItem)item).getRepliesContainerTimestamp(), item);
        }

        createNewDataset();
    }

    public void updatePreDataset(TreeMap<String, ListItem> items)
    {
//        System.out.println("----------------------------Printing Predataset Comments Recycler Adapter-----------------------------------------");
//        for(String key:items.keySet()) {
//            System.out.println(items.get(key).getType());
//            if (items.get(key).getType()==CommentsActivity.MAIN_COMMENT)
//                System.out.println(((MainItem)items.get(key)).getMainCommentTimestamp());
//        }
//        System.out.println("-----------------------------------------------------------------------------------------");

        if (!preDataset.isEmpty())
            preDataset.clear();

        for (String item:items.keySet())
        {

            if (items.get(item).getType()== CommentsActivity.MAIN_COMMENT) {

                preDataset.put(String.valueOf(((MainItem)items.get(item)).getMainCommentTimestamp()), items.get(item));
            }

            else {
                String repliesContainerTimestamp = ((RepliesContainerItem)items.get(item)).getRepliesContainerTimestamp();
                String mainTimestamp = repliesContainerTimestamp.substring(0, repliesContainerTimestamp.length()-1);

                if(preDataset.containsKey(mainTimestamp))
                    preDataset.put(((RepliesContainerItem)items.get(item)).getRepliesContainerTimestamp(), items.get(item));
            }
        }


        createNewDataset();
    }



    private void createNewDataset()
    {
        dataset = new ArrayList<>(preDataset.keySet());
//        System.out.println("----------------------------Printing Dataset Comments Recycler Adapter-----------------------------------------");
//        System.out.println(Arrays.asList(dataset));
//        System.out.println("-----------------------------------------------------------------------------------------");
        notifyDataSetChanged();
    }

    public ListItem getDatasetListItem(String timestamp)
    {
//        System.out.println(timestamp + " timestamp requested for ListItem in CommentsRecyclerAdapter");
        return preDataset.get(timestamp);
    }

    public ListItem getDatasetListItem(int position)
    {
        return preDataset.get(dataset.get(position));
    }

    public ArrayList<ListItem> getPreDataset()
    {
        ArrayList<ListItem> temp = new ArrayList<>();
        for(String key:preDataset.keySet())
            temp.add(preDataset.get(key));

        return temp;
    }

    public List<String> getDataset()
    {
        return dataset;
    }


    @Override
    public int getItemViewType(int position) {
        return preDataset.get(dataset.get(position)).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int type = getItemViewType(i);
        if(type == ListItem.TYPE_MAIN)
        {
//            System.out.println("Position "+i+" Setting type Main");
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_comment, viewGroup, false);
            return new MainItemVH(itemView);
        }
        if(type == ListItem.TYPE_REPLIES)
        {
//            System.out.println("Position "+i+" Setting type Reply");
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_replies, viewGroup, false);
            return new RepliesContainerVH(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        if (type == ListItem.TYPE_MAIN) {
            final MainItem mainItem = (MainItem) preDataset.get(dataset.get(i));
            System.out.println(mainItem.getMainData().getComment()+"Main Item Position"+i);
            mainItemVH = (MainItemVH) viewHolder;
            mainItemVH.bind(mainItem.getMainData());
        }
        if (type == ListItem.TYPE_REPLIES){
            RepliesContainerItem repliesContainerItem = (RepliesContainerItem) preDataset.get(dataset.get(i));
//            System.out.println(replyItem.getRepliesArrayData().get(0).getComment()+" REply Item");
            repliesContainerVH = (RepliesContainerVH) viewHolder;
            repliesContainerItem.setRepliesContainerVH(repliesContainerVH);
            repliesContainerVH.bind(repliesContainerItem.getReplyItems());

        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
