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
        var parent = this;
        var socket = new SockJS('/document-service-websocket');
        this.stompClient = Stomp.over(socket);
        this.stompClient.connect({}, function (frame) {
            parent.setConnected(true);
            console.log('Connected: ' + frame);
            parent.stompClient.subscribe('/topic/scenario', function (scenario) {
                console.log(scenario);
                scenarioListManagement.addToList(scenario);
             //   showGreeting(JSON.parse(greeting.body).content);
            });
        });
    },

    disconnect: function () {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
        this.setConnected(false);
        console.log("Disconnected");
    },

    showGreeting: function (message) {
        $("#greetings").append("<tr><td>" + message + "</td></tr>");
    },

    callForScenarios() {
        $.ajax({method: "GET", url: "api/v1/scenarios"});
    }
}