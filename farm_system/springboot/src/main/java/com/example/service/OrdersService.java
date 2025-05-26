package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.example.entity.Qa;
import com.example.entity.Orders;
import com.example.entity.Returns;
import com.example.mapper.OrdersMapper;
import com.example.mapper.ReturnsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务处理
 **/
@Service
public class OrdersService {

    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private QaService qaService;
    @Resource
    private ReturnsMapper returnsMapper;

    /**
     * 新增
     */
    @Transactional
    public void add(Orders orders) {
        orders.setTime(DateUtil.now());

        orders.setOrderNo(IdUtil.fastSimpleUUID());  // 唯一的订单编号
        // 扣减库存
        Qa qa = qaService.selectById(orders.getGoodsId());
        /*if (goods == null) {
            throw new CustomException("商品不存在");
        }
        int store = goods.getStore() - orders.getNum();
        if (store < 0) {
            throw new CustomException("商品库存不足");
        }
        goods.setStore(store);
        goodsService.updateById(goods);
        ordersMapper.insert(orders);*/
    }

    /**
     * 删除
     */
    public void deleteById(Integer id,String receiveName) {

        Orders order=ordersMapper.selectById(id);
        Returns returns=new Returns();
        returns.setOrderId(IdUtil.fastSimpleUUID());
        returns.setTotalPrice(order.getPrice());
        returns.setGoodId(order.getGoodsId());
        returns.setNum(order.getNum());
        returns.setReturnName(receiveName);
        returns.setReturnDate(DateUtil.now());
        ordersMapper.deleteById(id);
        returnsMapper.insert(returns);

    }

    /**
     * 修改状态
     */
    public void updateById(Orders orders) {
   /*     if ("已取消".equals(orders.getStatus())) { //用户取消订单  要返还库存
            Integer goodsId = orders.getGoodsId();
            Goods goods = goodsService.selectById(goodsId);
            if (goods != null) {
                goods.setStore(goods.getStore() + orders.getNum());
                goodsService.updateById(goods);
            }
        }*/
        ordersMapper.updateById(orders);
    }

    /**
     * 根据ID查询
     */
    public Orders selectById(Integer id) {
        return ordersMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Orders> selectAll(Orders orders) {
        return ordersMapper.selectAll(orders);
    }

    /**
     * 分页查询
     */
    public PageInfo<Orders> selectPage(Orders orders, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> list = ordersMapper.selectAll(orders);
        return PageInfo.of(list);
    }

}