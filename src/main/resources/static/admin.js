$(document).ready(() => {
        getUsers();
    }
);


function getUsers() {
    fetch('http://localhost:8080/admin/users')
        .then((response) => {
            return response.json()
        })
        .then(data => {
            let tableRows = '';
            let roles = '';
            data.forEach(user => {
                roles = userRolesForPrint(user.roles);
                tableRows += '<tr>' +
                    '<td>' + user.id + '</td>' +
                    '<td>' + user.firstName + '</td>' +
                    '<td>' + user.lastName + '</td>' +
                    '<td>' + user.age + '</td>' +
                    '<td>' + user.email + '</td>' +
                    '<td>' + roles + '</td>' +
                    '<td><button type="button" class="btn btn-info" data-toggle="modal" data-target="#editModal"' +
                    ' onclick="getUserForEditModal(' + user.id + ')" id="btnEdit">Edit</button></td>' +
                    '<td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal"' +
                    ' onclick="getUserForDeleteModal(' + user.id + ')" id="btnDelete">Delete</button></td>' +
                    '</tr>';
            });
            $('#tableAllUsers tbody').empty().append(tableRows);
        });
}


$(function addRolesToForms() {
    fetch('http://localhost:8080/admin/roles')
        .then((response) => {
            return response.json()
        })
        .then(roles => {
            roles.forEach(role => {
                console.log(role)
                $('select').append(function () {
                    return $('<option>', {
                        id: role.id,
                        value: role.roleName,
                        text: role.roleName.replace('ROLE_', '')
                    })
                })
            })
        })
})


function userRolesForPrint(roles) {
    let rolesForPrint = '';
    $.each(roles, function (key, value) {
        rolesForPrint += value.roleName;
    })
    return rolesForPrint.replaceAll('ROLE_', ' ');
}

function getUserForEditModal(id) {
    $('#editForm')[0].reset();
    $('#rolesForEdit').find('option').prop('selected', false)

    fetch('http://localhost:8080/admin/' + id)
        .then((response) => {
            return response.json()
        })
        .then((user) => {

            $('#idForEdit').val(user.id)
            $('#firstNameForEdit').val(user.firstName)
            $('#lastNameForEdit').val(user.lastName)
            $('#ageForEdit').val(user.age)
            $('#emailForEdit').val(user.email)
            $('#passwordForEdit').val(user.password)
            $('#rolesForEdit').find('option').each(function () {
                let roleInOption = this.value;
                $.each(user.roles, function (key, userRole) {
                    if (userRole.roleName === roleInOption) {
                         $('#rolesForEdit option[value="' + roleInOption + '"]').prop('selected', true)
                    }
                })
            })
        });
}

function getUserForDeleteModal(id) {
    fetch('http://localhost:8080/admin/' + id)
        .then((response) => {
            return response.json()
        })
        .then((user) => {
            console.log(id)
            $('#idForDelete').val(user.id)
            $('#firstNameForDelete').val(user.firstName)
            $('#lastNameForDelete').val(user.lastName)
            $('#ageForDelete').val(user.age)
            $('#emailForDelete').val(user.email)
            $('#passwordForDelete').val(user.password)
        });
}


$('#newUserForm').submit((e) => {
    e.preventDefault();

    let $userForDB = {};
    let $roles = [];

    $('#newUserForm').find('option:selected').each(function() {
        let $role = {};
        $role['id'] = this.id;
        $role['roleName'] = this.value
        $roles.push($role)
    });

    $('#newUserForm').find('input').each(function() {
        $userForDB[this.name] = $(this).val();
    });

    $userForDB.roles = $roles

    fetch('http://localhost:8080/admin', {
        method: 'POST',
        headers: {'Content-type': 'application/json'},
        body: JSON.stringify($userForDB)
    }).then(function() {

        $('#newUserForm').find('input').val('');
        $('#newUserForm select').find('option').prop('selected', false);
        getUsers();
        $('.nav-tabs a[href="#usersTable"]').click();
    });
})


$('#editForm').submit((e) => {
    e.preventDefault();

    let $userForDB = {};
    let $roles = [];

    $('#editForm').find('option:selected').each(function() {
        let $role = {};
        $role['id'] = this.id;
        $role['roleName'] = this.value
        $roles.push($role)
    });


    $('#editForm').find('input').each(function() {
        $userForDB[this.name] = $(this).val();
    });

    $userForDB.roles = $roles

    fetch('http://localhost:8080/admin', {
        method: 'PATCH',
        headers: {'Content-type': 'application/json'},
        body: JSON.stringify($userForDB)
    }).then(function() {
        $('#editModal').hide();
        $('.modal-backdrop').hide();
        getUsers();
    })
})


$('#deleteForm').submit((e) => {
    e.preventDefault();

    fetch('http://localhost:8080/admin/' + $('#idForDelete').val(), {
        method: 'DELETE',
        headers: {'Content-type': 'text'}
    }).then(function() {
        $('#deleteModal').hide();
        $(".modal-backdrop").hide();
        getUsers();
    })
})