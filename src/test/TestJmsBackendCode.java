package test;
/**
 * This class is a snippet code to test code with JMS context
 * @author Alberto Ielpo
 */
public class TestJmsBackendCode {

	/* Add at the end of:
	 * com.faac.janus.service.tax.TaxService    
	 *	@GET
	 *	@Path("/test/donotcommit")
	 *	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }) 
	 *	@NeedPermissions(permissions = { Permission.READ_ACCESS_POLICY })
	 *	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	 *	Response testDoNotCommitThisMethod(            
	 *		@QueryParam("id")
	 *		final Integer id);
	 *	
	 *	====================================================================
	 *	com.faac.janus.service.tax.TaxServiceImpl
	 *	@Override
	 *	public Response testDoNotCommitThisMethod(Integer id) {
	 *		// custom code to execute
	 *	 	return new Response();
	 *	}
	 */
		
}
