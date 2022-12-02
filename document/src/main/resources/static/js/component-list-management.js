const componentListManagement = {
    list: [],

    addToList: function(componentStr) {
        const components = JSON.parse(componentStr.body);
        if (components && components.length > 0) {
            components.forEach(function (component) {
            const item = this.list.findIndex(element => {
                return element.name == component.name;
            });
            if (item < 0) {
                this.list.push(component);
                this.addToUI(component.name, component.description, component.tip);
            }
            });
        }
    },
    

    getSelectedComponent: function (name) {
        return this.list.find(element =>element.name === name);
    },
}