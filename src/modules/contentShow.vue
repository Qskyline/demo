<template><b-col class="content_style">
	<b-card-group deck v-for="m in lineCount" class="card_row">
	    <b-card no-body v-for="n in count" title="测试" img-src="https://lorempixel.com/300/300/" img-alt="Img" img-top>
	      <b-card-body class="card_colum">
	      	<h3>测试</h3>
	      	<p class="card-text card_detail">test</p>
	      	<p class="card-text">测试:test</p>
	      </b-card-body>
	    </b-card>
	</b-card-group>
</b-col></template>

<script>
export default {
	data() {
		return {
			count: 5,
			lineCount: 3
		}
	}
}
</script>

<style>
.content_style {
	margin-left: 9%;
	margin-right: 9%;
}
.card_row {
	margin-bottom: 0.6rem;
}
.card_colum {
	padding-top: 0.6rem !important;
	padding-bottom: 0.6rem !important;
}
.card_detail {
	 margin-bottom: 0.5rem !important;
}
</style>