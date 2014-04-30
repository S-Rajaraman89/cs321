/**
 * Created by Sriram on 4/29/14.
 */
var take_quiz = {
    openQuiz: function () {
        var quizName = window.prompt("What is the quiz ID");
        var quizJson = JSON.parse(localStorage['quiz']);
        console.log(quizJson);
        var indexOfQuiz = 0;
        for (var x = 0; x < quizJson.length; x++) {
            for (var key in quizJson[x]) {
                if (key === quizName) {
                    indexOfQuiz = x;
                    break;
                }
            }
        }
        console.log("the quiz object is " + quizJson[indexOfQuiz]);
        var quiz = quizJson[indexOfQuiz];
        console.log(quiz[quizName][0].type);

        if (quiz[quizName][0].type === 'coding') {
            alert('coding question');
        }
        else if (quiz[quizName][0].type === 'frq') {
            take_quiz.takeFRQ(quiz);
        }
        else if (quiz[quizName][0].type === 'mc') {
            alert('mc question');
        }
    },

    takeFRQ: function (quiz) {
        w2ui['myLayout'].load('main', 'panel/takequiz/take_quiz_frq.html');
        take_quiz.frq = quiz;
    },
    frq: 0

};