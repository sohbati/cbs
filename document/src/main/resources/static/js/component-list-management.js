const componentListManagement = {
    list: [],

    addToList: function(componentStr) {
        const c = JSON.parse(componentStr.body);
        var parent = this;
        if (c && c.components && c.components.length > 0) {
            c.components.forEach(function (component) {
            const item = parent.list.findIndex(element => {
                return element.name == component.name;
            });
            if (item < 0) {
                parent.list.push(component);
                //parent.addToUI(component.name, component.description, component.tip);
            }
            });
        }
    },
    

    getSelectedComponent: function (name) {
        return this.list.find(element =>element.name === name);
    },
}