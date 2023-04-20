package clinical.data;

public class memberDto {
	private Integer seq;
	private String user_email;
	private String user_name;
	private String user_pwd;
	
	public memberDto(Integer seq, String user_email, String  user_name, String user_pwd) {
		this.seq = seq;
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_pwd = user_pwd;

	}
}
