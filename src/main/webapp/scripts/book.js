//封装了笔记本的操作
//加载用户笔记本列表
function loadUserBooks() {
    //1.获取请求参数
    var userId = getCookie("uid");
    //2.参数格式校验
    if (userId == null) {
        window.location.href = "log_in.html";
    } else {
        //3.发送Ajax
        $.ajax({
            url: base_path + "/book/loadbooks.do",
            type: "post",
            data: {"userId": userId},
            dataType: "json",
            success: function (result) {
                //TODO处理回调
                //获取返回笔记本集合
                var books = result.data;
                //循环生成列表元素
                for (var i = 0; i < books.length; i++) {
                    //获取笔记本id
                    var bookId = books[i].cn_notebook_id;
                    //获取笔记名称
                    var bookName = books[i].cn_notebook_name;
                    //创建笔记本列表li
                    createBookLi(bookId, bookName);
                }
            },
            error: function () {
                alert("查询笔记本异常");
            }

        });
    }

}

//创建笔记本列表li元素
function createBookLi(bookId, bookName) {
    // <li class="online">
    //     <a  class='checked'>
    //     <i class="fa fa-book" title="online" rel="tooltip-bottom">
    //     </i> 默认笔记本</a></li>
    //构建列表li元素
    var sli = '';
    sli += '<li class="online">';
    sli += ' <a>';
    sli += '<i class="fa fa-book" title="online" rel="tooltip-bottom">';
    sli += '</i>' + bookName + '</a></li>'
    //将bookId绑定到li元素上
    //将JS对象转换成jQuery对象
    var $li = $(sli);
    $li.data("bookId", bookId);
    //将li元素添加到ul列表中
    $("#book_ul").append($li);
}

//创建笔记本
function addBook() {
    //1.获取请求参数
    var userId = getCookie("uid");
    var name = $("#input_notebook").val().trim();
    //2.参数格式校验
    var ok = true;
    if (userId == null) {
        ok = false;
        window.location.href = "log_in.html";
    }
    if (name == "") {
        ok = false;
        $("#notebook_span").html("笔记名为空");
    }
    //3.发送Ajax
    if (ok) {
        $.ajax({
            url: base_path + "/book/add.do",
            type: "post",
            data: {"userId": userId, "name": name},
            dataType: "json",
            success: function (result) {
                if(result.status == 0){
                    //关闭新建笔记本对话框
                    closeAlertWindow();
                    var bookId = result.data.cn_notebook_id;
                    var bookName = result.data.cn_notebook_name;
                    //将生成的笔记本li
                    createBookLi(bookId,bookName);
                    //提示成功
                    alert(result.msg);
                }
            },
            error: function () {
                alert("添加笔记本异常");
            }
        });


    }
}