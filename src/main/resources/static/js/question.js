let main = {
    init : function () {
        let _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function (){
            _this.update();
        });
        $('#btn-delete').on('click',function (){
            _this.delete();
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
            location.href = "/question";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        let data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        let id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/question/' + parseInt(id),
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('질문이 수정되었습니다.');
            location.href = "/question";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function (){
        let id = $('#id').text();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/question/'+ parseInt(id),
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function (){
            alert('질문이 삭제되었습니다.');
            location.href = "/question";
        }).fail(function (error){
            console.log(error);
        });
    }

};

main.init();