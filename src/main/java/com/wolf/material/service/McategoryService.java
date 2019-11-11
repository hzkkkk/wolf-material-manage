package com.wolf.material.service;
/**
 * @Title: Mcategory
 * @Description: 物资种类管理业务层
 * @author 吴科男/吴志雄
 * @date 2019/11/08
 */
import com.wolf.material.pojo.Mcategory;

import java.util.List;


public interface McategoryService {
    List<Mcategory>findAll() throws Exception;

    List<Mcategory>findOne(Integer cid)throws Exception;
}
