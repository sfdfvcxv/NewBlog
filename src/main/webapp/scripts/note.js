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