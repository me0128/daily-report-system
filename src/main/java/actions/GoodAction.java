package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.GoodView;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.EmployeeService;
import services.GoodService;
import services.ReportService;

/**
 * いいねに関わるactionクラス
 *
 */
public class GoodAction extends ActionBase {

	private GoodService service;
	private ReportService reportService;
	private EmployeeService employeeService;

	@Override
	public void process() throws ServletException, IOException {
		service = new GoodService();
		reportService = new ReportService();
		employeeService = new EmployeeService();
		//メソッド実行
		invoke();
		employeeService.close();
		reportService.close();
		service.close();
	}

	//いいね登録
	public void good() throws ServletException, IOException {

		//idを条件に日報データを取得する
		ReportView rv = reportService.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));
		//ログイン中の従業員IDを取得
		EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

		putRequestScope(AttributeConst.GOOD, new GoodView());//空のいいねインスタンス

		//インスタンス作成
		GoodView gv = new GoodView(
				null,
				rv,
				ev);
		//いいね情報登録
		service.create(gv);

		//セッションに登録完了のフラッシュメッセージを設定
		putSessionScope(AttributeConst.FLUSH, MessageConst.I_GOOD.getMessage());

		//画面リダイレクト
		redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);

	}

	public void index() throws ServletException, IOException {

		//idを条件に日報データを取得する
		ReportView rv = reportService.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));

		//指定した日報のいいねデータ一覧を、指定されたページ数の一覧画面に表示する分取得する
		int page = getPage();
		List<GoodView> goods = service.getMinePerPage(rv, page);

		//指定した日報のいいねデータの件数を取得
		long goodsCount = service.countAllMine(rv);

		putRequestScope(AttributeConst.GOODS, goods); //取得したいいねデータ
		putRequestScope(AttributeConst.GOOD_COUNT, goodsCount); //指定した日報のいいねの数
		putRequestScope(AttributeConst.PAGE, page); //ページ数
		putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

		//セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
		String flush = getSessionScope(AttributeConst.FLUSH);
		if (flush != null) {
			putRequestScope(AttributeConst.FLUSH, flush);
			removeSessionScope(AttributeConst.FLUSH);
		}

		//一覧画面を表示
		forward(ForwardConst.FW_GOOD_INDEX);
	}

	//いいね削除
	public void destroy() throws ServletException, IOException {

		//セッションからログイン中に従業員情報を取得(従業員インスタンス）
		EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);
		//日報idを取得する
		ReportView rv = reportService.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));

		GoodView gv= service.getGoodbyReportAndEmployee(ev,rv);

		if (gv==null) {
			//セッションに削除のフラッシュメッセージを設定
			putSessionScope(AttributeConst.FLUSH, MessageConst.E_GOOD_DEL.getMessage());
			} else {
			//既にいいね済みの場合いいね削除
			service.destroy(toNumber(getRequestParam(AttributeConst.GOOD_ID)));

			//セッションに削除のフラッシュメッセージを設定
			putSessionScope(AttributeConst.FLUSH, MessageConst.I_GOOD_DEL.getMessage());
			//画面リダイレクト
			redirect(ForwardConst.ACT_GOOD, ForwardConst.CMD_INDEX);

		}
	}
}
