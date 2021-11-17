// Common Function ADMIN
// import :
// <script type="text/javascript" th:src='@{/admin/js/pages/ADM_product.js}' ></script>

/************************AJAX */

ADM_product = () =>{
    console.log('ADM_product');
    chrProductOrderStatistics();
};

/******************************** */
    
chrProductOrderStatistics = () =>{
    dataLabel = ["06-2021","07-2021","08-2021","09-2021","10-2021","11-2021"];
    dataValue = ["0","0","0","0","602000", "333000"];
    data = [...dataLabel,...dataValue];
    
    ChartOrderstatistics(data);
};

ChartOrderstatistics = (data) => {
    var OrderStatistics = $("#chrProductOrderStatistics").get(0).getContext("2d");
    var OrderStatistics = new Chart(OrderStatistics, {
      type: 'line',
      data: {
        labels: dataLabel,//data[0],
        datasets: [{
          label: 'total',
          data: dataValue,//data[1],
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