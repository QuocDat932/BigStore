// Common Function ADMIN
// import :
// <script type="text/javascript" th:src='@{/admin/js/pages/ADM_order.js}' ></script>

/************************AJAX */
ADM_order = () => {
    //getStatisticsOrderInPeriodChart();
};

var getStatisticsOrderInPeriodChart1;

getStatisticsOrderInPeriodChart = async (frmDt, toDt) => {
    let response = `/api/admin/order/getStatisticsOrderInPeriod?frmDt=${frmDt}&toDt=${toDt}`;
    await $.ajax({
        type: "GET",
        url: response,
        contentType: "application/json",
        success: function (data) {
            if (getStatisticsOrderInPeriodChart1 != undefined) {
                getStatisticsOrderInPeriodChart1.destroy();
            }
            getStatisticsOrderInPeriodChart2 = $("#getStatisticsOrderInPeriodChart").get(0).getContext("2d");

            getStatisticsOrderInPeriodChart1 = new Chart(getStatisticsOrderInPeriodChart2, {
                type: 'doughnut',
                data: {
                    labels: data[0],
                    datasets: [{
                        data: data[1],
                        backgroundColor: [
                            '#CAE5E8',//'rgba(255, 66, 15, 0.8)',
                            '#6EC3C9',//'rgba(0, 187, 221, 0.8)',
                            '#FEF889',//'rgba(255, 193, 7, 0.8)',
                            '#5BBD2B',//'rgba(0, 182, 122, 0.8)',
                            '#ff777c',//'rgba(153, 102, 255, 0.8)',
                            'rgba(255, 66, 15, 0.8)'
                        ],
                        borderColor: [
                            '#CAE5E8',//'rgba(255, 66, 15,1)',
                            '#6EC3C9',//'rgba(0, 187, 221, 1)',
                            '#FEF889',//'rgba(255, 193, 7, 1)',
                            '#5BBD2B',//'rgba(0, 182, 122, 1)',
                            '#ff777c',//'rgba(153, 102, 255, 1)',
                            '#F8F8FF'//'rgba(255, 159, 64, 1)'
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
        },
        error: function (e) {
            Swal.fire({
                icon: 'error',
                text: "Can't load data !!!",
            })
        }
    })
}