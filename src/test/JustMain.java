package test;

public class JustMain {

	public static String checkVelocityEndTag(String content) {
		if (content == null || "".equals(content))
			return "";
		
		/* put a space at the end to use split for count */
		String testContent = content.toLowerCase() + " ";	
		
		int ifTags = testContent.split("#if").length - 1;
		int foreachTags = testContent.split("#foreach").length - 1;
		int macroTags = testContent.split("#macro").length - 1;
		int defineTags = testContent.split("#define").length - 1;
		int sumOpenTags = ifTags + foreachTags + macroTags + defineTags;

		int endTags = testContent.split("#end").length - 1;
		
		if (sumOpenTags == 0 && endTags > 0) {
			return content.replaceAll("#end", "").replaceAll("#END", "");
		} else {
			if (sumOpenTags == endTags) {
				return content;
			} else if (sumOpenTags > endTags) {
				//There are missing end tag - We concatenate it at the end
				//The result could be not valid
				StringBuilder res = new StringBuilder(content);
				for(int ii=0; ii<sumOpenTags-endTags; ii++)
					res.append(" #end");
				return res.toString();
			} else if (endTags > sumOpenTags) {
				//UI wrongly added an end tag
				if(content.toLowerCase().lastIndexOf("#end") == content.length() - "#end".length()) {
					return content.substring(0, content.length() - "#end".length());
				} else { 
					//There is a syntax error
					System.out.println("MailTemplateService::checkVelocityEndTag - could be a syntax error in content" + content);
					return content;
				}
			}

		}
		return content;
	}

	public static void main(String[] args) throws InterruptedException {

//		String template = "#set($card = $cards.get(0)) Dear Customer, here your congress tickets for parking $card.vpname : <br/><br/>  #if($cards.size() == 1) Card: $card.identifier <br/> #if($card.amount > -1)  Amount: $card.amount <br /> #end #if($card.additionalinfo != '') Additional info: $card.additionalinfo <br/> #end #if($card.notes != '') Notes: $card.notes <br/> #end  #end Validity from: $card.validityfrom <br/> Validity to $card.validityto <br/><br/>";
//		System.out.println(template);
//		String res = checkVelocityEndTag(template);
//		System.out.println(res);
//		System.out.println(template.equals(res));
		
		String res = String.format("%d%s", 1, "0001");
		System.out.println(res);

	}
}
