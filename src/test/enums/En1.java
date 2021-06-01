
package test.enums;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * 
 * @author Paolo Bertuzzo
 * 
 */
@XmlEnum
public enum En1 {

    /**
     * 
     */
    @XmlEnumValue("enabled")
    ENABLED,
    
    /**
     * 
     */
    @XmlEnumValue("disabled")
    DISABLED,
    
    /**
     * 
     */
    @XmlEnumValue("ignore_once")
    IGNORE_ONCE;

}
