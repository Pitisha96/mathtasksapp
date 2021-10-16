let md = window.markdownit()
let stars = $('.unvoted').children()
let rating;
$(document).ready(function (){
    rating=0;
    $('#content-box').append($(md.render($('#content').val())))
    $('.rating').children().each(function (){
        if(this.classList.contains('checked'))
            rating+=1
    })
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

stars.each(handleMouseOverRating)

$('.rating').mouseout(function (){
    stars.each(function (i){
        $(this).removeClass('checked')
        if(rating>=i+0.5)
            $(this).addClass('checked')
    })

})

function handleMouseOverRating(idx,e){
    $(this).mouseover(function (){
        stars.removeClass('checked')
        for(let i=0;i<=idx;i++){
            if(!stars.get(i).classList.contains('checked')){
                stars.get(i).classList.add('checked')
            }
        }
    })
}





