const base = {
    get() {
        return {
            url : "http://localhost:48080/springboot-server/",
            name: "springboot-server",
            // 退出到首页链接
            indexUrl: 'http://localhost:48080/springboot-server/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "心航"
        } 
    }
}
export default base
