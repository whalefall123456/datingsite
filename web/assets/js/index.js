function initUserlist(){
        var urlStr = "user?method=showUser";
        var result;
        var data = $.get
        (
            urlStr,
            function w() {
                result = JSON.parse(data.responseText)
                var userlist = document.getElementsByClassName("row gx-5 gx-sm-3 gx-lg-5 gy-lg-5 gy-3 pb-3 projects");
                for(i=1;i<9;i++){
                    //设置超链接
                    userlist.item(0).getElementsByTagName("div").item((i-1)*3).getElementsByTagName("a").item(0).setAttribute("href","userinfo.html?uid="+result['user'+i].uid);
                    //设置图片
                    userlist.item(0).getElementsByTagName("div").item((i-1)*3).getElementsByTagName("a").item(0).getElementsByTagName("img").item(0).setAttribute("src",result['user'+i].image);
                    //设置用户名
                    userlist.item(0).getElementsByTagName("div").item((i-1)*3).getElementsByTagName("a").item(0).getElementsByTagName("div").item(0).getElementsByTagName("div").item(0).getElementsByTagName("span").item(0).innerText = result['user'+i].unick;
                    //设置个人介绍
                    userlist.item(0).getElementsByTagName("div").item((i-1)*3).getElementsByTagName("a").item(0).getElementsByTagName("div").item(0).getElementsByTagName("div").item(0).getElementsByTagName("p").item(0).innerText = result['user'+i].introduce;
                }
            }
        );
    }
initUserlist();
