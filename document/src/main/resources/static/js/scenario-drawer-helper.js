const scenarioDrawerHelper = {
    charLength: 8,
    getComponentRectWidth: function (scenario){
        var max = 0;
        for (var i=0;i<scenario.components.length; i++) {
            const c = scenario.components[i];
            max = max < c.length ? c.length : max;
        };
        return max * this.charLength;
    }
}