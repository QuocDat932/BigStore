

// Common Function ADMIN
// import :
// <script type="text/javascript" th:src='@{/admin/js/commonFuncs_Admin.js}' ></script>

/************************AJAX */
// Get All Data Process
    let DataProcess = [];
    ADM_GetDataProcess = () =>{
        let response = '/api/order/GetProcess'
        $.ajax({
            type: "GET",
            url : response,
            contentType:"application/json",
            success: function (data){
                DataProcess = data;
                ADM_sortByIdASC(DataProcess);
                console.log(DataProcess);
            },
            error: function (e){
                Swal.fire({
                    icon: 'error',
                    text: "Can't load data !!!",
                })
            }
        })
    };

    TestAdmin = (data) => {
        console.log(data,"This is a TestAdmin Function");
    };
    // Sort By Id ASC
    ADM_sortByIdASC = (list) =>{
        list.sort(function (a, b) {
            return  a.id - b.id;
        });
    };
    // Sort By Id DESC
    ADM_sortByIdDESC = (list) =>{
        list.sort(function (a, b) {
            return - a.id + b.id;
        });
    };
// From Process To Process
    ADM_frmProcessToProcess = (processId, TYPE) => {
    let result = [];
    if(TYPE === 'APPROVED'){
      if(processId === 1){
        console.log(DataProcess[0],' == ',DataProcess[1])
        result.push(DataProcess[0], DataProcess[1]);
      }
      else if(processId === 2){
        console.log(DataProcess[1],' == ',DataProcess[2])
        result.push(DataProcess[1], DataProcess[2]);
      }
      else if(processId === 3){
        console.log(DataProcess[2],' == ',DataProcess[3])
        result.push(DataProcess[2], DataProcess[3]);
      }
      else if(processId === 4){
        result.push(DataProcess[3]);
      }
      else if(processId === 5){
        result.push(DataProcess[4]); 
      }
    }
    else if(TYPE === 'REJECT'){
        result.push(DataProcess[0], DataProcess[4]);
    }
      return result;
}

/**********************************************************************ORDER - ORDER DETAIL****/
    let lstOrdDtl = [];
    ADM_getDataOrderDetailByOrderId = async (ordId) =>{
        await ADM_getOrderDetailByOrderId(ordId);
        return lstOrdDtl;
    }
    ADM_getOrderDetailByOrderId = async (orderId) =>{
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
    };
/******************************************************************************ORDER - ORDER_PROCESS BY ID ****/
    let lstOrdPrcss = [];
    ADM_GetDataOrderProcess = async (orderId) =>{
        await ADM_OrderProcess(orderId);
        return lstOrdPrcss;
    }
    ADM_OrderProcess = async (orderId) =>{
        let url = '/api/admin/order/stepProcess?ordId='+orderId;
        await $.ajax({
            type: "GET",
            url : url,
            contentType:"application/json",
            success: function (data){
                lstOrdPrcss = data;
            },
            error: function (e){
                Swal.fire({
                    icon: 'error',
                    text: "Can't load data !!!",
                })
            }
        });
    }
