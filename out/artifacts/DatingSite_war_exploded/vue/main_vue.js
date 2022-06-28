var urlStr = "/index?method=showUser";
var data = $.get
(
    urlStr,
    function w() {
    }
);
//传输来的data数据
var dataPerson = data

new Vue({
    el: "#person",
    data() {
        return {
            dataPerson_vue: dataPerson,
        }
    },
})

new Vue({
    el: ".showImg",
    data() {
        return {
            dataPerson_vue: dataPerson,
            currentIndex: 0,//当前所在图片下标
            timer: null,//定时轮询
            imgArr: [
                {
                    id: 0,
                    url: "./img/sg1.png",
                }, {
                    id: 1,
                    url: "./img/sg2.png",
                }, {
                    id: 2,
                    url: "./img/sg3.png",
                }, {
                    id: 3,
                    url: "./img/sg1.png",
                },
            ]
        }
    },
    methods: {
        //开启定时器
        startInterval() {
            // 事件里定时器应该先清除在设置，防止多次点击直接生成多个定时器
            clearInterval(this.timer);
            this.timer = setInterval(() => {
                this.currentIndex++;
                if (this.currentIndex > this.imgArr.length - 1) {
                    this.currentIndex = 0
                }
            }, 3000)
        },
        // 点击左右箭头
        clickIcon(val) {
            if (val === 'down') {
                this.currentIndex++;
                if (this.currentIndex === this.imgArr.length) {
                    this.currentIndex = 0;
                }
            } else {
                /* 第一种写法
                this.currentIndex--;
                if(this.currentIndex < 0){
                    this.currentIndex = this.imgArr.length-1;
                } */
                // 第二种写法
                if (this.currentIndex === 0) {
                    this.currentIndex = this.imgArr.length;
                }
                this.currentIndex--;
            }
        },
        // 点击控制圆点
        changeImg(index) {
            this.currentIndex = index
        },
        //鼠标移入移出控制
        changeInterval(val) {
            if (val) {
                clearInterval(this.timer)
            } else {
                this.startInterval()
            }
        }
    },
    //进入页面后启动定时轮询
    mounted() {
        this.startInterval()
    }
})