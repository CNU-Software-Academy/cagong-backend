let main = {
    init : function () {
        let _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function (){
            _this.update();
        });
    },
    save : function () {
        let data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/question',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('질문이 등록되었습니다.');
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function (){
        let data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        let id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/question/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('질문이 수정되었습니다.');
            location.herf = "/notice";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();