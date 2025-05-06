<template>
	<div class="main-content" :style='{"width":"100%","padding":"20px 30px","fontSize":"15px"}'>
		<!-- 列表页 -->
		<template v-if="!showFlag">
			<el-form :style='{"border":"0px solid #fff","padding":"10px","margin":"0","flexWrap":"wrap","background":"#ffffff","display":"flex","width":"100%","justifyContent":"space-between"}' :inline="true" :model="searchForm" class="center-form-pv">
				<el-row :style='{"padding":"10px","alignItems":"center","flexWrap":"wrap","background":"none","display":"flex"}'>
					<div :style='{"alignItems":"center","margin":"0 10px 0 0","display":"flex"}'>
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">心理测试</label>
						<el-input v-model="searchForm.name" placeholder="心理测试名称" clearable></el-input>
					</div>
					<el-button class="search" :style='{"border":"0","cursor":"pointer","padding":"0 20px","color":"#fff","borderRadius":"0px","background":"#0977fd","width":"auto","fontSize":"16px","minWidth":"90px","height":"40px"}' type="success" @click="search()">
						<span class="icon iconfont icon-fangdajing07" :style='{"margin":"0 2px","fontSize":"16px","color":"#fff","display":"none","height":"40px"}'></span>
						搜索
					</el-button>
				</el-row>
			</el-form>

			<div :style='{"border":"0px solid #fff","width":"100%","padding":"0 20px 20px","fontSize":"14px","color":"#000","background":"#fff"}'>
				<el-table
					:stripe='false'
					:style='{"padding":"0px 0","borderColor":"#f0f0f0","borderRadius":"0","borderWidth":"0px 0 0 1px","background":"#fff","width":"100%","fontSize":"inherit","borderStyle":"solid"}'
					:data="dataList"
					:border='true'
					v-loading="dataListLoading"
					@selection-change="selectionChangeHandler"
					style="width: 100%;"
				>
					<el-table-column :resizable='true' type="selection" header-align="center" align="center" width="50"></el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="name" header-align="center" align="center" sortable label="心理测试">
						<template slot-scope="scope">{{scope.row.name}}</template>
					</el-table-column>
					<el-table-column
						:resizable='true' :sortable='true'
						prop="time"
						header-align="center"
						align="center"
						sortable
						label="测试时长/分钟"
					>
						<template slot-scope="scope">{{scope.row.time}}</template>
					</el-table-column>
					<el-table-column
						header-align="center"
						align="center"
						width="150"
						label="操作"
					>
						<template slot-scope="scope">
							<el-button class="view" :style='{"border":"1px solid #0977fd60","cursor":"pointer","padding":"0 10px","margin":"0 5px 5px 0","color":"#0977fd","borderRadius":"0px","background":"#fff","width":"auto","fontSize":"14px","height":"32px"}' type="success" @click="addOrUpdateHandler(scope.row.id)">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								测试
							</el-button>
						</template>
					</el-table-column>
				</el-table>
			</div>
			<el-pagination
				@size-change="sizeChangeHandle"
				@current-change="currentChangeHandle"
				:current-page="pageIndex"
				:page-sizes="[10, 50, 100, 200]"
				:page-size="pageSize"
				:total="totalPage"
				:layout="layouts.join()"
				prev-text="<"
				next-text=">"
				:hide-on-single-page="false"
				:style='{"padding":"0 20px 20px","margin":"0px auto","whiteSpace":"nowrap","color":"#333","textAlign":"left","background":"#fff","width":"100%","fontSize":"inherit","position":"relative","fontWeight":"500"}'
			></el-pagination>
		</template>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				layouts: ["prev","pager","next","sizes"],
				searchForm: {
					key: ""
				},
				dataList: [],
				pageIndex: 1,
				pageSize: 10,
				totalPage: 0,
				dataListLoading: false,
				dataListSelections: [],
				showFlag: false,
				user: {}
			};
		},
		mounted() {
			this.$http({
				url: `${this.$storage.get("sessionTable")}/session`,
				method: "get"
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.user = data.data;
				} else {
					this.$message.error(data.msg);
				}
			});
			this.init();
			this.getDataList();
		},
		components: {},
		methods: {
			init() {},
			search() {
				this.pageIndex = 1;
				this.getDataList();
			},
			// 获取数据列表
			getDataList() {
				this.dataListLoading = true;
				var params = {
					page: this.pageIndex,
					limit: this.pageSize,
					sort: "id",
					status: "1"
				};
				if (this.searchForm.name) {
					params["name"] = `%${this.searchForm.name}%`;
				}
				this.$http({
					url: this.$api.exampaperpage,
					method: "get",
					params: params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.dataList = data.data.list;
						this.totalPage = data.data.total;
					} else {
						this.dataList = [];
						this.totalPage = 0;
					}
					this.dataListLoading = false;
				});
			},
			// 每页数
			sizeChangeHandle(val) {
				this.pageSize = val;
				this.pageIndex = 1;
				this.getDataList();
			},
			// 当前页
			currentChangeHandle(val) {
				this.pageIndex = val;
				this.getDataList();
			},
			// 多选
			selectionChangeHandler(val) {
				this.dataListSelections = val;
			},
			// 添加/修改
			addOrUpdateHandler(id) {
				this.$http({
					url: `examrecord/deleteRecords?userid=${this.user.id}&paperid=${id}`,
					method: "post",
					data: {}
				}).then(({ data }) => {});
				this.$router.push({
					name: "adminexam",
					params: {
						id: id
					}
				});
			},
			// 删除
			deleteHandler(id) {
				var ids = id
					? [Number(id)]
					: this.dataListSelections.map(item => {
						return Number(item.id);
					});
				this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning"
				}).then(() => {
					this.$http({
						url: this.$api.exampaperdelete,
						method: "post",
						data: ids
					}).then(({ data }) => {
						if (data && data.code === 0) {
							this.$message({
								message: "操作成功",
								type: "success",
								duration: 1500,
								onClose: () => {
									this.search();
								}
							});
						} else {
							this.$message.error(data.msg);
						}
					});
				});
			}
		}
	};
</script>
<style lang="scss" scoped>
	.form-content {
		background: transparent;
	}
	.table-content {
		background: transparent;
	}

	
	// form
	.center-form-pv .el-input {
				width: auto;
			}
	.center-form-pv .el-input /deep/ .el-input__inner {
				border: 1px solid #ddd;
				border-radius: 0px;
				padding: 0 12px;
				color: #666;
				width: 150px;
				font-size: 15px;
				height: 40px;
			}
	
	// table
	.el-table /deep/ .el-table__header-wrapper thead {
				color: #999;
				background: #fff;
				font-weight: 500;
				width: 100%;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr {
				background: #cae2ff;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th {
				padding: 8px 0;
				background: none;
				border-color: #f6f6f6;
				border-width: 0 0px 0px 0;
				border-style: solid;
				text-align: left;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
				padding: 0 0 0 5px;
				word-wrap: normal;
				color: #0977fd;
				white-space: normal;
				font-weight: bold;
				display: flex;
				vertical-align: middle;
				font-size: 14px;
				line-height: 24px;
				text-overflow: ellipsis;
				word-break: break-all;
				width: 100%;
				align-items: center;
				position: relative;
				min-width: 110px;
			}
	
	
	.el-table /deep/ .el-table__body-wrapper tbody {
				width: 100%;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr {
				background: #fff;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 4px 0;
				color: #333;
				background: #fff;
				font-size: inherit;
				border-color: #0977fd30;
				border-width: 0 0px 1px 0;
				border-style: solid;
				text-align: left;
			}
	
		
	.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
				padding: 4px 0;
				color: #333;
				background: #f4f9ff;
				border-color: #0977fd30;
				border-width: 0 0px 1px 0;
				border-style: solid;
				text-align: left;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 4px 0;
				color: #333;
				background: #fff;
				font-size: inherit;
				border-color: #0977fd30;
				border-width: 0 0px 1px 0;
				border-style: solid;
				text-align: left;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
				padding: 0 0 0 5px;
				overflow: hidden;
				word-break: break-all;
				white-space: normal;
				font-size: inherit;
				line-height: 24px;
				text-overflow: ellipsis;
			}
	
	// pagination
	.main-content .el-pagination /deep/ .el-pagination__total {
				margin: 0 10px 0 0;
				color: #666;
				font-weight: 400;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev {
				border: none;
				border-radius: 100px;
				padding: 0;
				margin: 0 5px;
				color: #fff;
				background: #000;
				display: inline-block;
				vertical-align: top;
				width: 28px;
				font-size: 16px;
				line-height: auto;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-next {
				border: none;
				border-radius: 100%;
				padding: 0;
				margin: 0 5px;
				color: #fff;
				background: #000;
				display: inline-block;
				vertical-align: top;
				width: 28px;
				font-size: 16px;
				line-height: auto;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev:disabled {
				border: none;
				cursor: not-allowed;
				padding: 0;
				margin: 0 5px;
				color: #666;
				display: inline-block;
				vertical-align: top;
				font-size: 16px;
				line-height: auto;
				border-radius: 100px;
				background: #ccc;
				width: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-next:disabled {
				border: none;
				cursor: not-allowed;
				padding: 0;
				margin: 0 5px;
				color: #666;
				display: inline-block;
				vertical-align: top;
				font-size: 16px;
				line-height: auto;
				border-radius: 100px;
				background: #ccc;
				width: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pager {
				padding: 0;
				margin: 0;
				display: inline-block;
				vertical-align: top;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number {
				cursor: pointer;
				border-radius: 100%;
				padding: 0 10px;
				margin: 0 2px;
				color: #fff;
				background: #b9b9b9;
				display: inline-block;
				vertical-align: top;
				font-size: 16px;
				line-height: 28px;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number:hover {
				cursor: pointer;
				border-radius: 100%;
				padding: 0 10px;
				margin: 0 2px;
				color: #fff;
				background: #0977fd;
				display: inline-block;
				vertical-align: top;
				font-size: 16px;
				line-height: 28px;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number.active {
				cursor: default;
				border-radius: 100%;
				padding: 0 10px;
				margin: 0 2px;
				color: #fff;
				background: #0977fd;
				display: inline-block;
				vertical-align: top;
				font-size: 16px;
				line-height: 28px;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes {
				display: inline-block;
				vertical-align: top;
				font-size: 15px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input {
				margin: 0 5px;
				width: 100px;
				position: relative;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
				border: 1px solid #DCDFE6;
				cursor: pointer;
				padding: 0 25px 0 8px;
				color: #606266;
				display: inline-block;
				font-size: 15px;
				line-height: 28px;
				border-radius: 10px;
				outline: 0;
				background: #FFF;
				width: 100%;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
				top: 0;
				position: absolute;
				right: 0;
				height: 100%;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
				cursor: pointer;
				color: #C0C4CC;
				width: 25px;
				font-size: 14px;
				line-height: 28px;
				text-align: center;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump {
				margin: 0 0 0 24px;
				color: #606266;
				display: inline-block;
				vertical-align: top;
				font-size: 15px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input {
				border-radius: 3px;
				padding: 0 2px;
				margin: 0 2px;
				display: inline-block;
				width: 50px;
				font-size: 15px;
				line-height: 18px;
				position: relative;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
				border: 1px solid #DCDFE6;
				cursor: pointer;
				padding: 0 3px;
				color: #606266;
				display: inline-block;
				font-size: 15px;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: #FFF;
				width: 100%;
				text-align: center;
				height: 28px;
			}
	
	.center-form-pv .search {
				border: 0;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 20px;
				color: #fff;
				background: #0977fd;
				width: auto;
				font-size: 16px;
				min-width: 90px;
				height: 40px;
			}
	
	.center-form-pv .search:hover {
				opacity: 0.8;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view {
				border: 1px solid #0977fd60;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 10px;
				margin: 0 5px 5px 0;
				color: #0977fd;
				background: #fff;
				width: auto;
				font-size: 14px;
				height: 32px;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view:hover {
				opacity: 0.8;
			}
	
	// list one
	.one .list1-view {
				border: 0;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 15px;
				margin: 0 5px 5px 0;
				outline: none;
				color: #fff;
				background: #157ed2;
				width: auto;
				font-size: 14px;
				height: 32px;
			}
	
	.one .list1-view:hover {
				opacity: 0.8;
			}
</style>
