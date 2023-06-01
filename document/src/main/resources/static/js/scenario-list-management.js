var scenarioListManagement = {
    list: [],

    addToList: function(scenarioStr) {
        let scenario = null;
        try {
            scenario = JSON.parse(scenarioStr.body);
        }catch (e) {
            console.log("Error in parsing scenario json");
            console.log(e);
            return;
        }

      const item = this.list.findIndex(element => {
          return element.name == scenario.name;
      });
      if (item < 0) {
          this.list.push(scenario);
          this.addToUI(scenario.name, scenario.description, scenario.tip);
      }
    },

    addToUI(name, displayName, title) {
        $("#scenarioUL").append(`<li><span onclick="scenarioDrawerModalManagement.open('${name}')" title="${title}">${displayName}</span></li>`);
    },

    getSelectedScenario: function (name) {
        return this.list.find(element =>element.name === name);
    },

    getTargetComponentNameByMessageId: function (scenario, messageId) {
        let componentIndex = -1;
        for(const msg of scenario.messages) {
            if (msg.uniqueId === messageId) {
                componentIndex = msg.end;
            }
        }
        if (componentIndex <0 ) {
            alert(" problem in defining messages in scenario");
            return
        }
        return scenario.components[componentIndex];
    },

    showOpenApiURLForMessageByMessageId: function (component, scenario, message) {
        const url = componentListManagement.getServiceURL(component) + component.openApiURI + "#" + message.data.apiURI;
        window.open(url, "_blank", "");
    },

    showDocumentURL: function (component, scenario, message) {
        const url = message.data.apiURI;
        window.open(url, "_blank", "");
    },

    showTextForMessageByMessageId: function (message) {
        alert(message.data.text);
    },

    getMessage: function (scenario, messageId) {
        for(const msg of scenario.messages) {
            if (msg.uniqueId === messageId) {
                return msg;
            }
        }
        return null;
    },

    showOpenApiOfClickedMessage: function (scenarioName, messageId) {
       const scenario = this.getSelectedScenario(scenarioName);
       const componentName = this.getTargetComponentNameByMessageId(scenario, messageId);
       const component = componentListManagement.getSelectedComponent(componentName);
       const message = this.getMessage(scenario, messageId);
       switch (message.data.type) {
           case "api-link" :this.showOpenApiURLForMessageByMessageId(component, scenario, message);
             break;
           case "document-link" :this.showDocumentURL(component, scenario, message);
              break;
           case "text" : this.showTextForMessageByMessageId(message);
              break;
       }
    }
}