package org.ljw.service.impl;

import java.util.Date;
import java.util.List;

import org.ljw.mapper.TbItemDescMapper;
import org.ljw.mapper.TbItemMapper;
import org.ljw.pojo.TbItem;
import org.ljw.pojo.TbItemDesc;
import org.ljw.pojo.TbItemExample;
import org.ljw.service.TbItemService;
import org.ljw.utils.EasyUIDataGridResult;
import org.ljw.utils.FjnyResult;
import org.ljw.utils.IdRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class TbItemServiceImpl implements TbItemService {
    
	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Autowired 
	private TbItemDescMapper tbItemDescMapper;
    @Override
	public EasyUIDataGridResult getTbItemList(Integer page,Integer rows) {
		
		PageHelper.startPage(page,rows);
		TbItemExample example =new TbItemExample();
		List<TbItem> list=tbItemMapper.selectByExample(example);
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).toString());
		}
		PageInfo<TbItem> pageInfo= new PageInfo<>(list);
		long total=pageInfo.getTotal();
		EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult(total,list);
		return easyUIDataGridResult;
	}
    
    
    
   @Override
   public FjnyResult saveItem(TbItem tbItem,String desc) {
	   tbItem.setId(IdRandom.getId());
	   tbItem.setStatus((byte)1);
	   tbItem.setCreated(new Date());
	   tbItem.setUpdated(new Date());
	   int a=tbItemMapper.insertSelective(tbItem);
       
	   if(a<0) {
		   return FjnyResult.build(500,"添加商品失败");
	   }
	   TbItemDesc tbItemDesc=new TbItemDesc();
	   tbItemDesc.setItemId(tbItem.getId());
	   tbItemDesc.setItemDesc(desc);
	   tbItemDesc.setCreated(new Date());
	   tbItemDesc.setUpdated(new Date());
	   tbItemDescMapper.insertSelective(tbItemDesc);
	   return FjnyResult.ok();
   }

}
