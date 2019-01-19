$('#CommentModal').on('shown.bs.modal', function (e) {
    // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
    $(this).css('display', 'block');
    var modalHeight=$(window).height() / 2 - $('#CommentModal .modal-dialog').height() / 2;
    $(this).find('.modal-dialog').css({
        'margin-top': modalHeight
    });
});
 