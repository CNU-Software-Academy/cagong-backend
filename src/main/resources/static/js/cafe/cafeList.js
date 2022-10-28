let main = {
    init : function () {
        let _this = this;
        $('#btn-search').on('click', function () {
            _this.search();
        });
    },
    search : function () {
        let keyword = $('#keyword').val();
        if(keyword === "") {
            alert("키워드를 입력해주세요.");
        } else {
            location.href = (location.href + "/search/keyword/" + keyword);
        }
    },
};
main.init();