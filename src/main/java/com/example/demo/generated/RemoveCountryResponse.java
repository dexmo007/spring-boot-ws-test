
package com.example.demo.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="removed" type="{http://example.com/demo}country"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "removed"
})
@XmlRootElement(name = "removeCountryResponse", namespace = "http://example.com/demo")
public class RemoveCountryResponse {

    @XmlElement(namespace = "http://example.com/demo", required = true)
    protected Country removed;

    /**
     * Gets the value of the removed property.
     * 
     * @return
     *     possible object is
     *     {@link Country }
     *     
     */
    public Country getRemoved() {
        return removed;
    }

    /**
     * Sets the value of the removed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Country }
     *     
     */
    public void setRemoved(Country value) {
        this.removed = value;
    }

}
