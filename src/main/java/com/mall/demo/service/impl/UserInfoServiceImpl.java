package com.mall.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.demo.pojo.UserInfo;
import com.mall.demo.pojo.WalletInfo;
import com.mall.demo.service.UserInfoService;
import com.mall.demo.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author hw
* @description 针对表【user_info】的数据库操作Service实现
* @createDate 2023-04-17 18:31:10
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

}




