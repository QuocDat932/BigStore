// Common Function ADMIN
// import :
// <script type="text/javascript" th:src='@{/admin/js/pages/ADM_user.js}' ></script>

/************************AJAX */
ADM_user = async () => {
    await getUserStatisticUsingAccount();
    await getUserStatisticTypeAccount();
    await ChartOrderstatisticsById();
};
ChartOrderstatisticsById = async (userId) => {
    if (userId) {
        let frmDate = $('#frmTotalPriceUserChartDt').val();
        let toDate = $('#toTotalPriceUserChartDt').val();
        let totalMonth = (monthsDifference(frmDate, toDate) < 1) ? 1 : monthsDifference(frmDate, toDate);
        let url = "/api/admin/totalPriceById?userId=" + userId + "&totalMonth="+totalMonth;
        await $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            async: false,
            success: (Data) => {
                ChartOrderstatistics(Data);
            },
            error: (e) => {
                alert(e);
            }
        })
    } else {
        let dataLabel = ["06-2021","07-2021","08-2021","09-2021","10-2021","11-2021","12-2021"];
        let dataValue = ["0","0","0","0","0","0","0"];
        let Data = [dataLabel, dataValue]
        ChartOrderstatistics(Data);
    }
};
var OrderStatistics1;
ChartOrderstatistics = (data) => {
    if (OrderStatistics1 != undefined) {
        OrderStatistics1.destroy()
    }
    var OrderStatistics2 = $("#OrderStatistics").get(0).getContext("2d");
    OrderStatistics1 = new Chart(OrderStatistics2, {
        type: 'line',
        data: {
            labels: data[0],
            datasets: [{
                label: `Total Price`,
                data: data[1],
                backgroundColor: [
                    'rgba(255, 66, 15, 0.4)',
                    'rgba(0, 187, 221, 0.4)',
                    'rgba(255, 193, 7, 0.4)',
                    'rgba(0, 182, 122, 0.4)',
                    'rgba(153, 102, 255, 0.4)',
                    'rgba(255, 159, 64, 0.4)'
                ],
                borderColor: [
                    'rgba(255, 66, 15,1)',
                    'rgba(0, 187, 221, 1)',
                    'rgba(255, 193, 7, 1)',
                    'rgba(0, 182, 122, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1,
                fill: true, // 3: no fill
            }]
        },
        options: {
            plugins: {
                filler: {
                    propagate: true
                }
            }
        }
    });
};

getUserStatisticUsingAccount = async () => {
    let response = `/api/admin/userMgt/getUserStatisticUsingAccount`;
    await $.ajax({
        type: "GET",
        url: response,
        contentType: "application/json",
        success: function (data) {
            let label = [];
            for (let i = 0; i < 2; i++) {
                label = [...label, (data[0][i] == '1' ? 'Using' : 'Deleted')];
            };
            data[0] = label;
            chartUserStatisticUsingAccount(data);
        },
        error: function (e) {
            Swal.fire({
                icon: 'error',
                text: "Can't load data !!!",
            })
        }
    })
};
let UserStatisticUsingAccount1;
chartUserStatisticUsingAccount = (data) => {
    if (UserStatisticUsingAccount1 != undefined) {
        UserStatisticUsingAccount1.destroy();
    }
    UserStatisticUsingAccount2 = $("#UserStatisticUsingAccount").get(0).getContext("2d");
    UserStatisticUsingAccount1 = new Chart(UserStatisticUsingAccount2, {
        type: 'pie',
        data: {
            labels: data[0].reverse(),
            datasets: [{
                data: data[1].reverse(),
                backgroundColor: [
                    'rgba(255, 193, 7, 0.8)',
                    'rgba(0, 182, 122, 0.8)',
                ],
                borderColor: [
                    'rgba(255, 193, 7, 0.8)',
                    'rgba(0, 182, 122, 0.8)',
                ],
                borderWidth: 3
            }]
        },
        options: {
            responsive: true,
            animation: {
                animateScale: true,
                animateRotate: true
            }
        }
    });
};

getUserStatisticTypeAccount = async () => {
    let response = `/api/admin/userMgt/getUserStatisticTypeAccount`;
    await $.ajax({
        type: "GET",
        url: response,
        contentType: "application/json",
        success: function (data) {
            let label = [];
            for (let i = 0; i < 2; i++) {
                label = [...label, (data[0][i] == 'GG' ? 'Google Account' : 'BigStore Account')];
            };
            data[0] = label;
            chartUserStatisticTypeAccount(data);
        },
        error: function (e) {
            Swal.fire({
                icon: 'error',
                text: "Can't load data !!!",
            })
        }
    })
};
let UserStatisticTypeAccount1;
chartUserStatisticTypeAccount = (data) => {
    if (UserStatisticTypeAccount1 != undefined) {
        UserStatisticTypeAccount1.destroy();
    }
    UserStatisticTypeAccount2 = $("#UserStatisticTypeAccount").get(0).getContext("2d");
    UserStatisticTypeAccount1 = new Chart(UserStatisticTypeAccount2, {
        type: 'pie',
        data: {
            labels: data[0],
            datasets: [{
                data: data[1],
                backgroundColor: [
                    '#CAE5E8',
                    '#6EC3C9'
                ],
                borderColor: [
                    'rgba(255, 193, 7, 0.8)',
                    'rgba(0, 182, 122, 0.8)',
                ],
                borderWidth: 3
            }]
        },
        options: {
            responsive: true,
            animation: {
                animateScale: true,
                animateRotate: true
            }
        }
    });

};

function monthsDifference(firstDate, secondDate) {
    var startDay = new Date(firstDate);
    var endDay = new Date(secondDate);

    var millisBetween = startDay.getTime() - endDay.getTime();
    var days = millisBetween / (1000 * 3600 * 24);
    return Math.round(Math.abs(days / 30));
}