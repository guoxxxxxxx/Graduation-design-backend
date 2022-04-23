package com.hebust.entity.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询百分比 形式为66.45这种形式
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Percentage {
    /**
     * 未被接单所占百分比
     */
    private String dont;

    /**
     * 已被接单所占百分比
     */
    private String take;

    /**
     * 已完成所占百分比
     */
    private String achieve;
}
