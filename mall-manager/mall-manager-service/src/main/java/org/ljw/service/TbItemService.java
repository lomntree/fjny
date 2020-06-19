package org.ljw.service;

import org.ljw.pojo.TbItem;
import org.ljw.utils.EasyUIDataGridResult;
import org.ljw.utils.FjnyResult;

public interface TbItemService {
    //获取商品列表
	public EasyUIDataGridResult getTbItemList(Integer page,Integer rows); 
		
	  public FjnyResult saveItem(TbItem tbItem,String desc);
}
