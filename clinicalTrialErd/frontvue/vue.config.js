// //spring vue 프로젝트 빌드
const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  outputDir: '../src/main/resources/static', // Build Directory
	devServer: {
		proxy: 'http://13.239.74.201:8080' // Spring Boot Server
	}
})