<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>登陆</title>
</head>
<body>
<center>
    <a href="logout">退出登录</a><br><br>
    <form action="upload" enctype="multipart/form-data" method="post">
        <table>
            <tr>
                <td>文件描述:</td>
                <td><input type="text" name="description"></td>
            </tr>
            <tr>
                <td>请选择文件:</td>
                <td><input type="file" name="file"></td>
            </tr>
            <tr>
                <td><input type="submit" value="上传"></td>
            </tr>
        </table>
    </form>

</center>


</body>
</html>
