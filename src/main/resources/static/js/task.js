let md = window.markdownit()

$(document).ready(function (){
    console.log('value')
    $('#content-box').append($(md.render($('#content').val())))
})

$('.img-button').click(function (){
    $('#image-show').attr('src',$(this).attr('src'))
})

$('#btn-send').click(function (){
    let type;
    let message;
    $('#answer-alert').empty()
    $.get({
        url:'/checkAnswer/'+$('meta[name="task-id"]').attr('content'),
        data: {answer:$('#answer').val()}
    }).done(function (data){
        if(data){
            type='info'
            message='OK. Try the next task'
            $('#answer').hide()
            $('#btn-send').hide()
        }else{
            type='danger'
            message='The answer is not correct. Try again'
        }
        $('#answer-alert')
            .append($(
                '<div class="alert alert-' + type +
                ' alert-dismissible" role="alert">' + message +
                '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>'
            ))
    })
})