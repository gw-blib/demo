package com.mall.demo.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class WalletRecord {
    private List<SuccessInfo> successInfoList=new ArrayList<>();
    private List<CancelInfo> cancelInfos=new ArrayList<>();
}
