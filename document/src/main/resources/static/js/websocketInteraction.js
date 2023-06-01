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
            //console.log('Connected: ' + frame);
            parent.stompClient.subscribe('/topic/scenario', function (scenario) {
                //console.log(scenario);
                scenarioListManagement.addToList(scenario);
            });
            parent.stompClient.subscribe('/topic/components', function (components) {
               // console.log(components);
                componentListManagement.addToList(components);
            });
        });
    },

    disconnect: function () {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
        this.setConnected(false);
        //console.log("Disconnected");
    },

    callForScenarios() {
        $.ajax({method: "GET", url: "api/v1/scenarios"});
    }
}