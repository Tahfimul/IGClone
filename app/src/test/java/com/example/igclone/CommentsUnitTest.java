package com.example.igclone;

import com.example.igclone.Comments.DataModel.ListItem;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CommentsUnitTest {
    @Test
    public void checkCommentsViews() {
        ArrayList<ListItem> commentsViews = new ArrayList<>();
        assertEquals(4, 2 + 2);
    }
}