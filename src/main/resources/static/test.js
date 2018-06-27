var stompClient = null;

$(document).ready(function myFunction() {

console.log('started test js');

var urlParams = new URLSearchParams(window.location.search);
var topicId = urlParams.get('id');
var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected111: ' + frame);
        stompClient.subscribe('/topic/user/'+topicId, function (message) {
            console.log("got message with body " + message.body);
            addNotification(JSON.parse(message.body).title, JSON.parse(message.body).description,
                        JSON.parse(message.body).id, JSON.parse(message.body).uid);
            document.getElementById("notification").style.display="block";
        });
    });
});


function addNotification(title, description, id, uid) {
    path = "window.open('/task?id="+id+"&uid="+uid+"')";
    $("#list").append("<div class='w3-margin-top w3-display-container w3-border-blue w3-border w3-light-grey w3-leftbar notifpadding'"+
            "id='id"+id+"' onclick="+path+"><span onclick='deleteNotification("+id+");event.stopPropagation();'"+
            "class='w3-button w3-display-topright'>&times;</span><p><b>"+
            title+"</b></p><p class='clip'>"+description+"</p></div>");
}

function deleteNotification(id) {
    $("#id"+id).remove();
}

function openTask(url) {
    window.open(url);
}


