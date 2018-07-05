package global.sesoc.test7.vo;

public class Board {
	
	private String boardnum;
	private String userid;
	private String title;
	private String content;
	private int hitcount;
	private String regdate;
	private String originalfile;
	private String savedfile;
	
	public Board() { }

	public Board(String boardnum, String userid, String title, String content, int hitcount, String regdate,
			String originalfile, String savedfile) {
		super();
		this.boardnum = boardnum;
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.hitcount = hitcount;
		this.regdate = regdate;
		this.originalfile = originalfile;
		this.savedfile = savedfile;
	}

	public String getBoardnum() {
		return boardnum;
	}

	public String getUserid() {
		return userid;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public int getHitcount() {
		return hitcount;
	}

	public String getRegdate() {
		return regdate;
	}

	public String getOriginalfile() {
		return originalfile;
	}

	public String getSavedfile() {
		return savedfile;
	}

	public void setBoardnum(String boardnum) {
		this.boardnum = boardnum;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setHitcount(int hitcount) {
		this.hitcount = hitcount;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public void setOriginalfile(String originalfile) {
		this.originalfile = originalfile;
	}

	public void setSavedfile(String savedfile) {
		this.savedfile = savedfile;
	}

	@Override
	public String toString() {
		return "Board [boardnum=" + boardnum + ", userid=" + userid + ", title=" + title + ", content=" + content
				+ ", hitcount=" + hitcount + ", regdate=" + regdate + ", originalfile=" + originalfile + ", savedfile="
				+ savedfile + "]";
	}
	
}
