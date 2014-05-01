/**
 * Created by Sriram on 4/29/14.
 */
var top_ten = {
    quizname: '',
    createGrid: function () {
        top_ten.quizname = window.prompt("What is the quiz name?");
        w2ui['myLayout'].load('main', 'panel/topten/top_ten.html');
    }
}