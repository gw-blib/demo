package com.mall.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.demo.pojo.CancelInfo;
import com.mall.demo.pojo.SuccessInfo;
import com.mall.demo.pojo.WalletInfo;
import com.mall.demo.pojo.WalletRecord;
import com.mall.demo.service.CancelInfoService;
import com.mall.demo.service.SuccessInfoService;
import com.mall.demo.service.WalletInfoService;
import com.mall.demo.mapper.WalletInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
* @author hw
* @description 针对表【wallet_info】的数据库操作Service实现
* @createDate 2023-04-17 18:31:12
*/
@Service
public class WalletInfoServiceImpl extends ServiceImpl<WalletInfoMapper, WalletInfo>
    implements WalletInfoService{
    @Autowired
    private SuccessInfoService successInfoService;
    @Autowired
    private CancelInfoService cancelInfoService;
    @Override
    public BigDecimal getBalanceByUid(Integer uid) {
       return baseMapper.getBalanceByUid(uid);
    }

    @Override
    public WalletInfo getWallteInfoByUid(Integer uid) {

        return baseMapper.getWallteInfoByUid(uid);
    }

    @Override
    public WalletRecord getWalletRecords(Integer uid) {
        //消费记录
        LambdaQueryWrapper<SuccessInfo> swrapper = new LambdaQueryWrapper<>();
        swrapper.eq(SuccessInfo::getUid,uid);
        List<SuccessInfo> successInfos = successInfoService.list(swrapper);
        System.out.println(successInfos);
        //退款记录
        LambdaQueryWrapper<CancelInfo> cwrapper = new LambdaQueryWrapper<>();
        cwrapper.eq(CancelInfo::getUid,uid);
        List<CancelInfo> cancelInfos = cancelInfoService.list(cwrapper);

        WalletRecord walletRecord = new WalletRecord();
        walletRecord.setCancelInfos(cancelInfos);
        walletRecord.setSuccessInfoList(successInfos);
        return walletRecord;
    }
}




