<template>
	<div id="app" class="">
		<router-view></router-view>
	</div>
</template>

<script>
	import watermark from 'watermark-dom';
	export default {
		name: 'app',
		data() {return{waterText: ' '}},
		created() {
			
			if(this.waterText!= ' ')setTimeout(()=>{watermark.load({watermark_id: 'shuiyin_id',watermark_txt: this.waterText,watermark_parent_node: null,watermark_angle: 0,watermark_alpha: 1,watermark_x_space: 120,watermark_y_space: 100,watermark_color: '#ff0000',watermark_width: 200});},1500)
		}
	}
</script>

<style>
	#shuiyin_id {position: fixed !important;top: 75% !important;left: 75% !important;z-index: 99999 !important;}

	* {
		padding: 0;
		margin: 0;
		box-sizing: border-box;
	}

	html,
	body {
		width: 100%;
		height: 100%;
	}

	#app {
		height: 100%;
	}

	body {
		padding: 0;
		margin: 0;

	}
</style>