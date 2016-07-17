$( "#contentText" ).keyup(function() {
    $( "#exitMenuCheckbox" ).prop('checked', true);
});


$( "#bold" ).change(function() {
    if($('#bold').prop('checked')){
        $("#contentText").css("font-weight","bold");
    }else{
        $("#contentText").css("font-weight","normal");
    }
});

$( "#italic" ).change(function() {
    if($('#italic').prop('checked')){
        $("#contentText").css("font-style","italic");
    }else{
        $("#contentText").css("font-style","normal");
    }
});

$( "#underline" ).change(function() {
    if($('#underline').prop('checked')){
        $("#contentText").css("text-decoration","underline");
    }else{
        $("#contentText").css("text-decoration","none");
    }
});

$( "#left" ).click(function() {
    $("#contentText").css("text-align","left");
});
$( "#center" ).click(function() {
    $("#contentText").css("text-align","center");
});
$( "#right" ).click(function() {
    $("#contentText").css("text-align","right");
});
$( "#justify" ).click(function() {
    $("#contentText").css("text-align","justify");
});

$(document).on('scroll', function() {
    $( "#exitMenuCheckbox" ).prop('checked', true);
});

$( "#publish" ).mouseup(function() {
    $("#articleHeaderName").text($("h1").text());
});

$( "#save" ).mouseup(function() {
    $("#articleHeaderName").text($("h1").text()+" (Draft)");
});

$( "#delete" ).mouseup(function() {
    $("#articleHeaderName").text("Create New Article");
    $("h1").text("Simple blog editor - A great way of learning");
    $("#contentText").text("Clered");
});