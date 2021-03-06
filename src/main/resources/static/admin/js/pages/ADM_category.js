// Common Function ADMIN
// import :
// <script type="text/javascript" th:src='@{/admin/js/pages/ADM_category.js}' ></script>

/************************AJAX */
let totalQuantityOfProduct = 0;
ADM_category = async () => {
    await getStatisticQuantityOfProductByType();
    getBalanceFluctuationsOverTime();
};

getStatisticQuantityOfProductByType = async () => {
    let response = `/api/admin/product/getStatisticQuantityOfProductByType`;
    await $.ajax({
        type: "GET",
        url: response,
        contentType: "application/json",
        success: function (data) {
            data[1].forEach((e) => {
                totalQuantityOfProduct += parseInt(e);
            });
            setHeaderInformation(data);
            getStatisticQuantityOfProductByTypeChart(data);
        },
        error: function (e) {
            Swal.fire({
                icon: 'error',
                text: "Can't load data !!!",
            })
        }
    })
};

setHeaderInformation = (data) => {
    let PercentQuantity_vegetable = Math.round((parseInt(data[1][3]) * 100) / totalQuantityOfProduct);
    let PercentQuantity_fastFood = Math.round((parseInt(data[1][2]) * 100) / totalQuantityOfProduct);
    let PercentQuantity_houseWare = Math.round((parseInt(data[1][1]) * 100) / totalQuantityOfProduct);
    let PercentQuantity_boxingPackingItem = Math.round((parseInt(data[1][0]) * 100) / totalQuantityOfProduct);
    // Set Quantity & Percentage Of product
    $('#Quantity_vegetable').text(data[1][3]);
    $('#PercentQuantity_vegetable').text(PercentQuantity_vegetable + " % ");

    $('#Quantity_fastFood').text(data[1][2]);
    $('#PercentQuantity_fastFood').text(PercentQuantity_fastFood + " % ");

    $('#Quantity_houseWare').text(data[1][1]);
    $('#PercentQuantity_houseWare').text(PercentQuantity_houseWare + " % ");

    $('#Quantity_boxingPackingItem').text(data[1][0]);
    $('#PercentQuantity_boxingPackingItem').text(PercentQuantity_boxingPackingItem + " % ");
};

let totalUser1;
getStatisticQuantityOfProductByTypeChart = (data) => {
    let percentageProduct = [];
    data[1].forEach((e) => {
        percentageProduct.push(Math.round(e * 100 / totalQuantityOfProduct));
    });

    if (totalUser1 != undefined) {
        totalUser1.destroy();
    }
    totalUser2 = $("#chartStatisticQuantityOfProductByType").get(0).getContext("2d");
    totalUser1 = new Chart(totalUser2, {
        type: 'pie',
        data: {
            labels: data[0].reverse(),
            datasets: [{
                data: percentageProduct.reverse(),
                backgroundColor: [
                    'rgba(0, 182, 122, 0.8)',
                    'rgba(255, 66, 15, 0.8)',

                    'rgba(255, 193, 7, 0.8)',
                    '#483D8B',

                    'rgba(153, 102, 255, 0.8)',
                    'rgba(255, 159, 64, 0.8)'
                ],
                borderColor: [
                    'rgba(0, 182, 122, 1)',
                    'rgba(255, 66, 15,1)',

                    'rgba(255, 193, 7, 1)',
                    '#483D8B',

                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
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

let BalanceFluctuationsOverTime1;
getBalanceFluctuationsOverTime = () => {
    if (BalanceFluctuationsOverTime1 != undefined) {
        BalanceFluctuationsOverTime1.destroy();
    }
    BalanceFluctuationsOverTime2 = $("#BalanceFluctuationsOverTime").get(0).getContext("2d");
    BalanceFluctuationsOverTime1 = new Chart(BalanceFluctuationsOverTime2, {
        type: 'line',
        data: {
            labels: ["January", "February", "March", "March", "April", "May", "June", "August", "September", "September", "September", "September"],
            datasets: [
                {
                    label: 'Actual Quantity',
                    data: [150, 200, 500, 350, 450, 120, 250, 244, 250, 263, 158, 620],
                    fill: false,
                    borderColor: '#CDC9C9',
                    backgroundColor: '#FFFF00',
                },
                {
                    label: 'Estimate Quantity',
                    data: [220, 500, 250, 450, 400, 320, 650, 344, 350, 63, 358, 520],
                    fill: false,
                    borderColor: '#97FFFF',
                    backgroundColor: '#FF0000',
                }
            ]
        },
        options: {
            interaction: {
                intersect: false,
                mode: 'index',
            },
            plugins: {
                tooltip: {
                    callbacks: {
                    }
                }
            }
        }
    });
}

