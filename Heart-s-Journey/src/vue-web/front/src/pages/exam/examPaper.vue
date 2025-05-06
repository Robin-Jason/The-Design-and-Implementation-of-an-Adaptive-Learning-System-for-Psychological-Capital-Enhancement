<template>
	<div :style='{"width":"100%","padding":"20px 16%","margin":"10px auto","position":"relative","background":"none"}'>
		<div class="section-title" :style='{"margin":"10px 0","color":"#fff","textAlign":"center","background":"url(http://codegen.caihongy.cn/20241127/0acd634bb5d34165a420a799b58e4f17.png) 100% 100%/cover no-repeat","fontSize":"24px","lineHeight":"100px","fontWeight":"bold","height":"80px"}'>心理测评</div>
		<el-form :style='{"padding":"10px","alignItems":"center","flexWrap":"wrap","background":"none","display":"flex","width":"100%","height":"auto","order":"1"}' :inline="true" :model="formSearch" class="center-form-pv">
			<el-form-item :style='{"margin":"0 10px 0 0"}'>
				<div class="lable" v-if="false" :style='{"width":"100px","padding":"0 10px 0 0","lineHeight":"42px","fontSize":"13px","display":"inline-block"}'>心理测评名称：</div>
				<el-input v-model="formSearch.name" placeholder="心理测评名称" @keydown.enter.native="getExamList(1)" clearable></el-input>
			</el-form-item>
			<el-button :style='{"cursor":"pointer","border":"0","padding":"0px 30px","margin":"0 10px 0 0","outline":"none","color":"#000","borderRadius":"30px","background":"#FDD922","width":"auto","fontSize":"14px","lineHeight":"42px","height":"42px"}' type="primary" @click="getExamList(1)"><i v-if="false" :style='{"color":"#fff","margin":"0 10px 0 0","fontSize":"14px"}' class="el-icon-search"></i>查询</el-button>
		</el-form>
		<el-table
			:data="tableData"
			style="width: 100%">
			<el-table-column
				label="心理测评名称"
				prop="name">
			</el-table-column>
			<el-table-column
				label="测试时长">
				<template slot-scope="scope">
					{{ scope.row.time }}分钟
				</template>
			</el-table-column>
			<el-table-column
				label="创建时间"
				prop="addtime">
			</el-table-column>
			<el-table-column label="操作" width="150">
				<template slot-scope="scope">
					<el-button
					type="success"
					size="mini"
					@click="handleExam(scope.$index, scope.row)">测试</el-button>
				</template>
			</el-table-column>
		</el-table>
		
		<el-pagination
			background
			id="pagination" class="pagination"
			:pager-count="7"
			:page-size="pageSize"
			:page-sizes="pageSizes"
			prev-text="<"
			next-text=">"
			:hide-on-single-page="true"
			:layout='["total","prev","pager","next"].join()'
			:total="total"
			:style='{"padding":"0","boxShadow":"inset 0px 3px 6px 0px #ccc","margin":"20px auto","whiteSpace":"nowrap","color":"#333","textAlign":"center","background":"#fff","width":"60%","fontSize":"inherit","fontWeight":"500"}'
			@current-change="curChange"
			@prev-click="prevClick"
			@size-change="sizeChange"
			@next-click="nextClick"
			></el-pagination>
		
	</div>
</template>

<script>
	export default {
		data() {
			return {
				layouts: '',
				tableData: [],
				total: 1,
				pageSize: 10,
				pageSizes: [],
				totalPage: 1,
				formSearch: {}
			}
		},
		created() {
			this.getExamList(1);
		},
		methods: {
			getExamList(page) {
				let params = {page, limit: this.pageSize, status: 1,sort: 'addtime',order: 'desc'}
				if(this.formSearch.name){
					params['name'] = `%${this.formSearch.name}%`
				}
				this.$http.get('exampaper/list', {params: params}).then(res => {
					if (res.data.code == 0) {
						this.tableData = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
		  sizeChange(size){
			this.pageSize = size
			this.getList(1);
		  },
			curChange(page) {
				this.getExamList(page);
			},
			prevClick(page) {
				this.getExamList(page);
			},
			nextClick(page) {
				this.getExamList(page);
			},
			handleExam(index, row) {
				this.$router.push({path: '/exam', query: {id: row.id, time: row.time}})
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.section {
		width: 900px;
		margin: 0 auto;
	}
	.center-form-pv .el-input {
		width: auto;
	}

  
</style>
