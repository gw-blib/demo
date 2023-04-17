package com.mall.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.demo.exception.MyException;
import com.mall.demo.pojo.CancelInfo;
import com.mall.demo.pojo.OrderInfo;
import com.mall.demo.pojo.SuccessInfo;
import com.mall.demo.pojo.WalletInfo;
import com.mall.demo.service.*;
import com.mall.demo.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
* @author hw
* @description 针对表【order_info】的数据库操作Service实现
* @createDate 2023-04-17 19:07:07
*/
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo>
    implements OrderInfoService{
    @Autowired
    private WalletInfoService walletInfoService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private SuccessInfoService successInfoService;
    @Autowired
    private CancelInfoService cancelInfoService;
    //消费一百元
    @Transactional
    @Override
    public void buySomething(String orederSn) {
        //查询订单信息
        LambdaQueryWrapper<OrderInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderInfo::getOrderSn,orederSn);
        OrderInfo orderInfo = this.getOne(wrapper);
        //本次消费总额  100
        BigDecimal sum = orderInfo.getSum();
        //查询钱包信息
        Integer uid = orderInfo.getUid();
        WalletInfo walletInfo= walletInfoService.getWallteInfoByUid(uid);
        BigDecimal originBalance = walletInfo.getBalance();

        if((originBalance.compareTo(sum)<=0)){
            //钱包余额不足
            throw new MyException(20001,"余额不足");
        }
        //扣除余额
        BigDecimal newBalance = originBalance.subtract(sum);
        walletInfo.setBalance(newBalance);
        walletInfoService.update(walletInfo,null);

        //进行记录
        SuccessInfo successInfo = new SuccessInfo();
        successInfo.setSum(sum);
        successInfo.setUid(uid);
        successInfoService.save(successInfo);
    }
    @Transactional
    @Override
    public void removeBuy(String orederSn) {
        //查询订单信息
        LambdaQueryWrapper<OrderInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderInfo::getOrderSn,orederSn);
        OrderInfo orderInfo = this.getOne(wrapper);
        //本次消费总额
        BigDecimal sum = orderInfo.getSum();
        //查询钱包信息
        Integer uid = orderInfo.getUid();
        WalletInfo walletInfo= walletInfoService.getWallteInfoByUid(uid);
        BigDecimal originBalance = walletInfo.getBalance();  //扣除余额
        //增加金额
        BigDecimal newBalance = originBalance.add(sum);
        walletInfo.setBalance(newBalance);
        walletInfoService.update(walletInfo,null);

        //进行记录
        CancelInfo cancelInfo = new CancelInfo();
        cancelInfo.setUid(uid);
        cancelInfo.setSum(sum);
        System.out.println(cancelInfo);
        cancelInfoService.save(cancelInfo);
    }
}




