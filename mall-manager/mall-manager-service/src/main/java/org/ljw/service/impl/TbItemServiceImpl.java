package org.ljw.service.impl;

import java.util.List;

import org.ljw.mapper.TbItemMapper;
import org.ljw.pojo.TbItem;
import org.ljw.pojo.TbItemExample;
import org.ljw.service.TbItemService;
import org.ljw.utils.EasyUIDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TbitemServiceImpl implements TbItemService {
    
	@Autowired
	private TbItemMapper tbItemMapper;
	
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

}
