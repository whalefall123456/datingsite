function getCookie(c_name)
{
    if (document.cookie.length>0)//首先查询cookie是否是空的
    {
        c_start=document.cookie.indexOf(c_name + "=")//检测这个cookie是否存在
        if (c_start!=-1)//如果cookie存在
        {
            c_start=c_start + c_name.length+1 //获取到cookie的值的开始位置
            c_end=document.cookie.indexOf(";",c_start)//从c_start开始查找";"的存在
            if (c_end==-1) c_end=document.cookie.length//如果没找到，说明是最后一项
            return document.cookie.substring(c_start,c_end)//把cookie的值拆分出来并且对这个值进行解码，unescape()与escape()相对，对被escape()编码的字符串进行解码
        }
    }
    return ""//不存在就返回空
}