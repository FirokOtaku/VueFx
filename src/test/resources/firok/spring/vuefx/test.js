const app = new Vue({
    el: '#app',
    data: {
        msg: 'hello world',
        in1: '',
        VueFxController: null,
        VueFxApplication: null,
    },
    methods: {
        btnClick() {
            this.VueFxController.log("JS 脚本调用 Java 方法: " + this.in1)
        },
    },
})

function VueFxPostInit()
{
    app.VueFxController = VueFxController
    app.VueFxApplication = VueFxApplication
}
