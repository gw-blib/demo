package com.mall.demo.controller;

import com.mall.demo.pojo.WalletRecord;
import com.mall.demo.service.OrderInfoService;
import com.mall.demo.service.WalletInfoService;
import com.mall.demo.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    private WalletInfoService walletInfoService;
    @Autowired
    private OrderInfoService orderInfoService;
    /**
     * 根据用户id查询用户钱包余额
     * @param uid
     * @return
     */
    @GetMapping("/getWalletBalance/{uid}")
    public R getWalletBalance(@PathVariable("uid") Integer uid){
       BigDecimal balance= walletInfoService.getBalanceByUid(uid);
       return R.ok().data("balance",balance);
    }

    /**
     *  根据订单号完成交易
     * @param orederSn
     * @return
     */
    @PostMapping("/buy/{orederSn}")
    public R buyProduct(@PathVariable("orederSn") String orederSn){
        orderInfoService.buySomething(orederSn);
        return R.ok();
    }

    /**
     * 根据订单号取消交易 完成退款
     * @param orederSn
     * @return
     */
    @PostMapping("/cancel/{orederSn}")
    public R cancelBuy(@PathVariable("orederSn") String orederSn){
        orderInfoService.removeBuy(orederSn);
        return R.ok();
    }

    @GetMapping("getWalletRecords/{uid}")
    public R getWalletRecords(@PathVariable("uid") Integer uid){
       WalletRecord record = walletInfoService.getWalletRecords(uid);
        return R.ok().data("record",record);
    }
}
