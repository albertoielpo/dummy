/**
 * 
 */
package test.enums;


public enum ManagementSystemParameterType {

    /**
     *
     */
    GRACE_PERIOD(Integer.class),

    /**
     *
     */
    COMPLIMENTARY_PERIOD(Integer.class),

    /**
     *
     */
    DAY_CHANGE_TIME_HOUR(Integer.class),

    /**
     *
     */
    DAY_CHANGE_TIME_MINUTE(Integer.class),

    /**
     *
     */
    WEEK_CHANGE_DAY(Integer.class),

    /**
     *
     */
    PERCENT_PRECISION(Integer.class),

    /**
     *
     */
    TAXES_EXTRA_TAX_STRING(String.class),

    /**
     *
     */
    TAXES_ADD_FLAT_TO_ZERO_AMOUNT(Boolean.class),

    /**
     *
     */
    TAXES_APPLY_TAXES_BEFORE_APPLYING_DISCOUNTS(Boolean.class),

    /**
     *
     */
    CONTRACT_AVAILABLE_FOR_JW(Boolean.class),

    /**
     *
     */
    CC_GATEWAY_PATH(String.class),

    /**
     *
     */
    LPN_DELETE_AFTER_EXIT_ENABLED(Boolean.class),

    /**
     *
     */
    LPN_DELETE_AFTER_EXIT_MINUTES(Integer.class),

    /**
     *
     */
    ACTION_REASONS_CONFIGURATION(String.class),

    /**
     *
     */
    J_PROXIMITY_DEFAULT_ISSUING_MODE(Enum.class),

	/**
	 *
     */
	CBU_VOID_AFTER_ENTRY_OR_EXIT(Enum.class),

    /**
	 *
     */
	CBU_VOID_CARD_ON_CC_REJECTED(Boolean.class),

    /**
	 *
     */
	CBU_PASSBACK_PENALTY_MINUTES(Integer.class),

    /**
	 *  drive JMS, JBL and JPS to use country fiscalization or not
     */
    MS_FISCALIZATION_POLICY(Enum.class),

    /**
     *
     */
    SEARCH_PLATE_PRECISION(Integer.class),
    
    /**
    *
    */
    PQ_BOOKING_USAGES(Integer.class),
	
    /**
    *
    */
    PQ_BOOKING_APB_ENABLED(Boolean.class),
    
    /**
     * 
     */
    LPR_PLATE_CONFIGURATION_ENABLED(Boolean.class),
    
    /**
     * 
     */
    LPR_PLATE_CONFIGURATION_PERIOD_JOB(Enum.class),
    
    /**
     * 
     */
    LPR_PLATE_CONFIGURATION_LIMIT_TIME(Enum.class);

    /**
     *
     */
    private Class type;

    /**
     * @param instanceNumber
     */
    private ManagementSystemParameterType(final Class type) {
        this.type = type;
    }

    /**
     * @return the type
     */
    public Class getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Class type) {
        this.type = type;
    }

}
