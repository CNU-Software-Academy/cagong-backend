let main = {
    init : function () {
        let _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        let data = {
            name: $('#name').val(),
            address: $('#address').val(),
            zone: $('#zone').val(),
            longitude: $('#longitude').val(),
            latitude: $('#latitude').val(),
            averagePrice: $('#averagePrice').val(),
            averageScore: $('#averageScore').val(),
            studyScore: $('#studyScore').val(),
            seatSelectionCount: $('#seatSelectionCount').val(),
            concentrationSelectionCount: $('#concentrationSelectionCount').val(),
            totalReviewCount: $('#totalReviewCount').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/cafe',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('카페가 추가되었습니다.');
            location.href = "/admin/cafes";
        }).fail(function (error) {
            console.log(JSON.stringify(error));
        });
    },
};
main.init();
