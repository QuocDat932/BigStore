// Common Function ADMIN
// import :
// <script type="text/javascript" th:src='@{/admin/js/pages/ADM_product.js}' ></script>

/************************AJAX */

ADM_product = () => {
  getProductOrderStatistics();
  getProductUnitStatistics()
};

/******************************** */
searchGetStatisticsOrderInPeriodChart = async () =>{
  await getProductOrderStatistics();
  await getProductUnitStatistics();
}
getProductOrderStatistics = async () => {
  let productId = $('#txtProdId').val();
  if (productId) {
    let frmDate = $('#frmOrderChartDt').val();
    let toDate = $('#toOrderChartDt').val();
    let totalMonth = (monthsDifference(frmDate, toDate) < 1) ? 1 : monthsDifference(frmDate, toDate);
    response = `/api/admin/product/getProductOrderStatistics?prodId=${productId}&frmDate=${frmDate}&toDate=${toDate}&totalMonth=${totalMonth}`;
    await $.ajax({
      type: "GET",
      url: response,
      contentType: "application/json",
      success: function (data) {
        ChartOrderStatistics(data);
      },
      error: function (e) {
        Swal.fire({
          icon: 'error',
          text: "Can't load data !!!",
        })
      }
    })
  }
  //
};
var OrderStatistics1;
ChartOrderStatistics = (data) => {
  if (OrderStatistics1 != undefined) {
    OrderStatistics1.destroy();
  }
  OrderStatistics2 = $("#chrProductOrderStatistics").get(0).getContext("2d");
  OrderStatistics1 = new Chart(OrderStatistics2, {
    type: 'line',
    data: {
      labels: data[0],
      datasets: [{
        label: `VNĐ`,//`[(#{user.totalPriceChart})]`
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

getProductUnitStatistics = async() =>{
  let productId = $('#txtProdId').val();
  if (productId) {
    let frmDate = $('#frmOrderChartDt').val();
    let toDate = $('#toOrderChartDt').val();
    let totalMonth = (monthsDifference(frmDate, toDate) < 1) ? 1 : monthsDifference(frmDate, toDate);
    response = `/api/admin/product/getStatistiscUnitInPeriod?prodId=${productId}&frmDate=${frmDate}&toDate=${toDate}&totalMonth=${totalMonth}`;
    await $.ajax({
      type: "GET",
      url: response,
      contentType: "application/json",
      success: function (data) {
        ChartUnitStatistics(data);
      },
      error: function (e) {
        Swal.fire({
          icon: 'error',
          text: "Can't load data !!!",
        })
      }
    })
  }
}
let UnitStatistics1;
ChartUnitStatistics = (data) => {
  let lstBackgroundColor = [];
  data[0].forEach((e)=>{
      lstBackgroundColor = [...lstBackgroundColor,'rgba(0, 182, 122, 0.7)']
  });
  if (UnitStatistics1 != undefined) {
    UnitStatistics1.destroy();
  }
  orderUnitStatistics2 = $("#chrProductQuantityStatistics").get(0).getContext("2d");
  UnitStatistics1 = new Chart(orderUnitStatistics2, {
    type: 'bar',
    data: {
      labels: data[0],
      datasets: [{
        label: 'Kilogram',
        data: data[1],
        backgroundColor: lstBackgroundColor,
        borderColor: lstBackgroundColor,
        borderWidth: 1,
        fill: false
      }]
    },
    options: {
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: true
          }
        }]
      },
      legend: {
        display: false
      },
      elements: {
        point: {
          radius: 0
        }
      }
  
    }
  });
}

function monthsDifference(firstDate, secondDate) {
  var startDay = new Date(firstDate);
  var endDay = new Date(secondDate);

  var millisBetween = startDay.getTime() - endDay.getTime();
  var days = millisBetween / (1000 * 3600 * 24);
  return Math.round(Math.abs(days / 30));
}
function numberWithCommas(x) {
  return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}