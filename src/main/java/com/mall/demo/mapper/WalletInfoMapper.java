package com.mall.demo.mapper;

import com.mall.demo.pojo.WalletInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
* @author hw
* @description 针对表【wallet_info】的数据库操作Mapper
* @createDate 2023-04-17 18:31:12
* @Entity com.mall.demo.pojo.WalletInfo
*/
public interface WalletInfoMapper extends BaseMapper<WalletInfo> {

    BigDecimal getBalanceByUid(@Param("uid") Integer uid);

    WalletInfo getWallteInfoByUid( @Param("uid") Integer uid);
}




