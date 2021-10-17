let table = $('#table').get(0);

['id','name','theme','published'].forEach((idElement,idx)=>{
    $('#'+idElement).click(()=>{
        console.log(idElement+' '+idx)
        let sortedRows = Array.from(table.rows)
            .slice(1)
            .sort((rowA, rowB) => rowA.cells[idx].innerHTML > rowB.cells[idx].innerHTML ? 1 : -1);
        table.tBodies[0].append(...sortedRows);
    })
})

