<template>
	<div class="main-content" :style='{"width":"100%","padding":"20px 30px","fontSize":"15px"}'>
		<!-- 列表页 -->
		<div v-if="!showFlag">
			<el-form :style='{"padding":"40px 25% 40px 18%","borderColor":"#eee","borderRadius":"10px","borderStyle":"solid","borderWidth":"1px","background":"#ffffff"}' :inline="true" :model="searchForm" class="add-update-preview">
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 22px 0","display":"inline-block"}' label="心理测试">
					<el-input v-model="searchForm.papername" placeholder="心理测试名称" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 22px 0","display":"inline-block"}' label="试题">
					<el-input v-model="searchForm.questionname" placeholder="试题名称" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 22px 0","display":"inline-block"}'>
					<el-button class="btn3" round @click="search()">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
						查询
					</el-button>
					<el-button class="btn3" type="primary" round @click="back()">
						<span class="icon iconfont icon-xihuan"></span>
						返回
					</el-button>
				</el-form-item>
			</el-form>
			<div class="table-content">
				<el-table
					:data="dataList"
					border
					v-loading="dataListLoading"
					@selection-change="selectionChangeHandler"
					style="width: 100%;"
					>
					<el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
					<el-table-column prop="username" header-align="center" align="center" sortable label="姓名"></el-table-column>
					<el-table-column
						prop="papername"
						header-align="center"
						align="center"
						sortable
						label="心理测试"
						></el-table-column>
					<el-table-column
						prop="questionname"
						header-align="center"
						align="center"
						sortable
						label="试题名称"
					>
						<template slot-scope="scope">
							<div class="ql-snow ql-editor" v-html="scope.row.questionname"></div>
						</template>
					</el-table-column>
					<el-table-column
						prop="myscore"
						header-align="center"
						align="center"
						sortable
						label="试题类型"
						>
						<template slot-scope="scope">
							<el-tag type="success" v-if="scope.row.type==0">客观题</el-tag>
							<el-tag type="danger" v-if="scope.row.type==4">主观题</el-tag>
						</template>
					</el-table-column>
					<el-table-column
						prop="myanswer"
						header-align="center"
						align="center"
						sortable
						label="考生答案"
					></el-table-column>
					<el-table-column
						prop="myscore"
						header-align="center"
						align="center"
						sortable
						label="考生分值"
						>
						<template slot-scope="scope">
							<el-tag v-if="scope.row.myscore==0" type="info">{{scope.row.myscore}}</el-tag>
							<el-tag v-else type="warning">{{scope.row.myscore}}</el-tag>
						</template>
					</el-table-column>
					<el-table-column
						prop="addtime"
						header-align="center"
						align="center"
						sortable
						width="170"
						label="测试时间"
						></el-table-column>
				</el-table>
				<el-pagination
					@size-change="sizeChangeHandle"
					@current-change="currentChangeHandle"
					:current-page="pageIndex"
					:page-sizes="[10, 50, 100, 200]"
					:page-size="pageSize"
					:total="totalPage"
					layout="total, sizes, prev, pager, next, jumper"
					class="pagination-content"
					></el-pagination>
			</div>
		</div>
	</div>
</template>
<script>
	export default {
		data() {
			return {
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
				paperid: 0,
				userid: 0
			};
		},
		props: ["parent"],
		components: {},
		methods: {
			init(row) {
				this.paperid = row.paperid;
				this.userid = row.userid;
				this.getDataList();
			},
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
					paperid: this.paperid,
					userid: this.userid
					// sort: "id"
				};
				if (this.searchForm.papername) {
					params["papername"] = `%${this.searchForm.papername}%`;
				}
				if (this.searchForm.questionname) {
					params["questionname"] = `%${this.searchForm.questionname}%`;
				}
				this.$http({
					url: this.$api.examrecordpage,
					method: "get",
					params: params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						for(let x in data.data.list){
							data.data.list[x].questionname = data.data.list[x].questionname.replace(/img src/gi,"img style=\"width:100%;\" src");
						}
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
			back() {
				this.parent.showFlag = false;
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
	.add-update-preview .btn3 {
		border: 0px solid #ccc;
		cursor: pointer;
		border-radius: 6px;
		padding: 0 10px;
		margin: 0 10px 0 0;
		color: #fff;
		background: #6ea0dc;
		width: auto;
		font-size: 16px;
		min-width: 110px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 40px;
		}
	}
	.add-update-preview .btn3:hover {
		opacity: 0.8;
	}
	.add-update-preview .btn4 {
		border: 0px solid #ccc;
		cursor: pointer;
		border-radius: 6px;
		padding: 0 10px;
		margin: 0 10px 0 0;
		color: #fff;
		background: #4abcff;
		width: auto;
		font-size: 16px;
		min-width: 110px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 40px;
		}
	}
	.add-update-preview .btn4:hover {
		opacity: 0.8;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
				padding: 0 10px 0 0;
				color: #6e6e6e;
				font-weight: 500;
				width: 180px;
				font-size: 15px;
				line-height: 40px;
				text-align: right;
			}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin-left: 180px;
	}
	
	.add-update-preview .el-input {
				width: auto;
			}
	
	.add-update-preview .el-input /deep/ .el-input__inner {
				border: 1px solid #E8E8E8;
				border-radius: 0px;
				padding: 0 12px;
				color: #666;
				background: #fff;
				width: 100%;
				font-size: 15px;
				min-width: 50%;
				height: 40px;
			}
	
	
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
				border: 1px solid #E8E8E8;
				cursor: pointer;
				border-radius: 0px;
				color: #666;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
				border: 1px solid #E8E8E8;
				cursor: pointer;
				border-radius: 0px;
				color: #666;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
				border: 1px solid #E8E8E8;
				cursor: pointer;
				border-radius: 0px;
				color: #666;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
				border: 1px solid #E8E8E8;
				border-radius: 0px;
				padding: 12px;
				color: #666;
				background: #fff;
				width: 100%;
				font-size: 15px;
				min-height: 150px;
				height: auto;
			}
</style>
