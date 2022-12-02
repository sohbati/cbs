var scenarioListManagement = {
    list: [],

    addToList: function(scenarioStr) {
      const scenario = JSON.parse(scenarioStr.body);
      const item = this.list.findIndex(element => {
          return element.name == scenario.name;
      });
      if (item < 0) {
          this.list.push(scenario);
          this.addToUI(scenario.name, scenario.description, scenario.tip);
      }
    },

    addToUI(name, displayName, title) {
        $("#scenarioUL").append(`<li><span onclick="modalManagement.open('${name}')" title="${title}">${displayName}</span></li>`);
    },

    getSelectedScenario: function (name) {
        return this.list.find(element =>element.name === name);
    },
}