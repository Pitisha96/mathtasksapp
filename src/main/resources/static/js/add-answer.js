let numAnswer=$('.answers').length
$('#addAnswer').click(function (e){
    numAnswer+=1
    if(numAnswer<=3){
        $('.answer-box')
            .append($($('<div>',{
                class: 'row my-lg-2'
            }))
            .append($($('<div>',{
                class:'col'
            }))
            .append($($('<textarea>',{
                class:'form-control border-dark answers',
                rows:'3',
                name:'answer'+numAnswer,
                placeholder:'Answer',
                style:'resize:none;'
            })))))
    }
    if(numAnswer==3){
        $(this).hide()
    }
})

