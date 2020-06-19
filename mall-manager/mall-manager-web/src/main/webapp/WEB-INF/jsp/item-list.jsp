<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="super-theme-example">
	<div style="height: 550px;">
		<table id="dgTbItem"></table>
	</div>
	<br /> <br />
	<!--<table id="pg" style="width: 300px"></table>-->
	<div id="itemEditWindow" class="easyui-window" title="商品编辑"
		style="width: 80%; height: 80%;"
		data-options="iconCls:'icon-save',modal:true,closed:true,href:'/item-edit'"></div>
</div>

</div>
<script type="text/javascript">
	$('#dgTbItem').datagrid({
		url : 'item/getItem',
		fit : true,
		pagination : true,
		fitColumns : true,
		toolbar : [ {
			text : '添加',
			iconCls : 'fa fa-plus',
			handler : function() {
				$("#item-add").click();
			}
		}, {
			text : '编辑',
			iconCls : 'fa fa-edit',
			handler : function() {
				var ids = getSelectionsIds();
				if (ids.length == 0) {
					$.messager.alert('提示', '必须选择一个商品才能编辑!');
					return;
				}
				if (ids.indexOf(',') > 0) {
					$.messager.alert('提示', '只能选择一个商品!');
					return;
				}

				$('#itemEditWindow').window({
					onLoad : function() {
						var data = $("#dgTbItem").datagrid("getSelections")[0];
						console.log("data:" + data)
						$('#itemEditForm').form('load', data);
						$.getJSON("item/query/item-desc/"+data.id,function(result){
							if(result.status ==200){
								itemEditEditor.html(result.data.itemDesc);
							}
						});
						TT.init({
							"pics" : data.image,
							"cid" : data.cid,
							fun : function(node) {
							}
						});
					}
				}).window('open');
			}
		}, {
			text : '保存',
			iconCls : 'fa fa-save',
			handler : function() {
			}
		}, {
			text : '删除',
			iconCls : 'fa fa-remove',
			handler : function() {
			}
		} ],
		height : 400,
		columns : [ [ {
			field : 'id',
			title : '商品id',
			width : 100,
			sortable : true,
			align : 'center'

		}, {
			field : 'title',
			title : '商品标题',
			width : 100,
			sortable : true,
			align : 'center'
		}, {
			field : 'sell_point',
			title : '商品卖点',
			width : 100,
			align : 'center',
			sortable : true
		}, {
			field : 'price',
			title : '商品价格',
			width : 100,
			align : 'center',
			sortable : true,
			formatter : TT.formatPrice
		}, {
			field : 'num',
			title : '商品数量',
			width : 100,
			sortable : true,
			align : 'center'
		}, {
			field : 'barcode',
			title : '商品条形码',
			width : 100,
			sortable : true,
			align : 'center'
		}, {
			field : 'image',
			title : '商品图片',
			width : 200,
			align : 'center',
			formatter : function(value, row) {
				return "<img src ="+value+" width='175px',height='200px '>"
			}
		}, {
			field : 'cid',
			title : '类目',
			width : 100,
			sortable : true,
			align : 'center'
		}, {
			field : 'status',
			title : '商品状态',
			width : 100,
			align : 'center',
			formatter : TT.formatItemStatus
		}, {
			field : 'created',
			title : '创建时间',
			width : 100,
			align : 'center',
			formatter : TT.formatDateTime
		}, {
			field : 'updated',
			title : '更新时间',
			width : 100,
			align : 'center',
			formatter : TT.formatDateTime
		}, ] ]
	});
	function getSelectionsIds() {
		var itemList = $("#dgTbItem");
		var sels = itemList.datagrid("getSelections");
		var ids = [];
		for ( var i in sels) {
			ids.push(sels[i].id);
		}
		ids = ids.join(",");
		return ids;
	}
</script>
