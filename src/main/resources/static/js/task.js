let md = window.markdownit()
let stars = $('.unvoted').children()
let rating;
$(document).ready(()=>{
    $('#content-box').append($(md.render($('#content').val())))
})

$(document).ready(handleRating)

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
            message='No. Try again'
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

function handleRating(){
    rating=0;
    $('.rating').children().each(function (){
        if(this.classList.contains('checked'))
            rating+=1
    })
}

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
stars.each(handleClickRating)

function handleClickRating(idx,e){
    $(this).click(function(){
        $.post({
            url:'../rating/'+$('meta[name="task-id"]').attr('content'),
            data:{_csrf:$('meta[name="csrf-token"]').attr('content')
                ,rating:idx+1}
        }).done(function (data){
            if(data){
                $('.unvoted').removeClass('unvoted');
                ['click','mouseover'].forEach(eventName=>{
                    stars.unbind(eventName)
                })
                $('.rating').unbind('mouseout')
                $.get({
                    url:'../rating/'+$('meta[name="task-id"]').attr('content'),
                    data:{_csrf:$('meta[name="csrf-token"]').attr('content')}
                }).done(function (data){
                    rating=data
                    handleRating();
                })
            }
        })
    })
}





