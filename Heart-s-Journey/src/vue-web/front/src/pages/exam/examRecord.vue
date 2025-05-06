<template>
	<div :style='{"width":"100%","padding":"20px 16%","margin":"10px auto","position":"relative","background":"none"}'>
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="section-title" :style='{"margin":"10px 0","color":"#fff","textAlign":"center","background":"url(http://codegen.caihongy.cn/20241127/0acd634bb5d34165a420a799b58e4f17.png) 100% 100%/cover no-repeat","fontSize":"24px","lineHeight":"100px","fontWeight":"bold","height":"80px"}'>{{title}}</div>
		<el-table
			:data="tableData"
			style="width: 100%">
			<el-table-column
				label="心理测评名称"
				prop="papername">
			</el-table-column>
			<el-table-column
				label="试题"
				prop="questionname">
				<template slot-scope="scope">
					<div class="ql-snow ql-editor" v-html="scope.row.questionname"></div>
				</template>
			</el-table-column>
			<el-table-column
				prop="type"
				label="试题类型"
			>
				<template slot-scope="scope">
					<el-tag type="success" v-if="scope.row.type==0">客观题</el-tag>
					<el-tag type="danger" v-if="scope.row.type==4">主观题</el-tag>
				</template>
			</el-table-column>
			<el-table-column
				label="考生答案"
				prop="myanswer">
			</el-table-column>
			<el-table-column
				label="测试得分">
				<template slot-scope="scope">
					{{ scope.row.myscore }}分
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
			@next-click="nextClick"
		></el-pagination>
	
	</div>
</template>

<script>
	export default {
		data() {
			return {
				layouts: '',
				title: '测试记录',
				tableData: [],
				total: 1,
				pageSize: 10,
				pageSizes: [],
				totalPage: 1
			}
		},
		created() {
			this.getExamRecord(1);
		},
		methods: {
			backClick() {
				this.$router.push('/index/center')
			},
			getExamRecord(page) {
				let otherParams = {};
				if (this.$route.params.type == 0) {
					this.title = '错题本';
					otherParams.myscore = 0;
					otherParams.ismark = 1
				}
				this.$http.get('examrecord/list', {params: Object.assign({page, limit: this.pageSize, paperid: this.$route.query.paperid, userid: localStorage.getItem('frontUserid')}, otherParams)}).then(res => {
					if (res.data.code == 0) {
						for(let x in res.data.data.list){
							res.data.data.list[x].questionname = res.data.data.list[x].questionname.replace(/img src/gi,"img style=\"width:100%;\" src");
						}
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
			curChange(page) {
				this.getExamRecord(page);
			},
			prevClick(page) {
				this.getExamRecord(page);
			},
			nextClick(page) {
				this.getExamRecord(page);
			},
			handleView(index, row) {
				this.$router.push({path: '/index/examRecord', query: {id: row.id}})
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.section {
		width: 900px;
		margin: 0 auto;
	}
</style>
