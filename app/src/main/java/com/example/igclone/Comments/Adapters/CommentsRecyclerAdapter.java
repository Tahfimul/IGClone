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
import com.example.igclone.Comments.DataModel.ReplyItem;
import com.example.igclone.Comments.ViewHolders.*;
import com.example.igclone.R;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

public class CommentsRecyclerAdapter extends RecyclerView.Adapter<CommentItemVH> {

    private static List<String> dataset;
//    private static TreeMap<String, ListItem> preDataset;
    private static NavigableMap<String, ListItem> preDataset;
    private static boolean isItemSelected;
    private static int curr_selected_item_type;
    private static String curr_selected_item_timestamp;
    private static String curr_selected_item_container_timestamp;

    public CommentsRecyclerAdapter()
    {
//        System.out.println("Set Comments Recycler Data");
//        preDataset = new TreeMap<>();
        preDataset = new ConcurrentSkipListMap<>();
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

        System.out.println("preDataset KeySet CommentsRecyclerAdapter "+Arrays.asList(preDataset.keySet()).toString());
//        if (!preDataset.isEmpty()) {
//
//            for (String item:preDataset.keySet())
//            {
//                if (!items.containsKey(item))
//                    preDataset.remove(item);
//            }
//        }

//        removeOldEntries(items);

        if(isItemSelected)
            switch (curr_selected_item_type)
            {
                case CommentsActivity.MAIN_COMMENT:
                    if(items.containsKey(curr_selected_item_timestamp)) {
                        MainItem mainItem = (MainItem) items.get(curr_selected_item_timestamp);
                        mainItem.setSelected(true);
                    }
                    break;
                case CommentsActivity.REPLY_COMMENT:
                    if (items.containsKey(curr_selected_item_container_timestamp)) {
                        System.out.println("CURR_SELECTED_ITEM_TIMESTAMP In CommentsRecyclerAdapter"+ curr_selected_item_timestamp);
                        RepliesContainerItem repliesContainerItem = (RepliesContainerItem) items.get(curr_selected_item_container_timestamp);
                        ReplyItem replyItem = repliesContainerItem.getReplyItem(curr_selected_item_timestamp);
                        replyItem.setIsSelected(true);
                    }
                    break;
            }

        if (!preDataset.isEmpty())
            preDataset.clear();

        for (String item:items.keySet())
        {

            if (items.get(item).getType()== CommentsActivity.MAIN_COMMENT) {
                MainItem mainItem = (MainItem)items.get(item);

                preDataset.put(String.valueOf(mainItem.getMainCommentTimestamp()), mainItem);
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

//    private void removeOldEntries(TreeMap<String, ListItem> items) {
//        for (String item:preDataset.keySet())
//        {
//            if (!items.containsKey(item)) {
//                System.out.println("removing preDataset old data in CommentsRecyclerAdapter");
//                preDataset.remove(item);
//            }
//        }
//    }

    public void setMainItemSelected(String itemTimestamp)
    {
        isItemSelected = true;
        curr_selected_item_type = CommentsActivity.MAIN_COMMENT;
        curr_selected_item_timestamp = itemTimestamp;

        MainItem mainItem = (MainItem) preDataset.get(itemTimestamp);
        mainItem.setSelected(true);

        notifyDataSetChanged();
    }

    public void setReplyItemSelected(String itemContainerTimestamp, String itemTimestamp)
    {
        isItemSelected = true;
        curr_selected_item_type = CommentsActivity.REPLY_COMMENT;
        curr_selected_item_container_timestamp = itemContainerTimestamp;
        curr_selected_item_timestamp = itemTimestamp;

        RepliesContainerItem repliesContainerItem = (RepliesContainerItem) preDataset.get(itemContainerTimestamp);
        ReplyItem replyItem =  repliesContainerItem.getReplyItem(itemTimestamp);

        replyItem.setIsSelected(true);

        notifyDataSetChanged();
    }

    public void setMainItemUnselected()
    {
        isItemSelected = false;
        MainItem mainItem = (MainItem) preDataset.get(curr_selected_item_timestamp);
        mainItem.setSelected(false);

        notifyDataSetChanged();
    }

    public void setReplyItemUnselected()
    {
        isItemSelected = false;
        RepliesContainerItem repliesContainerItem = (RepliesContainerItem) preDataset.get(curr_selected_item_container_timestamp);
        ReplyItem replyItem =  repliesContainerItem.getReplyItem(curr_selected_item_timestamp);

        replyItem.setIsSelected(true);

        notifyDataSetChanged();
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
        System.out.println("Type of Item "+preDataset.get(dataset.get(position)).getType()+"  "+dataset.get(position));
        return preDataset.get(dataset.get(position)).getType();
    }

    @NonNull
    @Override
    public CommentItemVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int type = getItemViewType(i);
        System.out.println(dataset.get(i)+" type in OnCreateViewHolder "+type+" pos "+i+Arrays.asList(dataset.subList(i, dataset.size()-1)).toString());

        if(type == ListItem.TYPE_MAIN)
        {
            System.out.println("Timestamp "+dataset.get(i)+" Setting type Main");
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_comment, viewGroup, false);
            return new MainItemVH(itemView);
        }
        if(type == ListItem.TYPE_REPLIES)
        {
            System.out.println("Timestamp "+dataset.get(i)+" Setting type Reply");
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_replies, viewGroup, false);
            return new RepliesContainerVH(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentItemVH viewHolder, int i) {
        int type = getItemViewType(i);

        System.out.println("obBindViewHolder called in CommentsRecyclerAdapter "+i+ " type "+type+" viewHolder type "+viewHolder.getItemViewType()+" timestamp "+dataset.get(i));
        if (type == ListItem.TYPE_MAIN) {
            MainItem mainItem = (MainItem) preDataset.get(dataset.get(i));
            viewHolder.bindMainItem(mainItem);
        }
        if (type == ListItem.TYPE_REPLIES){
            RepliesContainerItem repliesContainerItem = (RepliesContainerItem) preDataset.get(dataset.get(i));

            System.out.println("Binding ReplyContainerItem in CommentsRecyclerAdapter"+" viewHolderType "+viewHolder.getItemViewType()+" timestamp "+repliesContainerItem.getRepliesContainerTimestamp());
            viewHolder.bindReplyContainerItem(repliesContainerItem);
        }
    }

    @Override
    public int getItemCount() {
        System.out.println(dataset.size()+"datasetSize ");
        return dataset.size();
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        isItemSelected = false;
    }
}
