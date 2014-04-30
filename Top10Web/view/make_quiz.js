/**
 * Created by Sriram on 4/29/14.
 */

var make_quiz = {
    createQuestionOptions: function () {
        w2ui['myLayout'].load('main', 'panel/makequiz/make_quiz_option.html');
    },
    make_coding: function () {
        w2ui['myLayout'].load('main', 'panel/makequiz/make_coding_question.html');
    },
    make_frq: function () {
        w2ui['myLayout'].load('main', 'panel/makequiz/make_fr_question.html');
    },
    make_mc: function () {
        w2ui['myLayout'].load('main', 'panel/makequiz/make_mc_question.html');
    },
    exit: function () {
        w2ui['myLayout'].load('main', 'panel/option.html');
    }
}
