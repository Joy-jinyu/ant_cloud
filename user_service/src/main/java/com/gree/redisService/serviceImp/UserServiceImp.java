package com.gree.redisService.serviceImp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gree.entity.po.UserPO;
import com.gree.mapper.UserMapper;
import com.gree.model.MyPage;
import com.gree.redisService.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImp extends ServiceImpl<UserMapper, UserPO> implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    @Cacheable(cacheNames = {"user"},key = "#p0")
    public UserPO fetchByDSPW(String usid, String pawd) {
        return userMapper.fetchByDSPW(usid,pawd);
    }

    @Override
    @Cacheable(cacheNames = {"user"},key = "#p0") //用户数组查询缓存
    public UserPO fetchByUSID(String usid) {
        return userMapper.fetchByUSID(usid);
    }

    @Override
    @CachePut(cacheNames = {"user"},key = "#userPO.usid") //单个对象添加缓存
    public void updateByUsid(UserPO userPO) {
        userMapper.updateByUsid(userPO);
    }

    @Override
    @CacheEvict(cacheNames = {"user"},key = "#p0") //删除缓存 allEntries=true 删除所有的缓存
//    @Caching   //@Cacheable,@CacheaPut,@CacheaEvict都可以使用
//    @CacheaPut 存放数据
    public Integer deleteByusid(String usid) {
        return userMapper.deleteById(usid);
    }

    @Override
    @CacheEvict(cacheNames = {"user"},key = "#pager.selectInt")
    public MyPage<UserPO> queryAllByPage(MyPage<UserPO> pager) {
        return userMapper.queryAllByPage(pager);
    }
}
