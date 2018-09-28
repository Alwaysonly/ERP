package com.huige.erp.common.base;

/**
 * @Author Z.xichao
 * @Create 2018-8-23
 * @Comments
 */
public class BaseServiceImpl<M extends IBaseMapper<T>, T> extends com.baomidou.mybatisplus.service.impl.ServiceImpl<M, T> implements IBaseService<T> {
}
