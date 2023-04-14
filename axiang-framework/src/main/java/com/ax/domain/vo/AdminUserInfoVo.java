package com.ax.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * className: AdminUserInfoVo
 * description:
 *
 * @author: axiang
 * date: 2023/4/11 0011 19:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AdminUserInfoVo {

    private List<String> permissions;

    private List<String> roles;

    private UserInfoVo user;
}
