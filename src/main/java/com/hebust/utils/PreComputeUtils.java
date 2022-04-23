package com.hebust.utils;

import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * 计算百分比工具
 */
public class PreComputeUtils {

    /**
     * 计算百分比工具
     * @param rec_all 项目的总数量
     * @param rec_canTake 可以被接单的数量
     * @param rec_take 已被接单的数量
     * @param rec_achieve 已完成的数量
     * @return map
     */
    public static HashMap<String, String> compute(int rec_all, int rec_canTake, int rec_take, int rec_achieve){
        // 保留两位小数
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        HashMap<String, String> map = new HashMap<>();
        if (rec_all == 0){
            map.put("take", "0.00");
            map.put("achieve", "0.00");
            map.put("dont", "0.00");
        } else {
            if (rec_achieve == 0){
                map.put("achieve", "0.00");
            }
            else {
                double pre_achieve = (double) (rec_achieve * 100) / rec_all;
                String format = decimalFormat.format(pre_achieve);
                map.put("achieve", format);
            }

            if (rec_canTake == 0 || rec_take == 0){
                map.put("take", "0.00");
            }
            else {
                double pre_take = (double) (rec_take *100 ) / rec_canTake;
                String format = decimalFormat.format(pre_take);
                map.put("take", format);
            }

            double pre_dont = (double) ((rec_all - rec_take - rec_achieve)*100) / rec_all;
            String format = decimalFormat.format(pre_dont);
            map.put("dont", format);
        }
        return map;
    }
}
