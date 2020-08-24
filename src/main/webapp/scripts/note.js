//封装了note的操作
function loadBookNotes() {
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
        url:base_path+"/note/loadnotes.do",
        type:"post",
        data:{"bookId":bookId},
        dataType:"json",
        success:function (result) {
            //获取服务器端传过来的笔记集合
            var notes = result.data;
            //循环生成笔记li
            for(var i = 0; i < notes.length;i++){
                //获取笔记ID和笔记标题
                var noteId = notes[i].cn_note_id;
                var noteTitle = notes[i].cn_note_title;
                //创建一个笔记li元素
                createNoteLi(noteId,noteTitle);
            }
        },
        error:function () {
            alert("笔记加载异常");
        }
    });
}
//创建笔记li元素
function createNoteLi(noteId,noteTitle) {
    <!-- 动态生成笔记li元素 -->
    var sli = '';
    sli +='<li class="online">';
    sli +='<a>';
    sli +='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
    sli +=' </a>';
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
    $li.data("noteId",noteId);
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
        url:base_path+"/note/load.do",
        type:"post",
        data:{"noteId":noteId},
        dataType:"json",
        success:function (result) {
            if(result.status == 0){
                //获取标题
                var title = result.data.cn_note_title;
                //获取笔记内容
                var body = result.data.cn_note_body;
                //设置到编辑区域
                $("#input_note_title").val(title);
                um.setContent(body);
            }
        },
        error:function () {
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
    if(title == ""){
        $("#note_title_span").html("<font color='red'>标题不能为空</font>");
    }else if($li.length==0){
        alert("请选择要保存的笔记");
    }else{
        //3.发Ajax请求
        $.ajax({
            url:base_path+"/note/update.do",
            type:"post",
            data:{"noteId":noteId,"title":title,"body":body},
            dataType:"json",
            success:function (result) {
                if(result.status == 0){
                    //更新列表li中的标题
                    var sli = "";
                    sli +='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+title+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
                    //将选中li元素的a内容替换
                    $li.find("a").html(sli);
                    alert(result.msg);
                }
            },
            error:function () {
                alert("保存笔记异常");
            }
        });
    }

}