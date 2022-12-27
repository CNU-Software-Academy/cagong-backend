let main = {
    init: function () {
        let _this = this;
        $('#btn-search').on('click', function () {
            _this.search();
        });
        $('#btn-sort-by-average-score').on('click', function () {
            _this.sortBy("average_score");
        });
        $('#btn-sort-by-average-price').on('click', function () {
            _this.sortBy("average_price");
        });
        $('#btn-sort-by-study-score').on('click', function () {
            _this.sortBy("study_score");
        });
        $('#btn-sort-by-distance').on('click', function () {
            _this.sortByDistance();
        });
        $('#btn-sort-by-distance-average-score').on('click', function () {
            _this.sortByDistance("average_score");
        });
        $('#btn-sort-by-distance-average-price').on('click', function () {
            _this.sortByDistance("average_price");
        });
        $('#btn-sort-by-distance-study-score').on('click', function () {
            _this.sortByDistance("study_score");
        });
    },
    search: function () {
        let keyword = $('#keyword').val();
        if (keyword === "") {
            alert("키워드를 입력해주세요.");
        } else {
            location.href = (getHostname(location.href) + "/cafes/search/keyword/" + keyword);
        }
    },
    sortBy: function (attribute) {
        let href = location.href;
        if (isExistSorting(href)) {
            href = eraseSorting(href);
        }
        if (isKeywordSearch(href)) {
            location.href = href + "?sortBy=" + attribute;
        } else {
            location.href = (getHostname(href) + "/cafes/search/keyword/all?sortBy=" + attribute);
        }
    },
    sortByDistance: function (attribute) {
        function getPositionSuccess(position) {
            let href = getHostname(location.href) + "/cafes/search/location";
            const latitude = position.coords.latitude;
            const longitude = position.coords.longitude;

            if (attribute === "average_score") {
                href = href + "/average-score";
            }
            if (attribute === "average_price") {
                href = href + "/average-price";
            }
            if (attribute === "study_score") {
                href = href + "/study-score";
            }
            location.href=href + "?longitude=" + longitude + "&latitude=" + latitude;
        }

        function getPositionError() {
            alert("위치를 찾을 수 없습니다.");
        }
        navigator.geolocation.getCurrentPosition(getPositionSuccess, getPositionError);
    }
};
main.init();

function isKeywordSearch(href) {
    return href.indexOf("/search/keyword") !== -1;
}

function isExistSorting(href) {
    return href.indexOf("?sortBy=") !== -1;
}

function getHostname(href) {
    let cafesIndex = href.indexOf("/cafes");
    return href.substring(0, cafesIndex);
}

function eraseSorting(href) {
    let cafesIndex = href.indexOf("?sortBy=");
    return href.substring(0, cafesIndex);
}
