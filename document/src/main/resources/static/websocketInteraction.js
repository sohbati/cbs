var websocketInteraction = {
    stompClient: null,

    setConnected: function (connected) {
        $("#connect").prop("disabled", connected);
        $("#disconnect").prop("disabled", !connected);
        if (connected) {
            $("#conversation").show();
        }
        else {
            $("#conversation").hide();
        }
        $("#greetings").html("");
    },

    connect: function () {
        var socket = new SockJS('/gs-guide-websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/greetings', function (greeting) {
                console.log(greeting);
                debugger;
                showGreeting(JSON.parse(greeting.body).content);
            });
        });
    },

    disconnect: function () {
        if (stompClient !== null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    },

    showGreeting: function (message) {
        $("#greetings").append("<tr><td>" + message + "</td></tr>");
    }

}