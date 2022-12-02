package com.sina.cbs.document.scenario.data;


public class Scenario {
    private String name;
    private String tip;
    private String description;
    private String[] components;
    private Massage[] massages;

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getTip() { return tip; }
    public void setTip(String value) { this.tip = value; }

    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }

    public String[] getComponents() { return components; }
    public void setComponents(String[] value) { this.components = value; }

    public Massage[] getMassages() { return massages; }
    public void setMassages(Massage[] value) { this.massages = value; }
}
