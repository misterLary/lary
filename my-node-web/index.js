var http = require("http");//引入HTTP
//noinspection JSUnresolvedFunction
var fs = require("fs");
//noinspection JSUnresolvedFunction
var app = http.createServer(function (req,resp) {//创建服务

    resp.writeHead(200,{"Content_Type":"text/html"});//设置响应格式
    resp.write("hello NodeJS");
    resp.end();

});

//noinspection JSUnresolvedFunction
app.listen(7798);//监听端口号
