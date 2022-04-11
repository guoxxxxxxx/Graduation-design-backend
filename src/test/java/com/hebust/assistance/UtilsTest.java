package com.hebust.assistance;

import com.hebust.utils.DateUtils;
import org.junit.Test;

import java.sql.Time;
import java.util.Date;

public class UtilsTest {

    @Test
    public void currentTime(){
        Date dateFormat = DateUtils.getCurrentDate();
        Time timeFormat = DateUtils.getCurrentTime();
        System.out.println(timeFormat);
        System.out.println(dateFormat);
    }
}
