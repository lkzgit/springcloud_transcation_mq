package com.tcc.model.mapper;

import com.tcc.model.entity.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageMapper {

    //扣减库存
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);

    Storage findById(@Param("id") Integer id);
}
