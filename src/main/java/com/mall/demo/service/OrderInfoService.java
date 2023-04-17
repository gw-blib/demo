package com.mall.demo.service;

import com.mall.demo.pojo.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hw
* @description 针对表【order_info】的数据库操作Service
* @createDate 2023-04-17 19:07:07
*/
public interface OrderInfoService extends IService<OrderInfo> {

    void buySomething(String orederSn);

    void removeBuy(String orederSn);
}
