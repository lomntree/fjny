package org.ljw.service;

import org.ljw.utils.EasyUIDataGridResult;

public interface TbItemService {
    //获取商品列表
	public EasyUIDataGridResult getTbItemList(Integer page,Integer rows); 
		
	
}
