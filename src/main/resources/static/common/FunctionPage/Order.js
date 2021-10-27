

// Common Function PgOrdIN
// import :
// <script type="text/javascript" th:src='@{/common/FunctionPage/Order.js}' ></script>

/************************AJAX */
TestOrd = () =>{
    console.log("TestOrd");
}
// Get All Data Process
let DataProcess = [];
PgOrd_GetDataProcess = () =>{
    let response = '/api/order/GetProcess'
    $.ajax({
        type: "GET",
        url : response,
        contentType:"application/json",
        success: function (data){
            DataProcess = data;
            PgOrd_sortByIdASC(DataProcess);
        },
        error: function (e){
            Swal.fire({
                icon: 'error',
                text: "Can't load data !!!",
            })
        }
    })
};

// Sort By Id ASC
PgOrd_sortByIdASC = (list) =>{
    list.sort(function (a, b) {
        return  a.id - b.id;
    });
};
// Sort By Id DESC
PgOrd_sortByIdDESC = (list) =>{
    list.sort(function (a, b) {
        return - a.id + b.id;
    });
};
// From Process To Process
PgOrd_frmProcessToProcess = (processId) => {
let result = [];
  if(processId === 1){
    result.push(DataProcess[0], DataProcess[1]);
  }
  else if(processId === 2){
    result.push(DataProcess[1], DataProcess[2]);
  }
  else if(processId === 3){
    result.push(DataProcess[2], DataProcess[3]);
  }
  else if(processId === 4){
    result.push(DataProcess[3]);
  }
  return result;
}

/**********************************************************************ORDER - ORDER DETAIL****/
let lstOrdDtl = [];
PgOrd_getDataOrderDetailByOrderId = async (ordId) =>{
    await PgOrd_getOrderDetailByOrderId(ordId);
    return lstOrdDtl;
}
PgOrd_getOrderDetailByOrderId = async (orderId) =>{
    let url = '/api/admin/order/getLstOrderDtl?ordId='+orderId;
    await $.ajax({
            type: "GET",
            url : url,
            contentType:"application/json",
            success: function (data){
                lstOrdDtl = data;
            },
            error: function (e){
                Swal.fire({
                    icon: 'error',
                    text: "Can't load data !!!",
                })
            }
        })
}
