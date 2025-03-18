package com.cyl.manager.ums.domain.form;

import lombok.Data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class UpdateMemberCartForm {
    @NotNull(message = "id 不能为空")
    private Long id;

    @NotNull(message = "数量必填")
    @Min(value = 0, message = "数量不小于0")
    @Max(value = Integer.MAX_VALUE, message = "数量不大于" + Integer.MAX_VALUE)
    private Integer num;
}
