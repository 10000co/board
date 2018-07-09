package global.sesoc.test7.vo;

public class Reply {
	private int replynum;
	private int boardnum;
	private String userid;
	private String replytext;
	private String regdate;
	
	public Reply() { }

	public Reply(int replynum, int boardnum, String userid, String replytext, String regdate) {
		super();
		this.replynum = replynum;
		this.boardnum = boardnum;
		this.userid = userid;
		this.replytext = replytext;
		this.regdate = regdate;
	}
	
	public int getReplynum() {
		return replynum;
	}

	public int getBoardnum() {
		return boardnum;
	}

	public String getUserid() {
		return userid;
	}

	public String getReplytext() {
		return replytext;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setReplynum(int replynum) {
		this.replynum = replynum;
	}

	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "Reply [replynum=" + replynum + ", boardnum=" + boardnum + ", userid=" + userid + ", replytext="
				+ replytext + ", regdate=" + regdate + "]";
	}
	
	
}
