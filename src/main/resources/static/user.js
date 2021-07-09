$(document).ready(() => {
        getUser();
    }
);


function getUser() {
    fetch('http://localhost:8080/authorizedUser')
        .then((response) => {
            return response.json()
        })
        .then((authorizedUser) => {
            let tableRows = '';
            let rolesAuthorizedUser = userRolesForPrint(authorizedUser.roles);
            tableRows += '<tr>' +
                '<td>' + authorizedUser.id + '</td>' +
                '<td>' + authorizedUser.firstName + '</td>' +
                '<td>' + authorizedUser.lastName + '</td>' +
                '<td>' + authorizedUser.age + '</td>' +
                '<td>' + authorizedUser.email + '</td>' +
                '<td>' + rolesAuthorizedUser + '</td>' +
                '</tr>';
            $('#tableUser tbody').empty().append(tableRows);
            $('#userNameForHeader').text(authorizedUser.email);
            $('#userRolesForHeader').text(' with roles: ' + rolesAuthorizedUser);
        });
}

function userRolesForPrint(roles) {
    let rolesForPrint = '';
    $.each(roles, function (key, value) {
        rolesForPrint += (value.roleName + ' ');
    })
    return rolesForPrint;
}
