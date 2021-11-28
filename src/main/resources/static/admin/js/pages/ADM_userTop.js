// Common Function ADMIN
// import :
// <script type="text/javascript" th:src='@{/admin/js/pages/ADM_userTop.js}' ></script>
let imgUrl = 'datbq.jpg';
let dataChart = [];
ADM_userTop = () => {
    getStatisticTopUserTotalPriceByUserIdInPeriod();
};

getStatisticTopUserTotalPriceByUserIdInPeriod = async () => {
    let response = `/api/admin/userMgt/getStatisticTopUserTotalPriceByUserIdInPeriod`;
    await $.ajax({
        type: "GET",
        url: response,
        contentType: "application/json",
        success: function (data) {
            formatData(data);
            chartTotalPriceUser(dataChart);
        },
        error: function (e) {
            Swal.fire({
                icon: 'error',
                text: "Can't load data !!!",
            })
        }
    })
};

sortByTotalDesc = (list) => {
    list.sort(function (a, b) {
        return b.total - a.total;
    });
};

formatData = (listData) => {
    let maxtoTal = $('#maxValue').val();
    sortByTotalDesc(listData);
    listData.forEach((e) => {
        if (e.total >= 110000) {
            dataChart = [...dataChart, {
                name: e.string1,
                steps: e.total,
                pictureSettings: {
                    src: `/admin/images/faces/${e.string2}`
                }
            }]
        }
    });
};

