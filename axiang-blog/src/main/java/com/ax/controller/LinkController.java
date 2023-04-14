package com.ax.controller;

import com.ax.domain.ResponseResult;
import com.ax.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * className: LinkController
 * description: 友链
 *
 * @author: axiang
 * date: 2023/4/9 15:45
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    /**
     * description: 查看所有审核通过的友链
     * @param :
     * @return: ResponseResult <br>
     * date: 2023/4/10 0010 <br>
     */
    @GetMapping("/getAllLink")
    public ResponseResult getAllLink() {
        return linkService.getAllLink();
    }
}
