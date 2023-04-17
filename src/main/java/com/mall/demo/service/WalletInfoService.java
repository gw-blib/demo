package com.mall.demo.service;

import com.mall.demo.pojo.WalletInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.demo.pojo.WalletRecord;

import java.math.BigDecimal;
import java.util.List;

/**
* @author hw
* @description 针对表【wallet_info】的数据库操作Service
* @createDate 2023-04-17 18:31:12
*/
public interface WalletInfoService extends IService<WalletInfo> {

    BigDecimal getBalanceByUid(Integer uid);

    WalletInfo getWallteInfoByUid(Integer uid);

    WalletRecord getWalletRecords(Integer uid);
}
