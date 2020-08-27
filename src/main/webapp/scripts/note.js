//封装了note的操作
function loadBookNotes() {
    //显示切换
    $("#pc_part_6").hide();
    $("#pc_part_4").hide();
    $("#pc_part_7").hide();
    $("#pc_part_8").hide();
    $("#pc_part_2").show();
    //清空原有的noteli列表
    $("#note_ul li").remove();
    //清除选中效果
    $("#book_ul a").removeClass("checked");
    //1.获取请求参数
    var bookId = $(this).data("bookId");
    $(this).find("a").addClass("checked");
    //2.参数格式校验
    //3.发送Ajax
    $.ajax({
        url: base_path + "/note/loadnotes.do",
        type: "post",
        data: {"bookId": bookId},
        dataType: "json",
        success: function (result) {
            //获取服务器端传过来的笔记集合
            var notes = result.data;
            //循环生成笔记li
            for (var i = 0; i < notes.length; i++) {
                //获取笔记ID和笔记标题
                var noteId = notes[i].cn_note_id;
                var noteTitle = notes[i].cn_note_title;
                //创建一个笔记li元素
                var NoteTypeId = notes[i].cn_note_type_id;
                createNoteLi(noteId, noteTitle);
                if (NoteTypeId=="2"){
                    var $li = $("#note_ul li:last");
                    var img='<i class="fa fa-sitemap"></i>';
                    $li.find(".btn_slide_down").before(img);
                }
            }
        },
        error: function () {
            alert("笔记加载异常");
        }
    });
}

//创建笔记li元素
function createNoteLi(noteId, noteTitle) {
    <!-- 动态生成笔记li元素 -->
    var sli = '';
    sli += '<li class="online">';
    sli += '<a>';
    sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' + noteTitle + '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
    sli += ' </a>';
    sli += '<div class="note_menu" tabindex="-1"> ';
    sli += ' <dl>';
    sli += '<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
    sli += '<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
    sli += '<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
    sli += '</dl>';
    sli += '</div>';
    sli += '</li>';
    //将noteId绑定对应的li上
    var $li = $(sli);
    $li.data("noteId", noteId);
    //将li元素添加到笔记列表中
    $("#note_ul").append($li);
}

//根据笔记ID加载笔记信息
function loadNote() {
    //设置笔记选中效果
    $("#note_ul a").removeClass("checked");
    $(this).find("a").addClass("checked");
    //1.获取请求参数
    var noteId = $(this).data("noteId");
    //2.参数格式校验
    //3.发送Ajax
    $.ajax({
        url: base_path + "/note/load.do",
        type: "post",
        data: {"noteId": noteId},
        dataType: "json",
        success: function (result) {
            if (result.status == 0) {
                //获取标题
                var title = result.data.cn_note_title;
                //获取笔记内容
                var body = result.data.cn_note_body;
                //设置到编辑区域
                $("#input_note_title").val(title);
                um.setContent(body);
                $("#pc_part_5").hide();
                $("#pc_part_3").show();
            }
        },
        error: function () {
            alert("加载笔记异常");
        }
    });
}

//"保存笔记"按钮的处理
function updateNote() {
    //1.获取请求参数
    var title = $("#input_note_title").val();
    var body = um.getContent();
    var $li = $("#note_ul a.checked").parent();
    var noteId = $li.data("noteId");
    //清空原有错误提示
    $("#note_title_span").html("");
    //2.参数格式校验
    if (title == "") {
        $("#note_title_span").html("<font color='red'>标题不能为空</font>");
    } else if ($li.length == 0) {
        alert("请选择要保存的笔记");
    } else {
        //3.发Ajax请求
        $.ajax({
            url: base_path + "/note/update.do",
            type: "post",
            data: {"noteId": noteId, "title": title, "body": body},
            dataType: "json",
            success: function (result) {
                if (result.status == 0) {
                    //更新列表li中的标题
                    var sli = "";
                    sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' + title + '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
                    //将选中li元素的a内容替换
                    $li.find("a").html(sli);
                    alert(result.msg);
                }
            },
            error: function () {
                alert("保存笔记异常");
            }
        });
    }

}

//创建笔记
function addNote() {
    //获取请求参数
    var userId = getCookie("uid");
    var noteTitle = $("#input_note").val().trim();
    var $li = $("#book_ul a.checked").parent();
    var bookId = $li.data("bookId");
    //参数格式校验
    var ok = true;
    if (userId == null) {
        ok = false;
        window.location.href = "log_in.html";
    }
    if (noteTitle == "") {
        ok = false;
        $("#note_span").html("笔记名称为空");
    }
    //发送Ajax
    if (ok) {
        $.ajax({
            url: base_path + "/note/add.do",
            type: "post",
            data: {"userId": userId, "bookId": bookId, "noteTitle": noteTitle},
            dataType: "json",
            success: function (result) {
                if (result.status == 0) {
                    //关闭创建笔记弹窗
                    closeAlertWindow();
                    var noteId = result.data;
                    //生成笔记li
                    createNoteLi(noteId, noteTitle);
                    //提示成功
                    alert(result.msg);
                }
            },
            error: function () {
                alert("创建笔记异常");
            }
        });
    }
}

//显示笔记菜单
function popNoteMenu() {
    //隐藏所有笔记菜单
    $("#note_ul div").hide();
    //获取笔记菜单
    var $menus = $(this).parent().next();
    $menus.slideDown(1000);
    //点击下拉设置对应的笔记为选中状态
    $("#note_ul a").removeClass("checked");
    $(this).parent().addClass("checked");
    return false;
}

//隐藏笔记菜单
function hideNoteMenu() {
    $("#note_ul div").hide();
}
//笔记删除
function deleteNote() {
    var $li = $("#note_ul a.checked").parent();
    var noteId = $li.data("noteId");
    $.ajax({
        url: base_path+"/note/delete.do",
        type: 'post',
        data: {"noteId": noteId},
        dataType: 'json',
        success: function (result) {
            closeAlertWindow();
            $li.remove();
            alert(result.msg);
        },
        error:function (result) {
            alert("删除笔记异常");
        }
    })
}
//笔记移动
function moveNote() {
    var $li = $('#note_ul a.checked').parent();
    var noteId = $li.data("noteId");
    var bookId = $('#moveSelect').val();
    $.ajax({
        url:base_path+'/note/move.do',
        type:'post',
        data: {"noteId":noteId,'bookId': bookId},
        dataType:"json",
        success(result){
            closeAlertWindow();
            $li.remove();
            alert(result.msg);
        },
        error(result){
            alert("移动笔记失败")
        }
    })
}
//笔记分享
function shareNote() {
    // var noteId = $('#note_ul a.checked').parent().data('noteId');
    var $li = $(this).parents('li');
    var noteid =$li.data('noteId');
    $.ajax({
        url:base_path+'/note/share.do',
        type:'post',
        data:{'noteId':noteid},
        dataType:'json',
        success:function(result){
            if (result.status==0){
                var i='<i class="fa fa-sitemap"></i>';
                $li.find(".btn_slide_down").before(i)
            }
            alert(result.msg);
        },
        error:function(result){
            alert('分享异常')
        }
    })

}
//搜索分享笔记
function searchSharePage(keyword,page) {
//获取请求参数
//     格式校验
//    发送ajax
    $.ajax({
        url: base_path+'/note/searchShare.do',
        type:'post',
        data:{
            'keyword': keyword,
            'page':page
        },
        dataType:'json',
        success:function (result) {
            if (result.status==0){
                //获取列表
                var share = result.data;
                //循环生成列表
                for (var i=0;i<share.length;i++){
                    var shareId=share[i].cn_share_id;
                    var shareTitle=share[i].cn_share_title;
                    var sli = '';
                    sli += '<li class="online">';
                    sli += '<a>';
                    sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' + shareTitle + '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-star"></i></button>';
                    sli += ' </a>';
                    sli += '<div class="note_menu" tabindex="-1"> ';
                    sli += '</div>';
                    sli += '</li>';
                    //将shareId绑定对应的li上
                    var $li = $(sli);
                    $li.data("shareId", shareId);
                    //将li元素添加到笔记列表中
                    $("#pc_part_6 ul").append($li);
                }
            }
        },
        error:function (result) {
            alert('搜索分享笔记异常')
        }
    })
}
//搜索分享笔记详情
function shareBody() {
    $("li a").removeClass("checked")
    //设置选中效果
    $(this).find("a").addClass("checked")
    //请求参数
    var shareId = $(this).data("shareId");
    //参数校验
    //ajax
    $.ajax({
        url:base_path+"/share/ShareBody.do",
        type:"post",
        data:{
            "shareId":shareId
        },
        dataType:"json",
        success:function (result) {
            //获取信息
            var cnShareTitle = result.data.cn_share_title;
            var cnShareBody = result.data.cn_share_body;
            //设置信息
            $("#noput_note_title").html(cnShareTitle);
            $("#noput_note_title").next().html(cnShareBody);
            $("#pc_part_3").hide();
            $("#pc_part_5").show();
        },
        error:function (result) {
            alert("加载笔记分享异常")
        }
    })
}