<template>
	<div class="forum-preview">
		<div class="forum-title">
			<div>互动社区</div>
		</div>
		<el-form :inline="true" :model="formSearch" class="list-form-pv">
			<el-form-item class="search-item">
				<el-input v-model="title" placeholder="标题"></el-input>
			</el-form-item>
			<div class="search-btn-item">
				<el-button class="searchBtn" type="primary" @click="getForumList(1)">
					<span class="icon iconfont icon-shouye-zhihui"></span>
					查询
				</el-button>
				<el-button class="pubBtn" type="primary" @click="toForumAdd">
					<span class="icon iconfont icon-shouye-zhihui"></span>
					发布帖子
				</el-button>
			</div>
		</el-form>
		<div class="category-list">
			<div class="item" @click="categoryClick(0)" :class="categoryIndex == 0 ? 'active' : ''">全部</div>
			<div v-for="(item,index) in categoryList" @click="categoryClick(index+1)" :key="index" class="item" :class="categoryIndex == index+1 ? 'active' : ''">{{item.typename}}</div>
		</div>
		<div class="z-box">
			<div class="section-content" v-for="item in forumList" :key="item.id" @click.stop="toForumDetail(item)">
				<img class="item-img" :src="$config.baseUrl + item.cover.split(',')[0]" @click.stop="imgPreView($config.baseUrl + item.cover.split(',')[0])" />
				<div class="item-title">{{item.title}}</div>
				<div class="item-user">发布人：{{item.isanon==1&&item.userid!=userid?'匿名':item.username}}</div>
				<div class="item-time">{{item.addtime}}</div>
			</div>
		</div>
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
			@current-change="curChange"
			@prev-click="prevClick"
			@next-click="nextClick"
			></el-pagination>
		<el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
			<img :src="previewImg" alt="" style="width: 100%;">
		</el-dialog>
	</div>
</template>

<script>
	export default {
		//数据集合
		data() {
			return {
				formSearch: {},
				title: '',
				layouts: '',
				forumList: [],
				total: 1,
				pageSize: 10,
				pageSizes: [],
				totalPage: 1,
				previewImg: '',
				previewVisible: false,
				userid: localStorage.getItem('frontUserid'),
				categoryIndex: 0,
				categoryList: [],
			}
		},
		created() {
			this.getCategoryList()
			this.getForumList(1);
		},
		//方法集合
		methods: {
			getCategoryList(){
				this.$http.get('forumtype/list', {}).then(res => {
					if (res.data.code == 0) {
						this.categoryList = res.data.data.list
					}
				});
			},
			categoryClick(index) {
				this.categoryIndex = index
				this.getForumList()
			},
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
			},
			getForumList(page) {
				let params = {page, limit: this.pageSize, isdone: '开放', sort: 'istop,toptime', order: 'desc,desc',delflag: 0};
				let searchWhere = {};
				if(this.title != '') searchWhere.title = '%' + this.title + '%';
				if(this.categoryIndex!=0){
					searchWhere.typename = this.categoryList[this.categoryIndex - 1].typename
				}
				this.$http.get('forum/flist', {params: Object.assign(params, searchWhere)}).then(res => {
					if (res.data.code == 0) {
						this.forumList = res.data.data.list;
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
				this.getForumList(page);
			},
			prevClick(page) {
				this.getForumList(page);
			},
			nextClick(page) {
				this.getForumList(page);
			},
			toForumAdd() {
				this.$router.push('/index/forumAdd');
			},
			toForumDetail(item) {
				this.$router.push({path: '/index/forumDetail', query: {id: item.id}});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.forum-preview {
				padding: 20px 16%;
				margin: 20px auto;
				background: none;
				width: 100%;
				position: relative;
				.forum-title {
						margin: 10px 0;
						color: #fff;
						background: url(http://codegen.caihongy.cn/20241127/0acd634bb5d34165a420a799b58e4f17.png) 100% 100%/cover no-repeat;
						font-weight: bold;
						font-size: 24px;
						line-height: 100px;
						text-align: center;
						height: 80px;
						div {
								padding: 0 10px;
								color: inherit;
								width: 100%;
								font-size: inherit;
								line-height: 100px;
								text-align: center;
								height: 80px;
							}
		}
		.list-form-pv {
						padding: 10px;
						margin: 10px 0;
						background: none;
						display: flex;
						width: 100%;
						align-items: center;
						flex-wrap: wrap;
						height: auto;
						.search-item {
								margin: 0 10px;
								/deep/ .el-form-item__content {
										display: flex;
										align-items: center;
									}
				.el-input {
										width: 100%;
									}
				.el-input /deep/ .el-input__inner {
										border: 0;
										border-radius: 20px;
										padding: 0 10px;
										margin: 0;
										outline: none;
										color: #000;
										width: 280px;
										font-size: 14px;
										line-height: 42px;
										height: 42px;
									}
			}
			.search-btn-item {
								display: flex;
								.searchBtn {
										cursor: pointer;
										border: 0;
										border-radius: 20px;
										padding: 0px 35px;
										margin: 0 10px 0 0;
										outline: none;
										background: #FDD922;
										width: auto;
										font-size: 14px;
										line-height: 42px;
										height: 42px;
										.icon {
												margin: 0 10px 0 0;
												color: #fff;
												display: none;
											}
				}
				
				.searchBtn:hover {
										opacity: 0.7;
									}
				
				.pubBtn {
										cursor: pointer;
										border: 0;
										border-radius: 20px;
										padding: 0px 35px;
										margin: 0 10px 0 0;
										outline: none;
										background: #FDD922;
										width: auto;
										font-size: 14px;
										line-height: 42px;
										height: 42px;
										.icon {
												margin: 0 10px 0 0;
												color: #fff;
												display: none;
												font-size: 14px;
											}
				}
				
				.pubBtn:hover {
										opacity: 0.7;
									}
			}
		}
		.category-list {
						padding: 10px;
						background: none;
						display: flex;
						width: 100%;
						justify-content: center;
						height: auto;
						.item {
								cursor: pointer;
								border-radius: 5px;
								margin: 0 10px 0 0;
								color: #157ED2;
								background: #fff;
								width: 72px;
								font-size: 14px;
								line-height: 36px;
								text-align: center;
							}
			
			.item:hover {
								color: #fff;
								background: #157ED2;
							}
			
			.item.active {
								color: #fff;
								background: #157ED2;
							}
		}
		.z-box {
						margin: 50px auto;
						display: flex;
						width: 100%;
						justify-content: space-between;
						flex-wrap: wrap;
						.section-content {
								cursor: pointer;
								border-radius: 10px;
								padding: 20px;
								margin: 0 0 20px;
								background: #fff;
								display: flex;
								width: 49%;
								align-items: center;
								flex-wrap: wrap;
								.item-img {
										border-radius: 10px;
										margin: 20px calc(100% - 100px) 20px 0;
										width: 100px;
										transition: all .4s;
										height: 100px;
										order: 2;
									}
				.item-title {
										color: #333;
										width: 100%;
										font-size: 16px;
										transition: all .6s;
										order: 1;
									}
				.item-user {
										color: #666;
										width: 50%;
										font-size: 14px;
										transition: all .4s;
										order: 3;
									}
				.item-time {
										color: #666;
										width: 50%;
										font-size: 14px;
										text-align: right;
										order: 4;
									}
			}
			.section-content:hover {
								box-shadow: 0 4px 8px rgba(21, 126, 210, 0.2);
								background: #fff;
								.item-title {
										color: #000;
										font-weight: bold;
									}
				.item-user {
										color: #111;
									}
				.item-time {
										color: #111;
									}
			}
		}
	}
</style>
