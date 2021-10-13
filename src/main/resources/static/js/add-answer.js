let numAnswer=1;
$('#addAnswer').click(function (e){
    numAnswer+=1
    if(numAnswer<=3){
        $('.answer-box')
            .append($($('<div>',{
                class: 'row'
            }))
            .append($($('<div>',{
                class:'col my-lg-2'
            }))
            .append($($('<textarea>',{
                class:'form-control border-dark',
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

