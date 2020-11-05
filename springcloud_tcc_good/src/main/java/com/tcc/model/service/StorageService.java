package com.tcc.model.service;



import com.common.model.CommonResult;
import com.tcc.model.entity.Storage;
import com.tcc.model.mapper.StorageMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
@Slf4j
public class StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageService.class);

    @Resource
    private StorageMapper storageDao;

    /**
     * 扣减库存
     */
    public void decrease(Long productId, Integer count) {
        LOGGER.info("------->storage-service中扣减库存开始");
        storageDao.decrease(productId,count);
        LOGGER.info("------->storage-service中扣减库存结束");
    }

    public CommonResult findByGoodId(Integer id) {
        Storage good = storageDao.findById(id);
        return new CommonResult(0000,"ok",good);
    }
}
