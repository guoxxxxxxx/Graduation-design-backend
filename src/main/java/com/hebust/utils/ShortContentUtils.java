package com.hebust.utils;

import com.hebust.entity.table.DiscussTable;
import com.hebust.entity.table.ReplyTable;

import java.util.List;

/**
 * 精简内容
 */
public class ShortContentUtils {

    /**
     * 将内容裁短
     * @param content 所要裁短的内容
     * @return 处理后的内容
     */
    public static String toShortContent(String content){
        if (content.length() > 10){
            return content.substring(0, 10) + "...";
        }
        else {
            return content;
        }
    }

    /**
     * 完善评论
     * @param discussTables
     * @return
     */
    public static List<DiscussTable> discussTableToShort(List<DiscussTable> discussTables){
        for (DiscussTable discussTable : discussTables) {
            if (discussTable.getContent().length() > 10) {
                discussTable.setShortContent(discussTable.getContent().substring(0, 10) + "...");
            } else {
                discussTable.setShortContent(discussTable.getContent());
            }
        }
        return discussTables;
    }

    /**
     * 完善回复信息
     */
    public static List<ReplyTable> replyTableToShort(List<ReplyTable> replyTable){
        for (ReplyTable table : replyTable) {
            if (table.getContent().length() > 10) {
                table.setShortContent(table.getContent().substring(0, 10) + "...");
            } else {
                table.setShortContent(table.getContent());
            }
        }
        return replyTable;
    }
}
