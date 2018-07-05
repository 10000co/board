package global.sesoc.test7.util;

public class Test {
	public static void main(String[] args) {
		PageNavigator navi = new PageNavigator(14, 152);
		
		System.out.println("currentPage: " + 14);
		System.out.println("totalRecordCount: " + 152);
		
		System.out.println("\n=============================================\n");
		
		System.out.println("페이지당 레코드 개수: " + navi.getCountPerPage());
		System.out.println("그룹당 페이지 개수: " + navi.getPagePerGroup());
		System.out.println("현재 페이지: " + navi.getCurrentPage());
		System.out.println("총 레코드 카운트: " + navi.getTotalRecordCount());
		System.out.println("총 페이지 수: " + navi.getTotalPageCount());
		System.out.println("현재 페이지 그룹: " + navi.getCurrentGroup());
		System.out.println("시작 페이지: " + navi.getStartPageGroup());
		System.out.println("끝 페이지: " + navi.getEndPageGroup());
		System.out.println("시작 레코드: " + navi.getStartRecord());
		
	}
}