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

        if(data.name === "") {
            alert("카페 이름을 입력해주세요.")
        } else if(data.address === "") {
            alert("카페 주소를 입력해주세요.")
        } else if(data.averageScore < 0 || data.averageScore > 5) {
            alert("Average Score의 범위는 0부터 5까지 입니다.")
        } else if(data.studyScore < 0 || data.studyScore > 1) {
            alert("Study Score의 범위는 0부터 1까지 입니다.")
        } else {
            $.ajax({
                type: 'POST',
                url: '/api/cafe',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('카페가 추가되었습니다.');
                location.href = "/cafes";
            }).fail(function (error) {
                console.log(JSON.stringify(error));
            });
        }
    },
};
main.init();
