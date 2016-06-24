function sendAjax() {
 
    // get inputs
    var gameState = new Object();
    gameState.yourClicks = $('#yourClicks').val();
    gameState.adversaryClicks = $('#adversaryClicks').val();
    gameState.stopGame = $('#stopGame').val();
    //gameState.gameTime = $('#gameTime').val();
 
    $.ajax({
        url: 'gamepage',
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(gameState),
        contentType: 'application/json',
        mimeType: 'application/json',
 
        success: function (gameState) {
            //$.each(data, function (index, gameState) {
            	//$('#yourClicks').val(gameState.yourClicks);
            	$('#adversaryClicks').val(gameState.adversaryClicks);
            	$('#stopGame').val(gameState.stopGame);
            	$('#gameTime').text(gameState.gameTime);
            	$('#message').text(gameState.message);
                if(gameState.stopGame){
                	$('#clickButton').prop('disabled',true);
                }
 
            //}); 
        },
        error:function(data,status,er) {
            alert("error: "+data+" status: "+status+" er:"+er);
        }
    });
}

function countClicks(){
	var curClicks = +$('#yourClicks').val();
	$('#yourClicks').val(curClicks + 1);
}