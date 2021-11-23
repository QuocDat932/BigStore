// Common Function ADMIN
// import :
// <script type="text/javascript" th:src='@{/admin/js/pages/ADM_user.js}' ></script>

/************************AJAX */
ADM_user = async () => {
    await getUserStatisticUsingAccount();
    await getUserStatisticTypeAccount();
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
            for(let i = 0 ; i < 2; i++){
                label = [... label,(data[0][i] == 'GG' ? 'Google Account' : 'BigStore Account')];
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

}