/**
 * Created by Sriram on 4/29/14.
 */
var json =
    [
        {
            name: "jsmith",
            password: "passy"
        },
        {
            name: "matt",
            password: "pass"
        }

    ]
localStorage['users'] = JSON.stringify(json);
if (typeof localStorage['quiz'] === 'undefined') {
    localStorage['quiz'] = JSON.stringify([]);
}

var user;

function createLoginDisplay() {
    $(function () {
        var background = "background-color: #88C8F3;";
        $('#myLayout').w2layout({
            name: 'myLayout',
            panels: [
                { type: 'top', size: 120, style: background},
                { type: 'main', size: 400, style: background, overflow: 'auto'}

            ]
        });

        w2ui['myLayout'].load('top', 'panel/title.html');
        w2ui['myLayout'].load('main', 'panel/login.html');

    });
}
createLoginDisplay();

function runScript(e) {
    if (e.keyCode == 13) {
        loginUser();
    }
}

function loginUser() {
    var password = document.getElementById('password').value;
    var username = document.getElementById('username').value;
    var users = JSON.parse(localStorage['users']);

    for (var x = 0; x < users.length; x++) {
        if (users[x].name === username && users[x].password === password) {
            user = users[x].name;
            loggedin();
        }
        else {
            console.log("should show wrong thing")
            var $wrong = $('#wrong_password');
            $wrong.hide();
            $wrong.show(200);
        }
    }
}

function loggedin() {
    $('#home_button').show();
    $('#sign_button').show();
    w2ui['myLayout'].load('main', 'panel/option.html', 'flip-left');
}



