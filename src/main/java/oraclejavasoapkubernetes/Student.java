//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.08.27 at 03:44:00 PM BRT 
//


package oraclejavasoapkubernetes;

public class Student {

    private String name;
    private String lastName;

    public Student(String name, String lastname)
    {
    	super();
    	this.name = name;
    	this.lastName = lastname;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String value) {
        this.lastName = value;
    }

}
