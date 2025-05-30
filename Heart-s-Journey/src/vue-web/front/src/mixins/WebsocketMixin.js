import store from '@/store/'
// import { ACCESS_TOKEN } from '@/store/mutation-types'
import Vue from 'vue'
import config from '@/config/config'
export const WebsocketMixin = {
	mounted() {
		// this.initWebSocket();
	},
	destroyed: function() {
		// 离开页面生命周期函数
		// this.websocketOnclose();
	},
	methods: {
		initWebSocket(toId) {
			// let token = Vue.ls.get(localStorage.getItem('frontToken'))
			// console.log("------------WebSocket连接成功");
			// WebSocket与普通的请求所用协议有所不同，ws等同于http，wss等同于https
			var userId = localStorage.getItem('frontUserid')
			// if(!this.socketUrl.startsWith('/')){
			//   this.socketUrl = '/' + this.socketUrl
			// }
			// if(!this.socketUrl.endsWith('/')){
			//   this.socketUrl = this.socketUrl + '/'
			// }
			var url = `${config.baseUrl}ws?user_id=` + userId + '&to_id=' + toId;
			if (this.websock == null) {
				this.websock = new WebSocket(url);
				this.websock.onopen = this.websocketOnopen;
				this.websock.onerror = this.websocketOnerror;
				this.websock.onmessage = this.websocketOnmessage;
				// this.websock.onclose = this.websocketOnclose;

			}

		},
		websocketOnopen: function() {
			// console.log("WebSocket连接成功");
		},
		websocketOnerror: function(e) {
			// console.log("WebSocket连接发生错误");
			this.reconnect();
		},
		websocketOnclose: function(e) {
			this.websock.close(1000)
			// console.log('关闭了')
			// this.reconnect();
		},
		websocketOnmessage:function(e) {
			// console.log('收到了',e)
		},
		websocketSend(text) {
			// 数据发送
			try {
				this.websock.send(text);
			} catch (err) {
				console.log("send failed (" + err.code + ")");
			}
		},
		reconnect() {
			// var that = this;
			// if (that.lockReconnect) return;
			// that.lockReconnect = true;
			// //没连接上会一直重连，设置延迟避免请求过多
			// setTimeout(function() {
			// 	console.info("尝试重连...");
			// 	that.initWebSocket();
			// 	that.lockReconnect = false;
			// }, 5000);
		},
	}

}